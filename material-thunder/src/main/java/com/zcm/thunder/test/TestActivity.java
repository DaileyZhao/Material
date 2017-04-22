package com.zcm.thunder.test;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zcm.support.webview.BrowserActivity;
import com.zcm.support.widget.ActionSheetDialog;
import com.zcm.thunder.HomeWatcherReceiver;
import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;
import com.zcm.thunder.recyclerview.BaseAdapterAct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ZCM on 17-2-28 下午4:01.
 */

public class TestActivity extends THBaseActivity {

    private HomeWatcherReceiver receiver=null;
    private static final int IMAGE = 1;
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Intent intent1 = new Intent("com.android.camera.action.CROP");
            intent1.setDataAndType(Uri.fromFile(new File(imagePath)), "image/*");
            intent1.putExtra("crop", "true");
           // intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));//
            intent1.putExtra("aspectX", 1);
            intent1.putExtra("aspectY", 1);
            intent1.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
            intent1.putExtra("outputX", 720);
            intent1.putExtra("outputY", 720);
            intent1.putExtra("scale", true);
            intent1.putExtra("scaleUpIfNeeded", true);
            intent1.putExtra("return-data", false);
            startActivityForResult(intent1, 0x222);
            showToast(imagePath);
            c.close();
        }
        if (requestCode==0x222&&resultCode==Activity.RESULT_OK&&data!=null){
            showToast(data.getData().toString());
        }
        if (requestCode==0x111&&resultCode==Activity.RESULT_OK){
            showToast(data.getStringExtra("change01"));
        }
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
                //openActivity("http://www.ctolib.com/AndRouter.html#articleHeader1");
                //Router.open(TestActivity.this,"http://www.ctolib.com/AndRouter.html#articleHeader1");
                openActivityForResult(0x111,"activity://discovery/%s/%d/birthday","hehehe",23);
            }
        });
        item_names.add(new TestItem("系统相册"){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
    }
}
