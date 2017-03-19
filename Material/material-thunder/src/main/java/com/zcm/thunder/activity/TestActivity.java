package com.zcm.thunder.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.zcm.thunder.R;
import com.zcm.thunder.fragment.TestDialog;
import com.zcm.ui.base.BaseActivity;
import com.zcm.ui.mvp.IPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZCM on 17-2-28 下午4:01.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.imgbtn_test)
    ImageButton imgbtn_test;

    @Override
    protected int setViewById() {
        return R.layout.activity_test;
    }

    @Override
    protected void initData() {
        setSwipeBackEnable(true);
        //startActivity(new Intent(this,Test2Activity.class));
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
    @OnClick(R.id.imgbtn_test)
    public void onClick(View view){
        new TestDialog().show(getSupportFragmentManager(),"imagebutton");
    }
}
