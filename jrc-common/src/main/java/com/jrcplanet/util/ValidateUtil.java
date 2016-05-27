package com.jrcplanet.util;

/**
 * 验证工具类
 * Created by rxb on 2016/5/27.
 */
public class ValidateUtil {
    public static boolean isEmpty(Object source){
        return source == null || source.toString().trim().length() == 0 || source.toString().trim().equalsIgnoreCase("null");
    }
}
