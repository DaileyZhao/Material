package com.zcm.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zcm.router.Router;
import com.zcm.router.rule.ActivityRule;

/**
 * Created by zcm on 17-4-1.
 */

public class DiscoveryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setTextSize(50);
        tv.setText("DISCOVERY!!!");
        setContentView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiscoveryActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                if (Router.isExistRouter(ActivityRule.ACTIVITY_SCHEME + "bbs.main")) {
                    Intent it = Router.invoke(DiscoveryActivity.this, ActivityRule.ACTIVITY_SCHEME + "bbs.main");
                    startActivity(it);
                }
            }
        });
    }
}
