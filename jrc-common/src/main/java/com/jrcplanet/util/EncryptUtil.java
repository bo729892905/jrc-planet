package com.jrcplanet.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具
 * Created by rxb on 2016/1/27.
 */
public class EncryptUtil {

    /**
     * md5加密方式
     * @param source
     * @return
     */
    public static String encryptMD5(String source) {
        if (source == null) {
            source = "";
        }
        Md5Hash md5 = new Md5Hash(source);
        return md5.toString();
    }
}
