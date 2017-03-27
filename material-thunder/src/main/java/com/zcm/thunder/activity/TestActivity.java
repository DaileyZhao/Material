package com.zcm.thunder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.IPresenter;
import com.zcm.support.utils.DisplayUtils;
import com.zcm.support.utils.SystemUtils;
import com.zcm.support.utils.TimeUtils;
import com.zcm.thunder.R;
import com.zcm.thunder.fragment.TestDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZCM on 17-2-28 下午4:01.
 */

public class TestActivity extends BaseActivity {

    @Bind(R.id.imgbtn_test)
    ImageButton imgbtn_test;
    @Bind(R.id.tv_time)
    TextView tv_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setActivityTitle(R.string.app_name);
        tv_time.setText(SystemUtils.isForeground()+"");
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
