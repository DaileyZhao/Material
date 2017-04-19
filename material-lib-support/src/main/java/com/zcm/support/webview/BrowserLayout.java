package com.zcm.support.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zcm.support.R;
import com.zcm.support.picview.PicShowActivity;

import java.util.ArrayList;

/**
 * Created by zcm on 17-4-18.
 */

public class BrowserLayout extends LinearLayout {
    private Context mContext = null;
    private WebView mWebView = null;
    private ArrayList<String> mLists = new ArrayList<>();

    private CommonWebViewClient commonClient=new CommonWebViewClient();
    private CommonWebChromeClient commonChromeClient=new CommonWebChromeClient();

    private int mBarHeight = 5;
    private ProgressBar mProgressBar = null;

    private String mLoadUrl;
    private BrowserListener mListener;

    public BrowserLayout(Context context) {
        super(context);
        init(context);
    }

    public BrowserLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BrowserLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        mContext = context;
        setOrientation(VERTICAL);

        mProgressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.progress_horizontal, null);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        addView(mProgressBar, LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mBarHeight, getResources().getDisplayMetrics()));

        mWebView = new WebView(context);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        String dir = context.getDir("database", Context.MODE_PRIVATE).getPath();
        mWebView.getSettings().setGeolocationDatabasePath(dir);
        mWebView.addJavascriptInterface(new JavascriptInterface(), "imageListener");
        LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        addView(mWebView, lps);
        mWebView.setDownloadListener((url1, userAgent, contentDisposition, mimetype, contentLength) -> {
            Uri uri = Uri.parse(url1);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            try {
                // TODO: 17-4-18 这里如果在fragment里调用可能会有问题
                getContext().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        mWebView.setWebChromeClient(commonChromeClient);
        mWebView.setWebViewClient(commonClient);
    }
    public void loadUrl(String url) {
        mLoadUrl=url;
        mWebView.loadUrl(url);
    }

    public boolean canGoBack() {
        return null != mWebView ? mWebView.canGoBack() : false;
    }

    public boolean canGoForward() {
        return null != mWebView ? mWebView.canGoForward() : false;
    }

    public void goBack() {
        if (null != mWebView) {
            mWebView.goBack();
        }
    }

    public void goForward() {
        if (null != mWebView) {
            mWebView.goForward();
        }
    }
    public void setWebViewListener(BrowserListener listener){
        this.mListener=listener;
    }
    public WebView getWebView() {
        return mWebView != null ? mWebView : null;
    }
    private void addImageListener() {
        //in some case, src of an img tag might be base64 string but no an url
        //we can get image url by data-src if exists
        if (null != mWebView)
            mWebView.loadUrl("javascript:(function(){ "
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
                        (null != BrowserLayout.this.mLoadUrl && !BrowserLayout.this.mLoadUrl.contains(url)))
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
                // TODO: 17-4-18 这里考虑以后要换eventbus
                Intent intent = new Intent(mContext, PicShowActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        }
    }
    class CommonWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
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
    class CommonWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
                mListener.getRefreshBtn().setVisibility(VISIBLE);
            } else {
                mProgressBar.setProgress(newProgress);
                mProgressBar.setVisibility(VISIBLE);
                mListener.getRefreshBtn().setVisibility(GONE);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            mListener.setTitle(title);
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
    interface BrowserListener{
        void setTitle(String title);
        TextView getRefreshBtn();
    }
}
