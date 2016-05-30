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
                hidden: opt.showCheckBox || true
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
     * 删除选中行
     * @param id
     */
    deleteRow: function (id) {
        var obj = $('#' + id);
        var row = obj.datagrid('getSelections');
        if (row.length == 0) {
            dialogUtil.alert("请选择要删除的数据！");
            return;
        }
        row.forEach(function(e) {
            var rowIndex = obj.datagrid('getRowIndex', e);
            obj.datagrid('deleteRow', rowIndex);
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
                border: false,
            });
        }
    },

    /**
     * 刷新标签页
     * @param id
     * @param index
     */
    refreshTab: function (id, index) {
        var obj = $("#" + id);
        obj.tabs('getTab', index).panel('refresh');
    },

    /**
     * 关闭标签页
     * @param id
     * @param index
     */
    closeTab: function (id, index) {
        var obj = $("#" + id);
        obj.tabs('close', index);
    },

    /**
     * 关闭其他标签页
     * @param id
     * @param index
     */
    closeOtherTab: function (id, index) {
        EasyuiUtil.closeRightTab(id, index);
        EasyuiUtil.closeLeftTab(id, index);
    },

    /**
     * 关闭左侧标签页
     * @param id
     * @param index
     */
    closeLeftTab: function (id, index) {
        var obj = $("#" + id);
        for (var i = index - 1; i > 0; i--) {
            if (i != index) {
                obj.tabs('close', i);
            }
        }
    },

    /**
     * 关闭右侧标签页
     * @param id
     * @param index
     */
    closeRightTab: function (id, index) {
        var obj = $("#" + id);
        var tabs = obj.tabs("tabs");
        for (var i = tabs.length - 1; i > index; i--) {
            obj.tabs('close', i);
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
            width: opt.width || 600,
            height: opt.height || 400,
            href: opt.href || "",
            closable: true,
            modal: true,
            closed: true,
            buttons: [{
                id: 'ok',
                text: '确定',
                handler: opt.okFn
            }, {
                id: 'cancel',
                text: '取消',
                handler: opt.cancelFn || function () {
                    obj.dialog('close');
                }
            }
            ]
        });
    }
};