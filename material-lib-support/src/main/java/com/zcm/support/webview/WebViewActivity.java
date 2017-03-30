package com.zcm.support.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zcm.support.R;
import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.IPresenter;

/**
 * Created by zcm on 17-3-30.
 */

public class WebViewActivity extends BaseActivity {
    private WebView webView;
    private boolean isLOLLIPOP = false;
    String mUrl = "";
    String mCurrentUrl = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView= (WebView) findViewById(R.id.webview);
        initwebView();
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void initwebView(){
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            settings.setPluginState(WebSettings.PluginState.ON);
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setGeolocationEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setUseWideViewPort(true);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            isLOLLIPOP = true;
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        try {
            webView.setVerticalScrollBarEnabled(true);
            //启用数据库
            webView.getSettings().setDatabaseEnabled(true);
            //启用地理定位
            webView.getSettings().setGeolocationEnabled(true);
            //设置定位的数据库路径
            String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
            webView.getSettings().setGeolocationDatabasePath(dir);
            //最重要的方法，一定要设置，这就是出不来的主要原因
            webView.getSettings().setDomStorageEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 打开浏览器下载功能
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        webView.setWebChromeClient(new BaseWebChromeClient(new BaseWebChromeClient.SetTitleBarCallBack() {
            @Override
            public void setTitleBarCallBack(String title) {
                if (mCurrentUrl.startsWith("http"))
                    bt_title.setTitleText(title);
            }
        }, new BaseWebChromeClient.OpenFileChooserCallBack() {
            @Override
            public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {

            }

            @Override
            public void openFileChooserCallBackLOLLIPOP(ValueCallback<Uri[]> uploadMsg, String acceptType) {

            }
        }) {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mCurrentUrl = url;
                final View process = findViewById(R.id.eventIconProgressBar);
                process.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                final View process = findViewById(R.id.eventIconProgressBar);
                process.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
                // super.onReceivedSslError(view, handler, error);
                // 接受所有网站的证书，忽略SSL错误，执行访问网页
                handler.proceed();
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });
    }
    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
