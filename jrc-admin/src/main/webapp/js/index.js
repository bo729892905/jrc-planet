$(function () {
    initLeftMenuTree();
    initTabs();
});

var mainTableId = "mainTabs";

/**
 * 初始化菜单树
 */
function initLeftMenuTree() {
    var obj=$("#leftMenuTree");
    obj.tree({
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
            var isLeaf = obj.tree('isLeaf', node.target);
            if (!isLeaf) {
                $("#leftMenuTree").tree('toggle', node.target);
                return false;
            }
        },
        onSelect: function (node) {
            var opt = {
                title: node.text,
                id: node.enName,
                href: node.attributes.url
            };
            EasyuiUtil.addTab(mainTableId, opt);
        },
        onCollapse: function (node) {
            $(node.target).addClass("root-tree-node-selected");
        },
        onExpand: function (node) {
            $(node.target).removeClass("root-tree-node-selected");
        }
    });
}

function initTabs() {
    var obj = $("#" + mainTableId);
    obj.tabs({
        fit: true,
        border: false
    });
}