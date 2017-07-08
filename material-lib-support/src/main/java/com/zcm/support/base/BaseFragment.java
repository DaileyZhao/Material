package com.zcm.support.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by ZCM on 17-2-22 下午6:23.
 * 与BaseActivity结构相同的BaseFragment，功能陆续完善
 */

public abstract class BaseFragment<V extends IBaseView,P extends BasePresenter<V>> extends Fragment implements IBaseView {
    protected final String TAG=this.getClass().getSimpleName();
    protected P mPresenter;
    protected View mRootView;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter==null){
            mPresenter= createPresenter();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=initView(inflater,container);
        unbinder=ButterKnife.bind(this,mRootView);
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
            mPresenter= createPresenter();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter!=null){
            mPresenter.onStart();
        }
        if (useEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //ButterKnife.unbind(this);
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
    protected abstract P createPresenter();

    public void showLoading(){}

    public void hideLoading(){}
}
