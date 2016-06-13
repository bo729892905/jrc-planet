var UserListOpt = {
    listId: "userList",
    createWinId: "createUserWin",
    createFormId: "addUserForm",
    /**
     * 初始化
     */
    init: function () {
        UserListOpt.initTable();
        $("#toCreateUser").unbind("click").bind("click", UserListOpt.toCreateFn);
        $("#toDeleteUser").unbind("click").bind("click", UserListOpt.toDeleteFn);
        $("#deleteUser").unbind("click").bind("click", UserListOpt.deleteFn);
        $("#cancelDeleteUser").unbind("click").bind("click", UserListOpt.cancelDeleteFn);
    },

    /**
     * 初始化table
     */
    initTable: function () {
        var columns = [[
            {field: 'username', title: '用户名', width: 100},
            {field: 'realName', title: '姓名', width: 100},
            {field: 'gender', title: '性别', width: 100, align: 'right'},
            {field: 'mobilePhone', title: '手机号', width: 100, align: 'right'},
            {field: 'email', title: '邮箱', width: 100, align: 'right'}
        ]];
        var opt = {
            url: ctx + "/user/list",
            columns: columns,
            showCheckBox: false,
            toolbar: '#userToolBar',
            onOpen: UserListOpt.initSearchBox
        };
        EasyuiUtil.initDatagrid(this.listId, opt)
    },

    /**
     * 初始化搜索框
     */
    initSearchBox: function () {
        if (!$("#userSearchMenu").is(":hidden")) {
            $('#userSearch').searchbox({
                searcher: function (value, name) {
                    UserListOpt.find(value, name);
                },
                menu: '#userSearchMenu',
                prompt: '请输入值',
                height: 32
            });
        }
    },

    /**
     * 关键字查找用户
     * @param value 属性值
     * @param name 属性名
     */
    find: function (value, name) {
        var obj = {};
        obj[name] = value;
        $("#" + this.listId).datagrid('reload', obj);
    },

    /**
     * 开始新建用户
     */
    toCreateFn: function () {
        var win = $('#' + UserListOpt.createWinId)
        var content = win.text();
        if (content) {//若已初始化直接打开
            win.dialog("open");
            $('#' + UserListOpt.createFormId).form('reset');
        } else {//初始化对话框
            var opt = {
                title: '新建用户',
                href: ctx + '/partials/user/create-user.html',
                width: 500,
                height: 560,
                okFn: UserListOpt.saveFn
            };
            EasyuiUtil.initDialog(UserListOpt.createWinId, opt);
            win.dialog("open");
        }
    },

    /**
     * 保存用户
     */
    saveFn: function () {
        $.easyui.loading();
        $('#' + UserListOpt.createFormId).form('submit', {
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
                UserListOpt.callbackSaveFn(data);
            }
        });
    },

    /**
     * 保存用户回调函数
     * @param data
     */
    callbackSaveFn: function (data) {
        if (data.result) {
            $.easyui.loaded();	// 如果提交成功则隐藏进度条
            $("#" + UserListOpt.listId).datagrid("appendRow", data.data);
            $('#' + UserListOpt.createWinId).dialog('close');
            MessageUtil.msgslide("保存成功！");
        }
    },

    /**
     * 开始删除用户
     */
    toDeleteFn: function () {
        var obj = $("#" + UserListOpt.listId);
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
     * 删除用户
     */
    deleteFn: function () {
        var obj = $("#" + UserListOpt.listId);
        var selections = obj.datagrid("getSelections");
        if (selections.length > 0) {
            MessageUtil.confirm("确定要删除选中用户吗？", function (rlt) {
                if (rlt) {
                    var id = [];
                    for (var i = 0; i < selections.length; i++) {
                        id.push(selections[i].id);
                    }
                    BaseUtil.ajax({
                        url: ctx + "/user/deleteUser",
                        type: "POST",
                        data: {id: id},
                        callback: UserListOpt.callbackDeleteFn
                    })
                }
            });
        } else {
            MessageUtil.alert("请选择用户！")
        }
    },

    /**
     * 删除用户回调函数
     * @param data
     */
    callbackDeleteFn: function (data) {
        if (data.result) {
            EasyuiUtil.deleteRow(UserListOpt.listId);
            UserListOpt.deleteEndFn();
            MessageUtil.msgslide("删除成功！");
        }
    },

    /**
     * 取消删除用户
     */
    cancelDeleteFn: function () {
        UserListOpt.deleteEndFn();
    },

    /**
     * 删除取消或结束
     */
    deleteEndFn: function () {
        var obj = $("#" + UserListOpt.listId);
        obj.datagrid('hideColumn', 'checkbox');
        obj.datagrid("unselectAll");
        obj.datagrid("options").singleSelect = true;
        obj.datagrid("resize");
        UserListOpt.reInitToolBar();
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
 UserListOpt.init();
 });*/
