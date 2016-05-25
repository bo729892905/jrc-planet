var mainOpt = {
    mainTableId: "mainTabs",
    leftTreeId: "leftMenuTree",
    init: function () {
        mainOpt.initLeftMenuTree();
        mainOpt.initTabs();
    },
    initLeftMenuTree: function () {
        /**
         * 初始化菜单树
         */
        var obj = $("#" + mainOpt.leftTreeId);
        obj.tree({
            url: ctx + "/menu/tree",
            animate: true,
            onLoadSuccess: function (node) {
                var roots = obj.tree("getRoots");
                for (var i = 0; i < roots.length; i++) {
                    var rootTarget = roots[i].target;
                    $(rootTarget).addClass("root-tree-node");
                }
            },
            onBeforeSelect: function (node) {
                var isLeaf = obj.tree('isLeaf', node.target);
                if (!isLeaf) {
                    obj.tree('toggle', node.target);
                    return false;
                }
            },
            onSelect: function (node) {
                var opt = {
                    title: node.text,
                    id: node.enName,
                    href: node.attributes.url
                };
                EasyuiUtil.addTab(mainOpt.mainTableId, opt);
            },
            onCollapse: function (node) {
                $(node.target).addClass("root-tree-node-selected");
            },
            onExpand: function (node) {
                $(node.target).removeClass("root-tree-node-selected");
            }
        });
    },
    initTabs: function () {
        var obj = $("#" + mainOpt.mainTableId);
        obj.tabs({
            fit: true,
            border: false
        });
    }
};

$(function () {
    mainOpt.init();
});