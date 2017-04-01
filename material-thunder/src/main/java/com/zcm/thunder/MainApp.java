package com.zcm.thunder;

import com.zcm.bbs.BBSActivity;
import com.zcm.discovery.DiscoveryActivity;
import com.zcm.router.Router;
import com.zcm.router.rule.ActivityRule;
import com.zcm.support.base.BaseApplication;

/**
 * Created by zcm on 2017/3/26.
 */

public class MainApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        setRouter();
    }

    private void setRouter(){
        Router.router(ActivityRule.ACTIVITY_SCHEME+"bbs.main", BBSActivity.class);
        Router.router(ActivityRule.ACTIVITY_SCHEME+"discovery.main", DiscoveryActivity.class);
    }
}
