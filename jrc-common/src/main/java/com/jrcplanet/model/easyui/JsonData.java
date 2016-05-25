package com.jrcplanet.model.easyui;

/**
 * json数据封装类
 * Created by rxb on 2016/5/13.
 */
public class JsonData<T> {
    /**
     * 编码
     */
    private String code;
    /**
     * 结果
     */
    private boolean result;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public static <T> JsonData<T> createSuccessData(T data) {
        JsonData<T> jsonData = new JsonData<>();
        jsonData.setResult(true);
        jsonData.setData(data);
        return jsonData;
    }

    public static JsonData createSuccessData() {
        JsonData jsonData = new JsonData<>();
        jsonData.setResult(true);
        return jsonData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
