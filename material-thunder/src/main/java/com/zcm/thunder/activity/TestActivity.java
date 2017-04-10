package com.zcm.thunder.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zcm.support.mvp.IPresenter;
import com.zcm.support.webview.WebViewActivity;
import com.zcm.support.widget.ActionSheetDialog;
import com.zcm.thunder.HomeWatcherReceiver;
import com.zcm.thunder.R;
import com.zcm.thunder.adapter.FuncListAdapter;
import com.zcm.thunder.model.TestItem;
import com.zcm.thunder.presenter.TestPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ZCM on 17-2-28 下午4:01.
 */

public class TestActivity extends THBaseActivity {

    private HomeWatcherReceiver receiver=null;
    @Bind(R.id.function_list)
    RecyclerView function_list;
    private List<TestItem> item_names;
    private FuncListAdapter funcAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setActivityTitle(R.string.app_name);
        setSwipeBackEnable(false);
        bt_title.getLeftTextView().setVisibility(View.GONE);
        addItem();
        setView();
        setReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected IPresenter getPresenter() {
        return new TestPresenter();
    }
    private void setView(){
        funcAdapter=new FuncListAdapter(item_names,this);
        function_list.setAdapter(funcAdapter);
        function_list.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setReceiver(){
        receiver=new HomeWatcherReceiver();
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        //添加过滤为了能监听POWER的点击
        homeFilter.addAction(Intent.ACTION_SCREEN_OFF);
        homeFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver,homeFilter);
    }
    public void addItem(){
        item_names=new ArrayList<>();
        item_names.add(new TestItem("跳转浏览器"){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                startActivity(new Intent(TestActivity.this, WebViewActivity.class));
            }
        });
        item_names.add(new TestItem("showSheetDialog"){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                new ActionSheetDialog(TestActivity.this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem(
                                "保存到相册",
                                ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                    }
                                }).show();
            }
        });
        item_names.add(new TestItem("BaseAdapterHelper用法"){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                startActivity(new Intent(TestActivity.this,BaseAdapterAct.class));
            }
        });
    }
}
