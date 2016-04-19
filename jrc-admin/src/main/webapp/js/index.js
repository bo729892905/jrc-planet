$(function() {
    initLeftMenuTree();
});

/**
 * 初始化菜单树
 */
function initLeftMenuTree() {
    $("#leftMenuTree").tree({
        url: "json/leftMenuTree.json",
        animate:true
    });
}