package com.zcm.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;


/**
 * Created by zcm on 17-3-14.
 */

public class DisplayUtils {
    private static Context context= BaseApplication.getAppContext();
    /**
     * dp2px
     */
    public static int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px2dp
     */
    public static int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     *根据设备信息获取当前分辨率下指定单位对应的像素大小；
     * px,dip,sp -> px
     */
    public float getRawSize(int unit, float size) {
        Resources r;
        if (context == null){
            r = Resources.getSystem();
        }else{
            r = context.getResources();
        }
        return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
    }
    public static int getWindowWidth() {
        int width;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        return width;
    }

    public static int getWindowHeight() {
        int height;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        height = metrics.heightPixels;
        return height;
    }
    /**
     * px转换sp
     *
     * @param pxValue
     * px数值
     * @return sp数值
     */
    private static DisplayMetrics mDisplayMetrics;

    public static int px2sp(float pxValue) {
        mDisplayMetrics = getDisplayMetrics();
        return (int) (pxValue / mDisplayMetrics.scaledDensity + 0.5f);
    }
    /**
     * 获取系统显示材质
     ***/
    public static DisplayMetrics getDisplayMetrics() {
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics DisplayMetrics = new DisplayMetrics();
        windowMgr.getDefaultDisplay().getMetrics(DisplayMetrics);
        return DisplayMetrics;
    }
}
