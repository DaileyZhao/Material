package com.zcm.support.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.zcm.support.R;
import com.zcm.support.R2;
import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.BasePresenter;

import butterknife.BindView;

/**
 * Created by zcm on 17-4-18.
 */

public class BrowserActivity extends BaseActivity {
    @BindView(R2.id.layout_browser)
    BrowserLayout mBrowserLayout;
    private String url="https://www.baidu.com";
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        intent=getIntent();
        if (intent!=null){
            if (intent.getData() != null) {
                url = intent.getData().toString();
            }
        }
        //mBrowserLayout= (BrowserLayout) findViewById(R.id.layout_browser);
        bt_title.setRightImageResources(R.drawable.btn_refresh_pressed);
        bt_title.getRightTextView().setVisibility(View.GONE);
        initBrowser();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
    private void initBrowser(){
        mBrowserLayout.loadUrl(url);
        mBrowserLayout.setWebViewListener(new BrowserLayout.BrowserListener() {
            @Override
            public void setTitle(String title) {
                setActivityTitle(title);
            }

            @Override
            public TextView getRefreshBtn() {
                return bt_title.getRightTextView();
            }
        });
    }
    @Override
    protected void right_click() {
        super.right_click();
        mBrowserLayout.loadUrl(url);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&mBrowserLayout.canGoBack()){
            mBrowserLayout.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
