$(function () {
    initUserTable();
    $("#toCreateUser").unbind("click").bind("click", function () {
        $('#createUserWin').dialog({
            title: '新建用户',
            width: 600,
            height: 400,
            closable: true,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
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
                            data = eval('('+data+')');
                            var userListId = "userList";
                            $.messager.progress('close');	// 如果提交成功则隐藏进度条
                            $("#" + userListId).datagrid("appendRow",data.data);
                            $('#createUserWin').dialog('close');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $('#createUserWin').dialog('close');
                }
            }
            ]
        });
    });
    $("#toDeleteUser").unbind("click").bind("click",toDeleteUserFn);
});

function initUserTable() {
    var userListId = "userList";
    var columns = [[
        {field: 'username', title: '用户名', width: 100},
        {field: 'realName', title: '姓名', width: 100},
        {field: 'gender', title: '性别', width: 100, align: 'right'},
        {field: 'mobilePhone', title: '手机号', width: 100, align: 'right'},
        {field: 'email', title: '邮箱', width: 100, align: 'right'}
    ]];
    $("#" + userListId).datagrid({
        url: ctx + "/user/list",
        frozenColumns : [[{
            field : 'checkbox',
            checkbox : true,
            hidden:true
        }]],
        columns: columns,
        singleSelect: true,
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        border: false,
        toolbar: '#userToolBar',
        onBeforeLoad: initSearchBox
    });
}

function initSearchBox() {
    $('#userSearch').searchbox({
        searcher: function (value, name) {
            alert(value + "," + name)
        },
        menu: '#userSearchMenu',
        prompt: '请输入值',
        height: 32
    });
}

function toDeleteUserFn() {
    var userListId = "userList";
    var obj=$("#" + userListId);
    //显示多选框
    obj.datagrid('showColumn', 'checkbox');
    //设置可多选
    obj.datagrid("options").singleSelect = false;
    //调整由于显示多选框导致的滚动条
    obj.datagrid("resize");
}