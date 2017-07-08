package com.zcm.bbs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zcm on 17-4-1.
 */

public class BBSActivity extends BaseActivity {
    @BindView(R2.id.demo_text)
    TextView demo_text;
    @BindView(R2.id.circle_image)
    CircleImageView circleImageView;
    String strExt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);
        strExt = getIntent().getStringExtra("name");
        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.hugh));
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick(R2.id.demo_text)
    public void onClick() {
        Toast.makeText(BBSActivity.this, strExt, Toast.LENGTH_SHORT).show();
    }
}
