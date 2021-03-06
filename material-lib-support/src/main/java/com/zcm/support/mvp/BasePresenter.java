package com.zcm.support.mvp;

import android.support.annotation.Nullable;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;

/**
 * Created by ZCM on 17-2-27 下午4:25.
 */

public class BasePresenter<V extends IBaseView> implements IPresenter<V> {
    protected final String TAG=this.getClass().getSimpleName();
    protected Reference<V> viewRef;
    protected Rxmanager rxmanager;
    public BasePresenter(V view){
        viewRef=new WeakReference<V>(view);
        rxmanager=new Rxmanager();
    }

    @Nullable
    public V getView() {
        return viewRef==null?null:viewRef.get();
    }
    /**
     * 是否使用eventBus,默认为使用(false)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }
    @Override
    public void onStart() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().register(this);//注册eventbus
    }

    @Override
    public void onDestroy() {
        if (viewRef!=null){
            viewRef.clear();
           viewRef=null;
        }
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);//解除注册eventbus
        rxmanager.clear();
    }
}
