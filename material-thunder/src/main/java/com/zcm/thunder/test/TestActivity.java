package com.zcm.thunder.test;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zcm.router.Router;
import com.zcm.support.webview.BrowserActivity;
import com.zcm.support.widget.ActionSheetDialog;
import com.zcm.thunder.HomeWatcherReceiver;
import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;
import com.zcm.thunder.recyclerview.BaseAdapterAct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ZCM on 17-2-28 下午4:01.
 */

public class TestActivity extends THBaseActivity {

    private HomeWatcherReceiver receiver=null;
    @BindView(R.id.function_list)
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
    protected TestPresenter getPresenter() {
        return new TestPresenter(this);
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
                startActivity(new Intent(TestActivity.this, BrowserActivity.class));
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
        item_names.add(new TestItem("intent测试"){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Hi,我正在学习RxJava,推荐你下载这个app一起学习吧 到应用商店或者https://github.com/jiang111/RxJavaApp/releases即可下载");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "分享到"));
            }
        });
        item_names.add(new TestItem("路由测试"){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Router.open("http://www.baidu.com");
                //Router.open(TestActivity.this,"activity://bbs/");
            }
        });
    }
}
