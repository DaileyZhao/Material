package com.zcm.thunder.banner;

import android.util.Log;

import com.zcm.support.mvp.BasePresenter;
import com.zcm.support.network.RxService;
import com.zcm.support.network.RxUtil;
import com.zcm.thunder.api.ThunderApi;
import com.zcm.thunder.splash.PicBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zcm on 17-6-15.
 */

public class BannerPresenter extends BasePresenter<IBannerView> {
    public BannerPresenter(IBannerView view) {
        super(view);
    }
    public void loadData(){
        rxmanager.add(RxService.createApi(ThunderApi.class).meinv(10)
                .compose(RxUtil.rxSchedulerHelper())
        .subscribe(new Subscriber<PicBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(PicBean bean) {
                List<PicBean.Newslist> newslists=bean.getNewslist();
                List<String> piclist=new ArrayList<String>();
                for (PicBean.Newslist news:newslists){
                    piclist.add(news.getPicUrl());
                }
                getView().getPicList(piclist);
            }
        }));
    }
}
