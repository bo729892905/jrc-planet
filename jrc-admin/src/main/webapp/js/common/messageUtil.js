/**
 * Created by rxb on 2016/5/26.
 */
var MessageUtil = {
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
};