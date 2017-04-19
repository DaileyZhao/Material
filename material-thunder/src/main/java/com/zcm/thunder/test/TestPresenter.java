package com.zcm.thunder.test;

import com.zcm.support.mvp.IBaseView;
import com.zcm.thunder.THBasePresenter;

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
