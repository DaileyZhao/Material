package com.zcm.thunder.splash;

import com.zcm.support.mvp.IBaseView;

/**
 * Created by zcm on 17-4-18.
 */

public interface LaunchView extends IBaseView {
    void getPicBean(PicBean bean);
    void showError(String errormsg);
}
