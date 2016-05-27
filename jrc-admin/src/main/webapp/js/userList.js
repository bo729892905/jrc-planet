var userListOpt = {
    userListId: "userList",
    createWinId: "createUserWin",
    createFormId: "addUserForm",
    /**
     * 初始化
     */
    init: function () {
        userListOpt.initUserTable();
        $("#toCreateUser").unbind("click").bind("click", userListOpt.toCreateUserFn);
        $("#toDeleteUser").unbind("click").bind("click", userListOpt.toDeleteUserFn);
        $("#deleteUser").unbind("click").bind("click", userListOpt.deleteUserFn);
        $("#cancelDeleteUser").unbind("click").bind("click", userListOpt.cancelDeleteFn);
    },

    /**
     * 初始化table
     */
    initUserTable: function () {
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
            onOpen: userListOpt.initSearchBox
        };
        EasyuiUtil.initDatagrid(this.userListId, opt)
    },

    /**
     * 初始化搜索框
     */
    initSearchBox: function () {
        if (!$("#userSearchMenu").is(":hidden")) {
            $('#userSearch').searchbox({
                searcher: function (value, name) {
                    userListOpt.findUser(value, name);
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
    findUser: function (value, name) {
        var obj = {};
        obj[name] = value;
        $("#" + this.userListId).datagrid('reload', obj);
    },

    /**
     * 开始新建用户
     */
    toCreateUserFn: function () {
        var win = $('#' + userListOpt.createWinId)
        var content = win.text();
        if (content) {//若已初始化直接打开
            win.dialog("open");
            $('#' + userListOpt.createFormId).form('reset');
        } else {//初始化对话框
            var opt = {
                title: '新建用户',
                href: ctx + '/partials/user/create-user.html',
                height: 500,
                okFn: userListOpt.saveUserFn
            };
            EasyuiUtil.initDialog(userListOpt.createWinId, opt);
        }
    },

    /**
     * 保存用户
     */
    saveUserFn: function () {
        $.easyui.loading();
        $('#' + userListOpt.createFormId).form('submit', {
            url: "user/addUser",
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (!isValid) {
                    $.easyui.loaded();
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                data = eval('(' + data + ')');
                userListOpt.backSaveUserFn(data);
            }
        });
    },

    /**
     * 保存用户回调函数
     * @param data
     */
    backSaveUserFn: function (data) {
        if (data.result) {
            $.easyui.loaded();	// 如果提交成功则隐藏进度条
            $("#" + userListOpt.userListId).datagrid("appendRow", data.data);
            $('#' + userListOpt.createWinId).dialog('close');
            MessageUtil.msgslide("保存成功！");
        }
    },

    /**
     * 开始删除用户
     */
    toDeleteUserFn: function () {
        var obj = $("#" + userListOpt.userListId);
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
    deleteUserFn: function () {
        var obj = $("#" + userListOpt.userListId);
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
                        callback: userListOpt.backDeleteUserFn
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
    backDeleteUserFn: function (data) {
        if (data.result) {
            EasyuiUtil.deleteRow(userListOpt.userListId);
            userListOpt.deleteEndFn();
            MessageUtil.msgslide("删除成功！");
        }
    },

    /**
     * 取消删除用户
     */
    cancelDeleteFn: function () {
        userListOpt.deleteEndFn();
    },

    /**
     * 删除取消或结束
     */
    deleteEndFn: function () {
        var obj = $("#" + userListOpt.userListId);
        obj.datagrid('hideColumn', 'checkbox');
        obj.datagrid("unselectAll");
        obj.datagrid("options").singleSelect = true;
        obj.datagrid("resize");
        userListOpt.reInitToolBar();
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
 userListOpt.init();
 });*/
