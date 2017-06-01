package com.zcm.thunder.splash;

import com.zcm.support.network.RxService;
import com.zcm.support.network.RxUtil;
import com.zcm.thunder.THBasePresenter;
import com.zcm.thunder.api.ThunderApi;

/**
 * Created by zcm on 17-4-18.
 */

public class LaunchPresenter extends THBasePresenter<LaunchView> {
    public LaunchPresenter(LaunchView view) {
        super(view);
    }
    public void getPic(){
        rxmanager.add(RxService.createApi(ThunderApi.class).meinv(1)
                .compose(RxUtil.rxSchedulerHelper())
        .subscribe(data->getView().getPicBean(data),
                e->getView().showError("数据加载失败ヽ(≧Д≦)ノ")));
    }
}
