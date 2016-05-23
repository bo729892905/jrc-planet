$(function () {
    initLeftMenuTree();
    initTabs();
});

var mainTableId = "mainTabs";

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
        onSelect: function (node) {
            var opt = {
                title: node.text,
                href: node.attributes.url
            };
            addTabs(mainTableId, opt);
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

function addTabs(id, opt) {
    var obj = $("#" + id);

    var tab = obj.tabs("getTab", opt.title);
    if (tab) {//如果已存在则直接选中
        obj.tabs("select", opt.title);
    } else {
        obj.tabs("add", {
            title: opt.title,
            href: ctx + "/" + opt.href,
            selected: true,
            closable: true,
            border:false
        });
    }
}