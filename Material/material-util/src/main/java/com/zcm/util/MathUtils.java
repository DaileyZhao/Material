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
}
