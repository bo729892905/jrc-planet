var mainOpt = {
    mainTableId: "mainTabs",
    mainTabMenuId: "mainTabMenu",
    leftTreeId: "leftMenuTree",
    init: function () {
        mainOpt.initLeftMenuTree();
        mainOpt.initTabs();
        mainOpt.initTabMenu();
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
            border: false,
            onContextMenu: function (e, title, index) {
                e.preventDefault();
                var menuObj=$("#" + mainOpt.mainTabMenuId);
                menuObj.menu("show", {
                    left: e.pageX,
                    top: e.pageY
                });
                menuObj.menu('options').index = index;
            }
        });
    },
    initTabMenu:function() {
        var obj = $("#" + mainOpt.mainTabMenuId);
        obj.menu({
            onClick:function(item) {
                var index=obj.menu('options').index;
                if(index) {
                    var itemId = item.id;
                    switch(itemId) {
                        case 'refresh':
                            EasyuiUtil.refreshTab(mainOpt.mainTableId,index);
                            break;
                        case 'closeCurrent':
                            EasyuiUtil.closeTab(mainOpt.mainTableId,index);
                            break;
                        case 'closeOther':
                            EasyuiUtil.closeOtherTab(mainOpt.mainTableId,index);
                            break;
                        case 'closeLeft':
                            EasyuiUtil.closeLeftTab(mainOpt.mainTableId,index);
                            break;
                        case 'closeRight':
                            EasyuiUtil.closeRightTab(mainOpt.mainTableId,index);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }
};

$(function () {
    mainOpt.init();
});