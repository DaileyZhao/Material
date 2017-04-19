package com.zcm.thunder;

import com.zcm.support.base.BaseActivity;
import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.mvp.IBaseView;

/**
 * Created by zcm on 17-3-30.
 */

public abstract class THBaseActivity<V extends IBaseView,P extends BasePresenter<V>> extends BaseActivity<V,P>{
}
