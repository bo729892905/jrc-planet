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
                $(rootTarget).css("background","#22282e");
                $(rootTarget).css("margin-bottom","0.5px");
                $(rootTarget).mouseenter(function() {
                    $(this).css("background","#414d5c");
                }).mouseout(function(){
                    $(this).css("background","#22282e");
                });
            }
        },
        onBeforeSelect: function (node) {
            var isLeaf=$("#leftMenuTree").tree('isLeaf', node.target);
            if(!isLeaf) {
                $("#leftMenuTree").tree('toggle', node.target);
                return false;
            }
        }/*,
        onCollapse: function (node) {
            $(node.target).css("background","#37424f");
        }*/
    });
}