package com.zcm.thunder.activity;

import com.zcm.thunder.base.BaseActivity;
import com.zcm.thunder.mvp.IPresenter;

/**
 * Created by zhaocunming on 2017/3/5.
 */

public class Test2Activity extends BaseActivity {
    @Override
    protected int setViewById() {
        return 0;
    }

    @Override
    protected void initData() {
        finish();
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
