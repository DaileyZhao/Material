package com.zcm.thunder.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcm.thunder.mvp.IBaseView;
import com.zcm.thunder.mvp.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by ZCM on 17-2-22 下午6:23.
 * 与BaseActivity结构相同的BaseFragment，功能陆续完善
 */

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IBaseView{
    protected final String TAG=this.getClass().getSimpleName();
    protected P mPresenter;
    protected View mRootView;
    protected Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (useEventBus()){
            EventBus.getDefault().register(this);
        }
        if (mPresenter!=null){
            mPresenter=getPresenter();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=initView(inflater,container);
        mUnbinder= ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (mPresenter==null){
            mPresenter=getPresenter();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus()){
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter!=null){
            mPresenter.onDestroy();
        }
        mPresenter=null;
        mUnbinder=null;
        mRootView=null;
    }

    /**
     * 是否使用eventBus,默认为不使用(true)，
     *
     * @return
     */
    protected boolean useEventBus(){
        return false;
    }

    /**
     * 添加View
     * @param inflater
     * @param container
     * @return
     */
    protected abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    /**
     * 初始化参数,加载数据
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
