/**
 * Created by rxb on 2016/6/2.
 */
var PermGridOpt = {
    treeFormId: "permTreeForm",
    treeListId: "permTreeList",
    treeMenuId: "permTreeListMenu",
    tempIds: []
};

PermGridOpt.init = function () {
    this.initTreeGrid();
    PermGridOpt.initMenu();
    $("#toAddChildPerm").unbind("click").bind("click", PermGridOpt.toAddChildPermFn);
    $("#toAddRootPerm").unbind("click").bind("click", PermGridOpt.toAddRootFn);
    $("#addPerm").unbind("click").bind("click", PermGridOpt.addFn);
    $("#cancelAddPerm").unbind("click").bind("click", PermGridOpt.cancelAddFn);
    $("#toEditPerm").unbind("click").bind("click", PermGridOpt.toEditFn);
    $("#editPerm").unbind("click").bind("click", PermGridOpt.confirmUpdateFn);
    $("#cancelEditPerm").unbind("click").bind("click", PermGridOpt.cancelEditFn);
};

PermGridOpt.initTreeGrid = function () {
    $('#' + PermGridOpt.treeListId).treegrid({
        rownumbers: true,
        animate: true,
        collapsible: true,
        border: false,
        fit: true,
        fitColumns: true,
        url: 'perm/treegrid',
        method: 'get',
        idField: 'id',
        treeField: 'name',
        columns: [[
            {field: 'name', title: '权限名称', width: 100, editor: {type: 'validatebox', options: {required: true}}},
            {field: 'url', title: '权限地址', width: 80},
            {field: 'visible', title: '是否可见', width: 80, editor: {type: 'validatebox'}},
            {field: 'remark', title: '描述', width: 80, editor: {type: 'validatebox'}}
        ]],
        toolbar: "#permToolBar",
        onContextMenu: PermGridOpt.showMenu,
        onLoadSuccess: function (row) {
            $(this).treegrid('enableDnd', row ? row.id : null);
        },
        onBeforeSelect: function () {
            var visible = $("#editPermToolBar").is(":visible");
            if (visible) {//编辑状态禁止选择其他行
                return false;
            }
        },
        onBeforeDrop:function(targetRow,sourceRow) {
            /*var accept=false
            MessageUtil.confirm("确定要移动到此处吗？", function (rlt) {
                if(rlt) {
                    accept=true
                }
            });
            return accept;*/
        },
        onDrop:function(targetRow,sourceRow) {
            PermGridOpt.moveFn(targetRow.id,sourceRow.id);
        }
    });
};

/**
 * 初始化右键菜单
 */
PermGridOpt.initMenu = function () {
    $('#' + PermGridOpt.treeMenuId).menu({});
};

/**
 * 显示右键菜单
 * @param e
 * @param row
 */
PermGridOpt.showMenu = function (e, row) {
    e.preventDefault();
    $('#' + PermGridOpt.treeListId).treegrid('select', row.id);
    $('#' + PermGridOpt.treeMenuId).menu('show', {
        left: e.pageX,
        top: e.pageY
    });
};

PermGridOpt.toAddRootFn = function () {
    var uuid = BaseUtil.uuid();
    uuid = uuid.replace(/\-/g, "");
    var obj = $('#' + PermGridOpt.treeListId);

    var roots = obj.treegrid("getRoots");
    var rootNode = roots[roots.length - 1];

    /*新增行*/
    obj.treegrid('append', {
        after: rootNode.id,
        data: [{
            id: uuid,
            name: '新建权限',
            url: null,
            visible: true
        }]
    });

    obj.treegrid("disableDnd");
    obj.treegrid('select', uuid);
    obj.treegrid("beginEdit", uuid);
    PermGridOpt.tempIds.push(uuid);

    $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
    $('#addPermToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
};

/**
 * 保存添加
 */
PermGridOpt.addFn = function () {
    var formObj = $('#' + PermGridOpt.treeFormId);
    var tabObj = $('#' + PermGridOpt.treeListId);
    if (formObj.form("validate")) {
        PermGridOpt.tempIds.forEach(function (e) {
            tabObj.treegrid("endEdit", e);
        });
        var changes = tabObj.treegrid("getChanges");
        $.easyui.loading();
        changes.forEach(function (data) {
            $.ajax({
                url: ctx + "/perm/addPerm",
                data: data,
                type: "POST",
                dataType: "json",
                async: true
            });
        });
        $.easyui.loaded();
        MessageUtil.msgslide("保存成功!");
        tabObj.treegrid("acceptChanges");
        PermGridOpt.endAddFn();
    } else {
        //ignore
    }
};

/**
 * 取消添加
 */
PermGridOpt.cancelAddFn = function () {
    var tabObj = $('#' + PermGridOpt.treeListId);
    PermGridOpt.tempIds.forEach(function (e) {
        tabObj.treegrid("cancelEdit", e);
        tabObj.treegrid("remove", e);
    });
    PermGridOpt.endAddFn();
};

/**
 * 结束添加
 */
PermGridOpt.endAddFn = function () {
    PermGridOpt.tempIds = [];
    PermGridOpt.reInitToolBar();
};

PermGridOpt.reInitToolBar = function () {
    $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
    $('#initialPermToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    $('#' + PermGridOpt.treeListId).treegrid('enableDnd');
};

/**
 * 开始编辑
 */
PermGridOpt.toEditFn = function () {
    var obj = $('#' + PermGridOpt.treeListId);
    var selected = obj.treegrid("getSelected");
    if (selected) {
        var id = selected.id;
        obj.treegrid("disableDnd");
        obj.treegrid("beginEdit", id);

        $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
        $('#editPermToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    } else {
        MessageUtil.alert("请选择数据！");
    }
};

/**
 * 确认更新用户
 */
PermGridOpt.confirmUpdateFn = function () {
    if ($('#' + PermGridOpt.treeFormId).form("validate")) {
        MessageUtil.confirm("确定要保存修改吗？", PermGridOpt.updateFn);
    }
};

/**
 * 更新用户
 */
PermGridOpt.updateFn = function (rlt) {
    if (rlt) {
        var obj = $('#' + PermGridOpt.treeListId);
        var selected = obj.treegrid("getSelected");
        obj.treegrid("endEdit", selected.id);

        var changes = obj.treegrid("getChanges");
        if (changes.length == 1) {//当且仅当有一条数据改变时保存
            var change = changes[0];
            var data = {
                id: change.id,
                name: change.name,
                enName: change.enName,
                url: change.url,
                iconCls: change.iconCls
            };
            BaseUtil.ajax({
                url: ctx + "/perm/updatePerm",
                type: "POST",
                data: data,
                callback: PermGridOpt.callbackUpdate
            });
        } else {
            PermGridOpt.reInitToolBar();
        }
    }
};

/**
 * 更新用户回调函数
 * @param data
 */
PermGridOpt.callbackUpdate = function (data) {
    var obj = $('#' + PermGridOpt.treeListId);
    obj.treegrid("acceptChanges");
    PermGridOpt.reInitToolBar();
    obj.treegrid('enableDnd');
    MessageUtil.msgslide("修改成功！");
};

PermGridOpt.cancelEditFn = function () {
    var obj = $('#' + PermGridOpt.treeListId);
    var selected = obj.treegrid("getSelected");
    obj.treegrid("cancelEdit", selected.id);
    PermGridOpt.reInitToolBar();
};

PermGridOpt.moveFn=function(targetId,sourceId) {
    BaseUtil.ajax({
        url: ctx + "/perm/movePerm",
        type: "POST",
        data: {targetId:targetId,sourceId:sourceId},
        callback: PermGridOpt.callbackMove
    });
};

PermGridOpt.callbackMove = function (data) {
    MessageUtil.msgslide("移动成功！");
};
