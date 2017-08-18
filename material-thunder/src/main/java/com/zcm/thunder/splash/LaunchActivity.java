package com.zcm.thunder.splash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcm.support.imageselect.ImageLoaderManager;
import com.zcm.support.imageselect.ImageLoaderOptions;
import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;
import com.zcm.thunder.main.MainActivity;

import butterknife.BindView;

/**
 * Created by zcm on 17-4-18.
 */

public class LaunchActivity extends THBaseActivity<LaunchView,LaunchPresenter> implements LaunchView {
    @BindView(R.id.img_splash)
    ImageView img_splash;
    @BindView(R.id.tv_splash)
    TextView tv_splash;
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        showActivityTitle(false);
        handler=new Handler();
        mPresenter.getPic();
    }

    @Override
    protected LaunchPresenter createPresenter() {
        return new LaunchPresenter(this);
    }

    @Override
    public void getPicBean(PicBean bean) {
        Log.d(TAG, "getPicBean: "+bean.getNewslist().get(0).getPicUrl());
        ImageLoaderOptions options=new ImageLoaderOptions.Builder()
                .setCrossFade(true)
                .setSkipMemoryCache(false)
                .setPlaceHolder(com.zcm.support.R.drawable.error_picture)
                .setErrorDrawable(com.zcm.support.R.drawable.error_picture)
                .build();
        ImageLoaderManager.getSingleton().showImage(img_splash,bean.getNewslist().get(0).getPicUrl(),options);
        tv_splash.setText(bean.getNewslist().get(0).getTitle());
        gotoMain();
    }

    @Override
    public void showError(String errormsg) {
        showToast(errormsg);
    }
    private void gotoMain(){
        handler.postDelayed(() -> {
            img_splash.setDrawingCacheEnabled(true);
            Intent intent=new Intent();
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
            Log.e(TAG, "gotoMain: "+bitmap);
            Bundle bundle=new Bundle();
            bundle.putParcelable("bitmap",bitmap);
            intent.putExtras(bundle);
            intent.setClass(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
            finish();},5000);
    }
}
