package com.zcm.thunder.presenter;

import com.zcm.support.mvp.IBaseView;

/**
 * Created by zcm on 17-4-10.
 */

public class TestPresenter extends THBasePresenter {
    public TestPresenter(IBaseView view) {
        super(view);
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }
}
