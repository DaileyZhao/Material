package com.zcm.thunder.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.ImageView;

import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by zcm on 2017/7/10.
 */

public class MainActivity extends THBaseActivity<MainView,MainPresenter> {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.colormatrix_demo)
    ImageView colormatrix;
    private Bitmap bitmap;
    private float[] mColorMatrix = new float[20];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showActivityTitle(false);
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    break;
                case R.id.navigation_world:
                    break;
                case R.id.navigation_settings:
                    break;
            }
            return true;
        });
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.demo);
        colormatrix.setImageBitmap(bitmap);
    }

    @Override
    protected MainPresenter createPresenter() {
        return null;
    }
    // 将矩阵值设置到图像
    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(
                bitmap.getWidth(),
                bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix =
                new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        colormatrix.setImageBitmap(bmp);
    }
}
