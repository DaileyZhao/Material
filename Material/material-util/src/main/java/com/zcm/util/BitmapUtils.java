package com.zcm.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextPaint;


/**
 * Created by zcm on 17-3-17.
 */

public class BitmapUtils {
    private static Context context= XApplication.getAppContext();
    /**
     * 在左下角绘制logo
     *
     * @param canvas
     */
    public static void drawLogo(Canvas canvas, String type) {
        float heightParent = canvas.getHeight();

        float heightWrap = DisplayUtils.dip2px(40);
        float heightLogo = DisplayUtils.dip2px(20);
        float heightText = DisplayUtils.dip2px(12);

        float leftMargin = DisplayUtils.dip2px(10);

        canvas.save();
        canvas.translate(leftMargin, 0);
        // 绘制logo
        Bitmap logoBitmap = BitmapFactory.decodeResource(context.getResources(),android.R.layout.select_dialog_item);
        Matrix logoMatrix = new Matrix();
        Paint logoPaint = new Paint();
        logoPaint.setAntiAlias(true);
        float scale = heightLogo / (float) logoBitmap.getHeight();
        logoMatrix.postScale(scale, scale, 0, 0);
        logoMatrix.postTranslate(0, heightParent - heightWrap + (heightWrap - heightLogo) / 2);

        canvas.drawBitmap(logoBitmap, logoMatrix, logoPaint);

        // 绘制文字
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(heightText);
        String text = "  " + type + "  by zcm";
        canvas.drawText(text, heightLogo, heightParent - heightText, textPaint);
        canvas.restore();
    }
}
