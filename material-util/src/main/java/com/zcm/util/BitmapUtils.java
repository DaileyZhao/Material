package com.zcm.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


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
    /**
     * 回收view的bitmap
     *
     * @param view
     */
    public static void recycleViewBitmap(View view) {
        if (view != null) {
            Drawable drawable = view.getBackground();
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                view.setBackgroundDrawable(null);
                drawable.setCallback(null);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null) {
                    if (!bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                    bitmap = null;
                }
            }
        }
    }
    /**
     * 递归的回收view下的所有bitmap 建议运行在 Activity.onDestory()
     *
     * @param view
     */
    public static void recyleBitmapRecursively(final View view) {

        if (view != null) {
            view.setBackgroundDrawable(null);
        }
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(null);
        } else if (view instanceof ViewGroup) {
            int count = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < count; i++) {
                recyleBitmapRecursively(((ViewGroup) view).getChildAt(i));
            }
        }
    }
    /***
     * 指定大小的压缩
     *
     * @param path
     * @param limtMax
     * @return
     * @throws IOException
     */
    public static Bitmap revitionImageSize(String path, int limtMax) throws IOException {
        // 取得图片
        File file = new File(path);
        InputStream temp = new FileInputStream(file);
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 这个参数代表，不为bitmap分配内存空间，只记录一些该图片的信息（例如图片大小），说白了就是为了内存优化
        options.inJustDecodeBounds = true;
        // 通过创建图片的方式，取得options的内容（这里就是利用了java的地址传递来赋值）
        BitmapFactory.decodeStream(temp, null, options);
        // 关闭流
        temp.close();
        // 生成压缩的图片
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            // 这一步是根据要设置的大小，使宽和高都能满足
            if ((options.outWidth >> i <= limtMax) && (options.outHeight >> i <= limtMax)) {
                // 重新取得流，注意：这里一定要再次加载，不能二次使用之前的流！
                temp = new FileInputStream(file);
                // 这个参数表示 新生成的图片为原始图片的几分之一。
                options.inSampleSize = (int) Math.pow(2.0D, i);
                // 这里之前设置为了true，所以要改为false，否则就创建不出图片
                options.inJustDecodeBounds = false;
                options.inTargetDensity = 240;
                bitmap = BitmapFactory.decodeStream(temp, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }
    /**
     * 临时保存图片
     *
     * @param bitmap
     * @return 图片路径
     */
    public static String saveImageToAPP(Bitmap bitmap) {
        return saveImageToAPP(bitmap, System.currentTimeMillis()+".jpeg");
    }
    public static String saveImageToAPP(Bitmap bitmap, String picName) {
        String result = "";
        OutputStream out = null;
        try {
            if (bitmap != null) {
                String path = context.getApplicationContext().getFilesDir().getAbsolutePath();
                path = path + "/../.cache/";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                File cacheFile = new File(file, picName);
                cacheFile.deleteOnExit();
                out = new BufferedOutputStream(new FileOutputStream(cacheFile));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                result = cacheFile.getPath();
                // 文件关闭
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result;
    }
}
