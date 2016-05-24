var EasyuiUtil = {
    /**
     * 初始化dataGrid
     * @param id
     * @param opt
     */
    initDatagrid: function (id, opt) {
        $("#" + id).datagrid({
            url: opt.url,
            frozenColumns: [[{
                field: 'checkbox',
                checkbox: true,
                hidden: opt.showCheckBox | true
            }]],
            columns: opt.columns,
            singleSelect: true,
            fit: true,
            fitColumns: true,
            rownumbers: true,
            pagination: true,
            border: false,
            toolbar: opt.toolbar,
            onBeforeLoad: opt.onOpen
        });
    },
    /**
     * 动态添加新Tab
     * @param id
     * @param opt
     */
    addTab: function (id, opt) {
        var obj = $("#" + id);

        var tabExists = obj.tabs("exists", opt.title);
        if (tabExists) {//如果已存在则直接选中
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
    },
    /**
     * 初始化对话框
     * @param id
     * @param opt
     */
    initDialog: function (id, opt) {
        var obj = $('#' + id);
        obj.dialog({
            title: opt.title,
            width: opt.width | 600,
            height: opt.height | 400,
            closable: true,
            modal: true,
            buttons: [{
                id: 'ok',
                text: '确定',
                handler: opt.okFn
            }, {
                id: 'cancel',
                text: '取消',
                handler: opt.cancelFn | function () {
                    obj.dialog('close');
                }
            }
            ]
        });
    }
}