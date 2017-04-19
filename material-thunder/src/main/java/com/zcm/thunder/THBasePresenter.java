package com.zcm.thunder;

import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.mvp.IBaseView;

/**
 * Created by zcm on 17-4-10.
 */

public abstract class THBasePresenter<V extends IBaseView> extends BasePresenter<V> {
    public THBasePresenter(V view) {
        super(view);
    }
}
