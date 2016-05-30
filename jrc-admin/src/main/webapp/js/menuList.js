/**
 * 菜单操作js
 * Created by rxb on 2016/5/26.
 */
var MenuGridOpt = {
    menuTreeFormId: "menuTreeForm",
    menuTreeListId: "menuTreeList",
    treeMenuId: "menuTreeListMenu",
    tempId: 100,
    tempIds: [],
    init: function () {
        MenuGridOpt.initTreeGrid();
        MenuGridOpt.initMenu();
        $("#toAddChildMenu").unbind("click").bind("click", MenuGridOpt.toAddChildMenuFn);
        $("#toAddRootMenu").unbind("click").bind("click", MenuGridOpt.toAddRootMenuFn);
        $("#addMenu").unbind("click").bind("click", MenuGridOpt.addMenuFn);
        $("#cancelAddMenu").unbind("click").bind("click", MenuGridOpt.cancelAddMenuFn);
        $("#toDeleteMenu").unbind("click").bind("click", MenuGridOpt.toDeleteMenuFn);
        $("#toEditMemu").unbind("click").bind("click", MenuGridOpt.toEditMenuFn);
        $("#editMenu").unbind("click").bind("click", MenuGridOpt.confirmUpdateMenuFn);
        $("#cancelEditMenu").unbind("click").bind("click", MenuGridOpt.cancelEditMenuFn);
    },

    /**
     * 初始化表格树
     */
    initTreeGrid: function () {
        $('#' + MenuGridOpt.menuTreeListId).treegrid({
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
            onContextMenu: MenuGridOpt.showMenu,
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
        $('#' + MenuGridOpt.treeMenuId).menu({});
    },

    /**
     * 显示右键菜单
     * @param e
     * @param row
     */
    showMenu: function (e, row) {
        e.preventDefault();
        $('#' + MenuGridOpt.menuTreeListId).treegrid('select', row.id);
        $('#' + MenuGridOpt.treeMenuId).menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    },

    /**
     * 开始新增子菜单
     */
    toAddChildMenuFn: function () {
        var uuid = BaseUtil.uuid();
        uuid = uuid.replace(/\-/g, "");
        var obj = $('#' + MenuGridOpt.menuTreeListId);
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
            MenuGridOpt.tempIds.push(uuid);

            $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
            $('#addMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
        }
    },

    /**
     * 开始新增跟节点
     */
    toAddRootMenuFn: function () {
        var uuid = BaseUtil.uuid();
        uuid = uuid.replace(/\-/g, "");
        var obj = $('#' + MenuGridOpt.menuTreeListId);

        var roots = obj.treegrid("getRoots");
        var rootNode = roots[roots.length - 1];

        /*新增行*/
        obj.treegrid('append', {
            after: rootNode.id,
            data: [{
                id: uuid,
                name: '新建菜单',
                url: null,
                iconCls: null
            }]
        });

        obj.treegrid('select', uuid);
        obj.treegrid("beginEdit", uuid);
        MenuGridOpt.tempIds.push(uuid);

        $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
        $('#addMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    },

    /**
     * 保存添加
     */
    addMenuFn: function () {
        var formObj = $('#' + MenuGridOpt.menuTreeFormId);
        var tabObj = $('#' + MenuGridOpt.menuTreeListId);
        if (formObj.form("validate")) {
            MenuGridOpt.tempIds.forEach(function (e){
                tabObj.treegrid("endEdit", e);
            });
            var changes = tabObj.treegrid("getChanges");
            $.easyui.loading();
            changes.forEach(function(data){
                $.ajax({
                    url: ctx + "/menu/addMenu",
                    data: data,
                    type: "POST",
                    dataType: "json",
                    async: true
                });
            });
            $.easyui.loaded();
            MessageUtil.msgslide("保存成功!");
            tabObj.treegrid("acceptChanges");
            MenuGridOpt.endAddMenuFn();
        } else {
            //ignore
        }
    },

    /**
     * 取消添加
     */
    cancelAddMenuFn: function () {
        var tabObj = $('#' + MenuGridOpt.menuTreeListId);
        MenuGridOpt.tempIds.forEach(function (e) {
            tabObj.treegrid("cancelEdit", e);
            tabObj.treegrid("remove", e);
        });
        MenuGridOpt.endAddMenuFn();
    },

    /**
     * 结束添加
     */
    endAddMenuFn: function () {
        MenuGridOpt.tempIds = [];
        MenuGridOpt.reInitToolBar();
    },

    reInitToolBar: function () {
        $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
        $('#initialMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    },

    /**
     * 开始删除
     */
    toDeleteMenuFn: function () {
        var tabObj = $('#' + MenuGridOpt.menuTreeListId);
        var selected = tabObj.treegrid("getSelected");
        if (selected) {
            MessageUtil.confirm("确定要删除吗？", MenuGridOpt.deleteMenuFn);
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
            var tabObj = $('#' + MenuGridOpt.menuTreeListId);
            var id = tabObj.treegrid("getSelected").id;
            BaseUtil.ajax({
                url: ctx + "/menu/deleteMenu",
                data: {id: id},
                callback: MenuGridOpt.callbackDeleteMenuFn
            });
        }
    },

    /**
     * 取消删除
     * @param data
     */
    callbackDeleteMenuFn: function (data) {
        if (data.result) {
            var tabObj = $('#' + MenuGridOpt.menuTreeListId);
            var id = tabObj.treegrid("getSelected").id;
            tabObj.treegrid("remove", id);
            MessageUtil.msgslide("删除成功!");
        }
    },

    /**
     * 开始编辑
     */
    toEditMenuFn: function () {
        var obj = $('#' + MenuGridOpt.menuTreeListId);
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
        if($('#' + MenuGridOpt.menuTreeFormId).form("validate")) {
            MessageUtil.confirm("确定要保存修改吗？", MenuGridOpt.updateMenuFn);
        }
    },

    /**
     * 更新用户
     */
    updateMenuFn: function (rlt) {
        if (rlt) {
            var obj = $('#' + MenuGridOpt.menuTreeListId);
            var selected = obj.treegrid("getSelected");
            obj.treegrid("endEdit", selected.id);

            var changes = obj.treegrid("getChanges");
            if (changes.length == 1) {//当且仅当有一条数据改变时保存
                var change = changes[0];
                var data = {
                    id: change.id,
                    name:change.name,
                    enName:change.enName,
                    url:change.url,
                    iconCls:change.iconCls
                };
                BaseUtil.ajax({
                    url: ctx + "/menu/updateMenu",
                    type: "POST",
                    data: data,
                    callback: MenuGridOpt.callbackUpdateMenu
                });
            } else {
                MenuGridOpt.reInitToolBar();
            }

        }
    },

    /**
     * 更新用户回调函数
     * @param data
     */
    callbackUpdateMenu: function (data) {
        $('#' + MenuGridOpt.menuTreeListId).treegrid("acceptChanges");
        MenuGridOpt.reInitToolBar();
        MessageUtil.msgslide("修改成功！")
    },

    cancelEditMenuFn: function () {
        var obj = $('#' + MenuGridOpt.menuTreeListId);
        var selected = obj.treegrid("getSelected");
        obj.treegrid("cancelEdit", selected.id);
        MenuGridOpt.reInitToolBar();
    }
};

/*
 $(function () {
 MenuGridOpt.init();
 });*/
