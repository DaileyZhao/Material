package com.zcm.support.mvp;

/**
 * Created by ZCM on 17-2-27 下午3:16.
 */

public interface IPresenter<V extends IBaseView> {
    void onStart();
    void onDestroy();
}
