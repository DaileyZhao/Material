package com.zcm.support.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zcm on 17-6-1.
 */

public class Rxmanager {
    private CompositeSubscription compositeSubscription=new CompositeSubscription();
    public void add(Subscription s){
        if (compositeSubscription.isUnsubscribed()){
            compositeSubscription=new CompositeSubscription();
        }
        compositeSubscription.add(s);
    }
    public void clear(){
        compositeSubscription.clear();
    }
}
