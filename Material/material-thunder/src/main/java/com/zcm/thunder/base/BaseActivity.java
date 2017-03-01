package com.zcm.thunder.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zcm.thunder.R;
import com.zcm.thunder.mvp.IBaseView;
import com.zcm.thunder.mvp.IPresenter;
import com.zcm.ui.swipebacklayout.SwipeBackLayout;
import com.zcm.ui.swipebacklayout.Utils;
import com.zcm.ui.swipebacklayout.app.SwipeBackActivityBase;
import com.zcm.ui.swipebacklayout.app.SwipeBackActivityHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by ZCM on 17-2-22 下午6:23.
 * 此框架为轻量级框架,只是封装了view和presenter,model层需要自行封装
 * 支持activity的滑动退出，可以自行设置
 * Rxjava的订阅与退订阅还没有添加
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IBaseView,SwipeBackActivityBase {
    protected final String TAG=this.getClass().getSimpleName();
    private SwipeBackActivityHelper mHelper;
    private Unbinder mUnbinder;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mHelper==null)
            mHelper= new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        if (mPresenter==null){
            mPresenter=getPresenter();
        }
        setContentView(setViewById());
        mUnbinder= ButterKnife.bind(this);
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(useEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDestroy();
        }
        if(useEventBus()){
            EventBus.getDefault().unregister(this);
        }
        if (mUnbinder!=Unbinder.EMPTY) mUnbinder.unbind();
        mPresenter=null;
        mUnbinder=null;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter!=null){
            mPresenter=getPresenter();
        }
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    /**
     * 继承该基类的activity可以设置是否支持滑动退出
     * @param enable
     */
    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
    }
    /**
     * 是否使用eventBus,默认为不使用(true)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    /**
     * 设置布局，在setContentView中调用
     * @return
     */
    protected abstract int setViewById();

    /**
     * 初始化参数,加载数据,在onCreate方法中调用
     */
    protected abstract void initData();

    /**
     * 获取view对应的Presenter
     * @return
     */
    protected abstract P getPresenter();

    public void showLoading(){}

    public void hideLoading(){}
}
