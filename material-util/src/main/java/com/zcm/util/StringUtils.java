package com.zcm.util;

import android.content.Context;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zcm on 17-3-21.
 */

public class StringUtils {
    private static Context context= BaseApplication.getAppContext();
    /**
     * @param str md5加密
     * @return
     */
    public static String md5s(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        StringBuffer hexString = new StringBuffer();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            int length = hash.length;
            for (int i = 0; i < length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }
    /**
     * 是否是汉字
     *
     * @return
     */
    public static boolean isChineseCharacter(String value) {
        if (!TextUtils.isEmpty(value)) {
            return value.matches("[\\u4e00-\\u9fa5]");
        } else {
            return false;
        }

    }
    /**
     * 返回字符串的长度 ,一个汉字占两个字符,两个字母占两个字符
     *
     * @return
     */
    public static float getStringLength(String value) {
        float count = 0;
        if (!TextUtils.isEmpty(value)) {
            for (int i = 0; i < value.length(); i++) {
                if (isChineseCharacter(value.substring(i, i + 2))) {
                    count = count++;
                } else {
                    count = (float) (count + 1);
                }
            }
            return count;
        } else {
            return 0;
        }
    }
    public static String concat(Object... objects) {

        String str = new String();
        if (objects != null) {
            for (Object object : objects) {
                if (object != null) {
                    try {
                        str = str.concat(object.toString());
                    } catch (Exception e) {
                    }
                }
            }
        }
        return str;
    }
    /**
     * 获取string
     *
     * @param
     * @return
     */
    public static String getStrValue(int id) {
        try {
            return context.getString(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
