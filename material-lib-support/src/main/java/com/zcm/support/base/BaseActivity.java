package com.zcm.support.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zcm.router.Router;
import com.zcm.router.route.ActivityRoute;
import com.zcm.support.R;
import com.zcm.support.basetitle.BaseTitleView;
import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.mvp.IBaseView;
import com.zcm.support.swipebacklayout.SwipeBackActivityBase;
import com.zcm.support.swipebacklayout.SwipeBackActivityHelper;
import com.zcm.support.swipebacklayout.SwipeBackLayout;
import com.zcm.support.swipebacklayout.SwipeBackUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by ZCM on 17-2-22 下午6:23.
 * 此框架为轻量级框架,只是封装了view和presenter,model层需要自行封装
 * 支持activity的滑动退出，可以自行设置
 * Rxjava的订阅与退订阅还没有添加
 */

public abstract class BaseActivity<V extends IBaseView,P extends BasePresenter<V>> extends AppCompatActivity
        implements IBaseView,SwipeBackActivityBase {
    protected final String TAG=this.getClass().getSimpleName();
    protected FrameLayout fm_title;
    protected BaseTitleView bt_title;
    private FrameLayout layout_container;
    private SwipeBackActivityHelper mHelper;
    protected P mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (mHelper == null) {
            mHelper = new SwipeBackActivityHelper(this);
        }
        mHelper.onActivityCreate();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        unbinder.unbind();
        //ButterKnife.unbind(this);
        mPresenter = null;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
    }

    @Override
    public void setContentView(View view) {
        // 初始化公共头部
        layout_container = (FrameLayout) findViewById(R.id.layout_container);
        layout_container.setBackgroundColor(getResources().getColor(R.color.white));
        fm_title=(FrameLayout) findViewById(R.id.fm_title);
        bt_title=(BaseTitleView) findViewById(R.id.bt_title);
        bt_title.getLeftTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_click();
            }
        });
        bt_title.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right_click();
            }
        });
        bt_title.setLeftImageResources(R.drawable.icon_back);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layout_container.addView(view,layoutParams);
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.baseactivity_layout);
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
        unbinder=ButterKnife.bind(this);
    }
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    /**
     * 继承该基类的activity可以设置是否支持滑动退出
     *
     * @param enable
     */
    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        SwipeBackUtils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    public void finish() {
        super.finish();
    }
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    /**
     * 是否使用eventBus,默认为使用(false)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }


    /**
     * 获取view对应的Presenter
     *
     * @return
     */
    protected abstract P createPresenter();

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    /**
     * 设置activity标题
     * @param title
     */
    protected void setActivityTitle(String title){
        bt_title.setTitleText(title);
    }
    protected void setActivityTitle(int title){
        setActivityTitle(getString(title));
    }
    /**
     * 设置自定义标题栏
     * @param view
     */
    protected void setTitleView(View view){
        if(view !=null){
            fm_title.removeAllViews();
            fm_title.addView(view);
        }
    }
    protected FrameLayout getFl_titleBar(){
        return fm_title;
    }

    /**
     * 左侧按钮点击事件
     */
    protected void left_click(){finish();}

    /**
     * 右侧按钮点击事件
     */
    protected void right_click(){}
    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }
    protected void openActivity(String url,Object ... params){
        Router.open(this,url,params);
    }
    protected void openActivityForResult(int requestCode,String url,Object ... params){
        ActivityRoute activityRoute = (ActivityRoute) Router.getRoute(url,params);
        activityRoute.withOpenMethodStartForResult(this, requestCode)
                .open();
    }
}
