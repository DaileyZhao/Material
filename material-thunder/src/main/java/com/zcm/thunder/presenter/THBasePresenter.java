package com.zcm.thunder.presenter;

import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.mvp.IBaseView;

/**
 * Created by zcm on 17-4-10.
 */

public abstract class THBasePresenter extends BasePresenter {
    public THBasePresenter(IBaseView view) {
        super(view);
    }
}
