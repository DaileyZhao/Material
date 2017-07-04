package com.zcm.discovery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaMetadataCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.widget.rule.RulerWheel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zcm on 17-4-1.
 */

public class DiscoveryActivity extends BaseActivity {
    @BindView(R2.id.tv_distest)
    TextView tv_distest;
    @BindView(R2.id.rule_wheel)
    RulerWheel rule_wheel;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_discovery);
        intent=getIntent();
        tv_distest.setTextSize(50);
        tv_distest.setText(intent.getStringExtra("name")+intent.getIntExtra("age",0));
        tv_distest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiscoveryActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent();
                mIntent.putExtra("change01", "1000");
                setResult(Activity.RESULT_OK,mIntent);
                openActivity("activity://bbs/");
                finish();
            }
        });
        List<String> heightList=new ArrayList<>();
        for (int i=0;i<201;i++){
            heightList.add(i+"");
        }
        rule_wheel.setData(heightList);
        rule_wheel.setSelectedValue(30+"");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
