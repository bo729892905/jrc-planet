$(function() {
    initLeftMenuTree();
});

function initLeftMenuTree() {
    $("#leftMenuTree").tree({
        url: "json/leftMenuTree.json"
    });
}