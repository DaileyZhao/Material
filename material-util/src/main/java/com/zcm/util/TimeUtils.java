package com.zcm.util;

/**
 * Created by zcm on 17-3-21.
 */

public class TimeUtils {
    public static String getTime(long time) {
        StringBuilder stringBuilder = new StringBuilder("");
        String format = "%02d";
        stringBuilder.append(String.format(format, time / 3600));
        stringBuilder.append(":");
        stringBuilder.append(String.format(format, time % 3600 / 60));
        return stringBuilder.toString();
    }
}
