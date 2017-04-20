package com.zcm.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.BasePresenter;

import butterknife.BindView;

/**
 * Created by zcm on 17-4-1.
 */

public class DiscoveryActivity extends BaseActivity {
    @BindView(R2.id.tv_distest)
    TextView tv_distest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_discovery);
        tv_distest.setTextSize(50);
        tv_distest.setText("DISCOVERY!!!");
        tv_distest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiscoveryActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
