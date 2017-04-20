package com.zcm.thunder;

import android.app.Activity;

import com.zcm.bbs.BBSActivity;
import com.zcm.router.Router;
import com.zcm.router.router.IActivityRouteTableInitializer;
import com.zcm.support.base.BaseApplication;

import java.util.Map;

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
        Router.initBrowserRouter(getApplicationContext());
        Router.initActivityRouter(getApplicationContext(), new IActivityRouteTableInitializer() {
            @Override
            public void initRouterTable(Map<String, Class<? extends Activity>> router) {
                router.put("activity://bbs/", BBSActivity.class);
            }
        });
    }
}
