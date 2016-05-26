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
            onContextMenu: menuGridOpt.showMenu
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
            EasyuiUtil.alert("请选择父菜单！");
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

            $('.visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
            $('#addMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
        }
    },

    addMenuFn: function () {
        var formObj = $('#' + menuGridOpt.menuTreeFormId);
        var tabObj = $('#' + menuGridOpt.menuTreeListId);
        if (formObj.form("validate")) {
            for (var i = 0; i < menuGridOpt.tempIds.length; i++) {
                tabObj.treegrid("endEdit", menuGridOpt.tempIds[i]);
            }
            var changes = tabObj.treegrid("getChanges");
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
            tabObj.treegrid("acceptChanges");
            menuGridOpt.endAddMenuFn();
        } else {
            //ignore
        }
    },

    cancelAddMenuFn: function () {
        var tabObj = $('#' + menuGridOpt.menuTreeListId);
        for (var i = 0; i < menuGridOpt.tempIds.length; i++) {
            tabObj.treegrid("cancelEdit", menuGridOpt.tempIds[i]);
            tabObj.treegrid("remove", menuGridOpt.tempIds[i]);
        }
        menuGridOpt.endAddMenuFn();
    },

    endAddMenuFn: function () {
        menuGridOpt.tempIds = [];
        menuGridOpt.reInitToolBar();
    },

    reInitToolBar: function () {
        $('.panel:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
        $('#initialMenuToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    },

    toDeleteMenuFn:function() {
        var tabObj = $('#' + menuGridOpt.menuTreeListId);
        var selected = tabObj.treegrid("getSelected");
        if(selected) {
            EasyuiUtil.confirm("确定要删除吗？", menuGridOpt.deleteMenuFn);
        }else {
            EasyuiUtil.alert("请选择数据！");
        }
    },

    deleteMenuFn:function(rlt) {
        if(rlt) {
            var tabObj = $('#' + menuGridOpt.menuTreeListId);
            var id = tabObj.treegrid("getSelected").id;
            $.ajax({
                url: ctx + "/menu/deleteMenu",
                data: {id:id},
                type: "POST",
                dataType: "json",
                success:function(data) {
                    if(data.result) {
                        tabObj.treegrid("remove", id);
                        EasyuiUtil.msgslide("删除成功!");
                    }
                }
            });
        }
    }
};

/*
 $(function () {
 menuGridOpt.init();
 });*/
