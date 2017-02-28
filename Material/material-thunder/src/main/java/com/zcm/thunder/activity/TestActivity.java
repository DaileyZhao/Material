package com.zcm.thunder.activity;

import com.zcm.thunder.R;
import com.zcm.thunder.base.BaseActivity;
import com.zcm.thunder.mvp.IPresenter;

/**
 * Created by ZCM on 17-2-28 下午4:01.
 */

public class TestActivity extends BaseActivity {

    @Override
    protected int setViewById() {
        return R.layout.activity_test;
    }

    @Override
    protected void initData() {
        setSwipeBackEnable(true);
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
