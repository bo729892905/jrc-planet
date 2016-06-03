/**
 * Created by rxb on 2016/6/3.
 */
var RoleListOpt = {
    listId: "roleList",
    createWinId: "createRoleWin",
    createFormId: "addRoleForm",
    /**
     * 初始化
     */
    init: function () {
        RoleListOpt.initTable();
        $("#toCreateRole").unbind("click").bind("click", RoleListOpt.toCreateFn);
        $("#toDeleteRole").unbind("click").bind("click", RoleListOpt.toDeleteFn);
        $("#deleteRole").unbind("click").bind("click", RoleListOpt.deleteFn);
        $("#cancelDeleteRole").unbind("click").bind("click", RoleListOpt.cancelDeleteFn);
    },

    /**
     * 初始化table
     */
    initTable: function () {
        var columns = [[
            {field: 'name', title: '名称', width: 100},
            {field: 'code', title: '编码', width: 100},
            {field: 'gender', title: '描述', width: 200}
        ]];
        var opt = {
            url: ctx + "/role/list",
            columns: columns,
            showCheckBox: false,
            toolbar: '#roleToolBar',
            onOpen: RoleListOpt.initSearchBox
        };
        EasyuiUtil.initDatagrid(this.listId, opt)
    },

    /**
     * 初始化搜索框
     */
    initSearchBox: function () {
        if (!$("#roleSearchMenu").is(":hidden")) {
            $('#roleSearch').searchbox({
                searcher: function (value, name) {
                    RoleListOpt.find(value, name);
                },
                menu: '#roleSearchMenu',
                prompt: '请输入值',
                height: 32
            });
        }
    },

    /**
     * 关键字查找角色
     * @param value 属性值
     * @param name 属性名
     */
    find: function (value, name) {
        var obj = {};
        obj[name] = value;
        $("#" + this.listId).datagrid('reload', obj);
    },

    /**
     * 开始新建角色
     */
    toCreateFn: function () {
        var win = $('#' + RoleListOpt.createWinId)
        var content = win.text();
        if (content) {//若已初始化直接打开
            win.dialog("open");
            $('#' + RoleListOpt.createFormId).form('reset');
        } else {//初始化对话框
            var opt = {
                title: '新建角色',
                href: ctx + '/partials/role/create-role.html',
                width: 800,
                height: 675,
                okFn: RoleListOpt.saveFn
            };
            EasyuiUtil.initDialog(RoleListOpt.createWinId, opt);
            win.dialog("open");
        }
    },

    /**
     * 保存角色
     */
    saveFn: function () {
        $.easyui.loading();
        $('#' + RoleListOpt.createFormId).form('submit', {
            url: "user/addUser",
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (!isValid) {
                    $.easyui.loaded();
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                data = JSON.parse(data);
                RoleListOpt.callbackSaveFn(data);
            }
        });
    },

    /**
     * 保存角色回调函数
     * @param data
     */
    callbackSaveFn: function (data) {
        if (data.result) {
            $.easyui.loaded();	// 如果提交成功则隐藏进度条
            $("#" + RoleListOpt.listId).datagrid("appendRow", data.data);
            $('#' + RoleListOpt.createWinId).dialog('close');
            MessageUtil.msgslide("保存成功！");
        }
    },

    /**
     * 开始删除角色
     */
    toDeleteFn: function () {
        var obj = $("#" + RoleListOpt.listId);
        //显示多选框
        obj.datagrid('showColumn', 'checkbox');
        //设置可多选
        obj.datagrid("options").singleSelect = false;
        //调整由于显示多选框导致的滚动条
        obj.datagrid("resize");
        $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
        $('#deleteUserToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    },

    /**
     * 删除角色
     */
    deleteFn: function () {
        var obj = $("#" + RoleListOpt.listId);
        var selections = obj.datagrid("getSelections");
        if (selections.length > 0) {
            MessageUtil.confirm("确定要删除选中角色吗？", function (rlt) {
                if (rlt) {
                    var id = [];
                    for (var i = 0; i < selections.length; i++) {
                        id.push(selections[i].id);
                    }
                    BaseUtil.ajax({
                        url: ctx + "/user/deleteUser",
                        type: "POST",
                        data: {id: id},
                        callback: RoleListOpt.callbackDeleteFn
                    })
                }
            });
        } else {
            MessageUtil.alert("请选择角色！")
        }
    },

    /**
     * 删除角色回调函数
     * @param data
     */
    callbackDeleteFn: function (data) {
        if (data.result) {
            EasyuiUtil.deleteRow(RoleListOpt.listId);
            RoleListOpt.deleteEndFn();
            MessageUtil.msgslide("删除成功！");
        }
    },

    /**
     * 取消删除角色
     */
    cancelDeleteFn: function () {
        RoleListOpt.deleteEndFn();
    },

    /**
     * 删除取消或结束
     */
    deleteEndFn: function () {
        var obj = $("#" + RoleListOpt.listId);
        obj.datagrid('hideColumn', 'checkbox');
        obj.datagrid("unselectAll");
        obj.datagrid("options").singleSelect = true;
        obj.datagrid("resize");
        RoleListOpt.reInitToolBar();
    },

    /**
     * 重新初始工具栏
     */
    reInitToolBar: function () {
        $('.panel.datagrid:visible .visible-tool-bar').removeClass('visible-tool-bar').addClass('hidden-tool-bar');
        $('#initialUserToolBar').removeClass('hidden-tool-bar').addClass('visible-tool-bar');
    }
};

/*$(function () {
 RoleListOpt.init();
 });*/
