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
        for (var i = 0, j = row.length; i < j; i++) {
            var rowIndex = obj.datagrid('getRowIndex', row[i]);
            obj.datagrid('deleteRow', rowIndex);
        }
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
            width: opt.width || 600,
            height: opt.height || 400,
            closable: true,
            modal: true,
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
    },

    /**
     * 弹出框
     * @param obj
     * title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
     */
    alert: function (obj) {
        if (typeof obj == "string") {
            obj = {"title": "信息提醒", "msgString": obj, "msgType": "info"};
        }
        var title = obj.title || "信息提醒";
        var msgString = obj.msgString || "信息提醒";
        var msgType = obj.msgType || "info";
        $.messager.alert(title, msgString, msgType);
    },

    /**
     * 提示信息
     * @param msg
     * @param fn
     */
    confirm: function (msg, fn) {
        $.messager.confirm("提示信息", msg, fn)
    },

    msgshow: function (obj) {
        if (typeof obj == 'string') {
            obj = {w: 250, h: 200, msg: obj};
        }
        var w = obj.w || 250, h = obj.h || 200;
        $.messager.show({
            title: '提示',
            msg: obj.msg,
            width: w,
            height: h,
            timeout: 2500,
            showType: 'show'
        });
    },

    msgslide: function (obj) {
        if (typeof obj == 'string') {
            obj = {w: 250, h: 200, msg: obj};
        }
        var w = obj.w || 250, h = obj.h || 200;
        $.messager.show({
            title: '提示',
            msg: obj.msg,
            width: w,
            height: h,
            timeout: 3000,
            showType: 'slide'
        });
    },

    msgfade: function (obj) {
        if (typeof obj == 'string') {
            obj = {w: 250, h: 200, msg: obj};
        }
        var w = obj.w || 250, h = obj.h || 200, t = obj.t || 3000;
        $.messager.show({
            title: '提示',
            msg: obj.msg,
            timeout: t,
            width: w,
            height: h,
            showType: 'fade'
        });
    }
}