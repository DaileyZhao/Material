package com.zcm.support.webview;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by zcm on 17-3-30.
 */

public class BaseWebChromeClient extends WebChromeClient {
    private OpenFileChooserCallBack mOpenFileChooserCallBack;
    private SetTitleBarCallBack mSetTitleBarCallBack;
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (!TextUtils.isEmpty(title))
            mSetTitleBarCallBack.setTitleBarCallBack(title);
    }

    public BaseWebChromeClient(SetTitleBarCallBack setTitleBarCallBack, OpenFileChooserCallBack openFileChooserCallBack) {
        mOpenFileChooserCallBack = openFileChooserCallBack;
        mSetTitleBarCallBack = setTitleBarCallBack;
    }
    // For Android > 5.0
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, WebChromeClient.FileChooserParams
            fileChooserParams) {
        mOpenFileChooserCallBack.openFileChooserCallBackLOLLIPOP(uploadMsg, "");
        return true;
    }
    //For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);
    }

    // For Android < 3.0,未涉及
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    // For Android  > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    //选择图片接口。
    public interface OpenFileChooserCallBack {
        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);
        void openFileChooserCallBackLOLLIPOP(ValueCallback<Uri[]> uploadMsg, String acceptType);
    }

    //设置标题接口
    public interface SetTitleBarCallBack {
        void setTitleBarCallBack(String title);
    }
}
