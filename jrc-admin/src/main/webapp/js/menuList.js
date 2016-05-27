/**
 * 菜单操作js
 * Created by rxb on 2016/5/26.
 */
var menuGridOpt = {
    menuTreeFormId: "menuTreeForm",
    menuTreeListId: "menuTreeList",
    treeMenuId: "menuTreeListMenu",
    tempId: 100,
    tempIds: [],
    init: function () {
        menuGridOpt.initTreeGrid();
        menuGridOpt.initMenu();
        $("#toAddMenu").unbind("click").bind("click", menuGridOpt.toAddMenuFn);
        $("#addMenu").unbind("click").bind("click", menuGridOpt.addMenuFn);
        $("#cancelAddMenu").unbind("click").bind("click", menuGridOpt.cancelAddMenuFn);
        $("#toDeleteMenu").unbind("click").bind("click", menuGridOpt.toDeleteMenuFn);
        $("#toEditMemu").unbind("click").bind("click", menuGridOpt.toEditMenuFn);
        $("#editMenu").unbind("click").bind("click", menuGridOpt.confirmUpdateMenuFn);
        $("#cancelEditMenu").unbind("click").bind("click", menuGridOpt.cancelEditMenuFn);
    },

    /**
     * 初始化表格树
     */
    initTreeGrid: function () {
        $('#' + menuGridOpt.menuTreeListId).treegrid({
            rownumbers: true,
            animate: true,
            collapsible: true,
            border: false,
            fit: true,
            fitColumns: true,
            url: 'menu/treegrid',
            method: 'get',
            idField: 'id',
            treeField: 'name',
            columns: [[
                {field: 'name', title: '名称', width: 100, editor: {type: 'validatebox', options: {required: true}}},
                {field: 'enName', title: '英文名', width: 60, align: 'right', editor: {type: 'validatebox'}},
                {field: 'url', title: '请求地址', width: 80, editor: {type: 'validatebox'}},
                {field: 'iconCls', title: '图标样式', width: 80, editor: {type: 'validatebox'}}
            ]],
            toolbar: "#menuToolBar",
            onContextMenu: menuGridOpt.showMenu,
            onBeforeSelect: function () {
                var visible = $("#editMenuToolBar").is(":visible");
                if (visible) {//编辑状态禁止选择其他行
                    return false;
                }
            }
        });
    },

    /**
     * 初始化右键菜单
     */
    initMenu: function () {
        $('#' + menuGridOpt.treeMenuId).menu({});
    },

    /**
     * 显示右键菜单
     * @param e
     * @param row
     */
    showMenu: function (e, row) {
        e.preventDefault();
        $('#' + menuGridOpt.menuTreeListId).treegrid('select', row.id);
        $('#' + menuGridOpt.treeMenuId).menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    },

    /**
     * 开始新增菜单
     */
    toAddMenuFn: function () {
        var uuid = BaseUtil.uuid();
        uuid = uuid.replace(/\-/g, "");
        var obj = $('#' + menuGridOpt.menuTreeListId);
        var node = obj.treegrid("getSelected");
        if (!node) {
            MessageUtil.alert("请选择父菜单！");
        } else {
            /*新增行*/
            obj.treegrid('append', {
                parent: node.id,
                data: [{
                    id: uuid,
                    name: '新建菜单',
                    url: null,
                    iconCls: null,
                    parentId: node.id
                }]
            });

            obj.treegrid('select', uuid);
            obj.treegrid("beginEdit", uuid);
            menuGridOpt.tempIds.push(uuid);

            $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
            $('#addMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
        }
    },

    /**
     * 保存添加
     */
    addMenuFn: function () {
        var formObj = $('#' + menuGridOpt.menuTreeFormId);
        var tabObj = $('#' + menuGridOpt.menuTreeListId);
        if (formObj.form("validate")) {
            for (var i = 0; i < menuGridOpt.tempIds.length; i++) {
                tabObj.treegrid("endEdit", menuGridOpt.tempIds[i]);
            }
            var changes = tabObj.treegrid("getChanges");
            $.easyui.loading();
            for (var j = 0; j < changes.length; j++) {
                var data = changes[j];
                $.ajax({
                    url: ctx + "/menu/addMenu",
                    data: data,
                    type: "POST",
                    dataType: "json",
                    async: true
                });
            }
            $.easyui.loaded();
            MessageUtil.msgslide("保存成功!");
            tabObj.treegrid("acceptChanges");
            menuGridOpt.endAddMenuFn();
        } else {
            //ignore
        }
    },

    /**
     * 取消添加
     */
    cancelAddMenuFn: function () {
        var tabObj = $('#' + menuGridOpt.menuTreeListId);
        for (var i = 0; i < menuGridOpt.tempIds.length; i++) {
            tabObj.treegrid("cancelEdit", menuGridOpt.tempIds[i]);
            tabObj.treegrid("remove", menuGridOpt.tempIds[i]);
        }
        menuGridOpt.endAddMenuFn();
    },

    /**
     * 结束添加
     */
    endAddMenuFn: function () {
        menuGridOpt.tempIds = [];
        menuGridOpt.reInitToolBar();
    },

    reInitToolBar: function () {
        $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
        $('#initialMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    },

    /**
     * 开始删除
     */
    toDeleteMenuFn: function () {
        var tabObj = $('#' + menuGridOpt.menuTreeListId);
        var selected = tabObj.treegrid("getSelected");
        if (selected) {
            MessageUtil.confirm("确定要删除吗？", menuGridOpt.deleteMenuFn);
        } else {
            MessageUtil.alert("请选择数据！");
        }
    },

    /**
     * 确定删除
     * @param rlt
     */
    deleteMenuFn: function (rlt) {
        if (rlt) {
            var tabObj = $('#' + menuGridOpt.menuTreeListId);
            var id = tabObj.treegrid("getSelected").id;
            BaseUtil.ajax({
                url: ctx + "/menu/deleteMenu",
                data: {id: id},
                callback: menuGridOpt.callbackDeleteMenuFn
            });
        }
    },

    /**
     * 取消删除
     * @param data
     */
    callbackDeleteMenuFn: function (data) {
        if (data.result) {
            var tabObj = $('#' + menuGridOpt.menuTreeListId);
            var id = tabObj.treegrid("getSelected").id;
            tabObj.treegrid("remove", id);
            MessageUtil.msgslide("删除成功!");
        }
    },

    /**
     * 开始编辑
     */
    toEditMenuFn: function () {
        var obj = $('#' + menuGridOpt.menuTreeListId);
        var selected = obj.treegrid("getSelected");
        if (selected) {
            var id = selected.id;
            obj.treegrid("beginEdit", id);

            $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
            $('#editMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
        } else {
            MessageUtil.alert("请选择数据！");
        }
    },

    /**
     * 确认更新用户
     */
    confirmUpdateMenuFn: function () {
        MessageUtil.confirm("确定要保存修改吗？", menuGridOpt.updateMenuFn);
    },

    /**
     * 更新用户
     */
    updateMenuFn: function (rlt) {
        if (rlt) {
            var obj = $('#' + menuGridOpt.menuTreeListId);
            var selected = obj.treegrid("getSelected");
            obj.treegrid("endEdit", selected.id);

            var changes = obj.treegrid("getChanges");
            if (changes.length == 1) {//当且仅当有一条数据改变时保存
                var data = changes[0];
                BaseUtil.ajax({
                    url: ctx + "/menu/updateMenu",
                    type: "POST",
                    data: data,
                    callback: menuGridOpt.callbackUpdateMenu
                });
            }else{
                menuGridOpt.reInitToolBar();
            }

        }
    },

    /**
     * 更新用户回调函数
     * @param data
     */
    callbackUpdateMenu: function (data) {
        $('#' + menuGridOpt.menuTreeListId).treegrid("acceptChanges");
        menuGridOpt.reInitToolBar();
        MessageUtil.msgslide("修改成功！")
    },

    cancelEditMenuFn:function() {
        var obj = $('#' + menuGridOpt.menuTreeListId);
        var selected = obj.treegrid("getSelected");
        obj.treegrid("cancelEdit", selected.id);
        menuGridOpt.reInitToolBar();
    }
};

/*
 $(function () {
 menuGridOpt.init();
 });*/
