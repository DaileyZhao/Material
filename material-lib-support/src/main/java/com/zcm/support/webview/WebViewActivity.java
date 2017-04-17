package com.zcm.support.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zcm.support.R;
import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.mvp.IPresenter;
import com.zcm.support.picview.PicShowActivity;

import java.util.ArrayList;

/**
 * Created by zcm on 17-3-30.
 * 查看图片的源码地址
 *  git@github.com:freecats/WebImageViewer.git
 */

public class WebViewActivity extends BaseActivity {
    private String url="http://www.recyclerview.org/";
    private ArrayList<String> mLists = new ArrayList<>();
    private WebView webView;
    private boolean isLOLLIPOP = false;
    private CommonWebViewClient commonClient=new CommonWebViewClient();
    private CommonWebChromeClient commonChromeClient=new CommonWebChromeClient();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView= (WebView) findViewById(R.id.webview);
        initwebView();
        webView.loadUrl(url);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
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
            settings.setDefaultTextEncodingName("UTF-8");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            isLOLLIPOP = true;
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        try {
            // 适应内容
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            // 设置可以支持缩放
            webView.getSettings().setSupportZoom(true);
            // 设置是否出现缩放工具
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.setVerticalScrollBarEnabled(true);
            //启用数据库
            webView.getSettings().setDatabaseEnabled(true);
            //启用地理定位
            webView.getSettings().setGeolocationEnabled(true);
            //设置定位的数据库路径
            String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
            webView.addJavascriptInterface(new WebViewActivity.JavascriptInterface(), "imageListener");
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
        webView.setWebChromeClient(commonChromeClient);
        webView.setWebViewClient(commonClient);
    }
    private void addImageListener() {
        //in some case, src of an img tag might be base64 string but no an url
        //we can get image url by data-src if exists
        if (null != webView)
            webView.loadUrl("javascript:(function(){ "
                    + " var objs = document.getElementsByTagName(\"img\"); "
                    + " for(var i=0;i<objs.length;i++)  "
                    + " {"
                    + "     window.imageListener.addImage(objs[i].src, objs[i].dataset.src); "
                    + "     objs[i].onclick=function()  "
                    + "    {  "
                    + "      window.imageListener.openImage(this.src, this.dataset.src);  "
                    + "     }  "
                    + " } "
                    + " })()");
    }
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public class JavascriptInterface {

        @android.webkit.JavascriptInterface
        public void addImage(String src, String dataSrc) {
            if (null != mLists) {
                String url = null;
                if (!TextUtils.isEmpty(dataSrc) && (dataSrc.startsWith("http:") || dataSrc.startsWith("https:"))) {
                    url = dataSrc;
                } else if (!TextUtils.isEmpty(src) && (src.startsWith("http:") || src.startsWith("https:"))) {
                    url = src;
                }

                if (!TextUtils.isEmpty(url) &&
                        (null != WebViewActivity.this.url && !WebViewActivity.this.url.contains(url)))
                    mLists.add(url);
            }

        }

        @android.webkit.JavascriptInterface
        public void openImage(String url, String dataSrc) {
            if (null != mLists && null != url && (mLists.contains(url) || mLists.contains(dataSrc))) {
                int position = -1 == mLists.indexOf(url) ? mLists.indexOf(dataSrc) : mLists.indexOf(url);
                if (-1 == position) return;
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(PicShowActivity.KEY_URLS, mLists);
                bundle.putInt(PicShowActivity.KEY_INDEX, position);
                Intent intent = new Intent(WebViewActivity.this, PicShowActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
    class CommonWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            final View process = findViewById(R.id.eventIconProgressBar);
            process.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            final View process = findViewById(R.id.eventIconProgressBar);
            process.setVisibility(View.GONE);
            super.onPageFinished(view, url);
            mLists.clear();
            addImageListener();
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
    }
    class CommonWebChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            View process = findViewById(R.id.eventIconProgressBar);
            if (newProgress == 100) {
                process.setVisibility(View.GONE);
            } else {
                process.setVisibility(View.VISIBLE);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            setActivityTitle(title);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url,
                                           boolean precomposed) {
            // TODO Auto-generated method stub
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog,
                                      boolean isUserGesture, Message resultMsg) {
            // TODO Auto-generated method stub
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin,true,false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }
}
