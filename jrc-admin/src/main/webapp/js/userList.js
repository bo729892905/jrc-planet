var userListOpt = {
    userListId: "userList",
    createWinId: "createUserWin",
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
    initSearchBox: function () {
        $('#userSearch').searchbox({
            searcher: function(value,name){
                //alert(value + "," + name)
                userListOpt.findUser(value, name);
            },
            menu: '#userSearchMenu',
            prompt: '请输入值',
            height: 32
        });
    },
    findUser:function(value, name) {
        var obj={};
        obj[name] = value;
        $("#" + this.userListId).datagrid('reload', obj);
    },
    toCreateUserFn: function () {
        var opt = {
            title: '新建用户',
            okFn: userListOpt.saveUserFn
        };
        EasyuiUtil.initDialog(userListOpt.createWinId, opt);
    },
    saveUserFn: function () {
        $.messager.progress();	// 显示进度条
        $('#addUserForm').form('submit', {
            url: "user/addUser",
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (!isValid) {
                    $.messager.progress('close');
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                data = eval('(' + data + ')');
                var userListId = "userList";
                $.messager.progress('close');	// 如果提交成功则隐藏进度条
                $("#" + userListOpt.userListId).datagrid("appendRow", data.data);
                $('#' + userListOpt.createWinId).dialog('close');
            }
        });
    },
    toDeleteUserFn: function () {
        var userListId = "userList";
        var obj = $("#" + userListId);
        //显示多选框
        obj.datagrid('showColumn', 'checkbox');
        //设置可多选
        obj.datagrid("options").singleSelect = false;
        //调整由于显示多选框导致的滚动条
        obj.datagrid("resize");
    }
}

/*
$(function() {
    userListOpt.initUserTable();
    $("#toCreateUser").unbind("click").bind("click",userListOpt.toCreateUserFn);
    $("#toDeleteUser").unbind("click").bind("click", userListOpt.toDeleteUserFn);
});*/
