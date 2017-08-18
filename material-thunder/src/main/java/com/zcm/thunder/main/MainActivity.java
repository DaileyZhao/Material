package com.zcm.thunder.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.widget.FrameLayout;

import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;

import butterknife.BindView;

/**
 * Created by zcm on 2017/7/10.
 */

public class MainActivity extends THBaseActivity<MainView,MainPresenter> {
    @BindView(R.id.fl_container)
    FrameLayout fl_container;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showActivityTitle(false);
    }

    @Override
    protected MainPresenter createPresenter() {
        return null;
    }
}
