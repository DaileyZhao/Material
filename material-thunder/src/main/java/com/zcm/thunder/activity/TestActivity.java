package com.zcm.thunder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcm.support.mvp.IPresenter;
import com.zcm.support.webview.WebViewActivity;
import com.zcm.thunder.R;
import com.zcm.thunder.fragment.TestDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZCM on 17-2-28 下午4:01.
 */

public class TestActivity extends THBaseActivity {

    @Bind(R.id.imgbtn_test)
    ImageButton imgbtn_test;
    @Bind(R.id.img_launch)
    ImageView img_launch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setActivityTitle(R.string.app_name);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490769498540&di=14625f84ff992a47b0af66718560b738&imgtype=0&src=http%3A%2F%2Fimgb.mumayi.com%2Fandroid%2Fwallpaper%2F2012%2F01%2F21%2Fsl_600_2012012101535755738287.jpg").into(img_launch);
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
    @OnClick(R.id.imgbtn_test)
    public void onClick(View view){
        new TestDialog().show(getSupportFragmentManager(),"imagebutton");
        startActivity(new Intent(TestActivity.this, WebViewActivity.class));
    }
}
