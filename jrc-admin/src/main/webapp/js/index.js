$(function() {
    initLeftMenuTree();
});

/**
 * 初始化菜单树
 */
function initLeftMenuTree() {
    $("#leftMenuTree").tree({
        url: "json/leftMenuTree.json",
        animate:true,
        onLoadSuccess:function(node) {
            var roots=$("#leftMenuTree").tree("getRoots");
            for(var i=0;i<roots.length;i++) {
                var rootTarget=roots[i].target;
                $(rootTarget).addClass("root-tree-node");
            }
        },
        onBeforeSelect: function (node) {
            var isLeaf=$("#leftMenuTree").tree('isLeaf', node.target);
            if(!isLeaf) {
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