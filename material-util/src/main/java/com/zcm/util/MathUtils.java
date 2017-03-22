package com.zcm.util;

/**
 * Created by zcm on 17-3-14.
 */

public class MathUtils {
    public static Float max(Float... nums) {

        Float result = null;
        if (nums.length > 0) {
            result = nums[0];
            for (Float num : nums) {
                if (num > result) {
                    result = num;
                }
            }
        }
        return result;
    }

    public static Float min(Float... nums) {

        Float result = null;
        if (nums.length > 0) {
            result = nums[0];
            for (Float num : nums) {
                if (num < result) {
                    result = num;
                }
            }
        }
        return result;
    }

    public static Float sum(Float... nums) {

        Float result = 0f;
        for (Float num : nums) {
            result += num;
        }
        return result;
    }
    public static String getIntgerNumberStr(float number) {
        return getIntgerNumberStr(number, 2);

    }

    /**
     * 沒有小數時不显示转为整数, 否则显示全部
     *
     * @param
     * @return
     */
    public static String getIntgerNumberStr(float number, int length) {
        String result;
        // 有小数
        if (Math.abs(number - (int) number) > 0) {
            result = toString(String.format("%." + length + "f", number));
        } else {
            result = toString((int) number);
        }
        return result;
    }
    public static String toString(Object object) {
        String result = "错误：null";
        if (object != null) {
            result = object.toString();
        }
        return result;
    }
}
