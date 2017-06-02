package com.zcm.thunder.splash;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;
import com.zcm.thunder.test.TestActivity;

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
        bt_title.setVisibility(View.GONE);
        handler=new Handler();
        mPresenter.getPic();
    }

    @Override
    protected LaunchPresenter getPresenter() {
        return new LaunchPresenter(this);
    }

    @Override
    public void getPicBean(PicBean bean) {
        Log.d(TAG, "getPicBean: "+bean.getNewslist().get(0).getPicUrl());
        Glide.with(this).load(bean.getNewslist().get(0).getPicUrl()).thumbnail(0.7f).into(img_splash) ;
        tv_splash.setText(bean.getNewslist().get(0).getTitle());
        gotoMain();
    }

    @Override
    public void showError(String errormsg) {
        showToast(errormsg);
    }
    private void gotoMain(){
        handler.postDelayed(() -> {startActivity(new Intent(LaunchActivity.this,TestActivity.class));
            finish();},5000);
    }
}
