var EasyuiUtil = {
    /**
     * 初始化DataGrid
     * @param id
     * @param columns
     * @param url
     */
    initDatagrid: function (id, columns, url) {
        $("#" + id).datagrid({
            url: url,
            columns: columns,
            singleSelect: true,
            fit: true,
            fitColumns: true,
            rownumbers: true,
            pagination: true,
            border: false
        });
    },
    /**
     * 动态添加新Tab
     * @param id
     * @param opt
     */
    addTab: function (id, opt) {
        var obj = $("#" + id);

        var tab = obj.tabs("getTab", opt.title);
        if (tab) {//如果已存在则直接选中
            obj.tabs("select", opt.title);
        } else {
            obj.tabs("add", {
                id: opt.enName,
                title: opt.title,
                href: ctx + "/" + opt.href,
                selected: true,
                closable: true,
                border: false
            });
        }
    }
}