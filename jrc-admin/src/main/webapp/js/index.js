$(function () {
    initLeftMenuTree();
    initUserTable();
});

/**
 * 初始化菜单树
 */
function initLeftMenuTree() {
    $("#leftMenuTree").tree({
        url: ctx + "/menu/tree",
        animate: true,
        onLoadSuccess: function (node) {
            var roots = $("#leftMenuTree").tree("getRoots");
            for (var i = 0; i < roots.length; i++) {
                var rootTarget = roots[i].target;
                $(rootTarget).addClass("root-tree-node");
            }
        },
        onBeforeSelect: function (node) {
            var isLeaf = $("#leftMenuTree").tree('isLeaf', node.target);
            if (!isLeaf) {
                $("#leftMenuTree").tree('toggle', node.target);
                return false;
            }
        },
        onCollapse: function (node) {
            $(node.target).addClass("root-tree-node-selected");
        },
        onExpand: function (node) {
            $(node.target).removeClass("root-tree-node-selected");
        }
    });
}

function initUserTable() {
    var columns = [[
        {field: 'username', title: '用户名', width: 100},
        {field: 'realName', title: '姓名', width: 100},
        {field: 'gender', title: '性别', width: 100, align: 'right'},
        {field: 'mobilePhone', title: '手机号', width: 100, align: 'right'},
        {field: 'email', title: '邮箱', width: 100, align: 'right'}
    ]];
    EasyuiUtil.initDatagrid("userList", columns, ctx + "/user/list");
}