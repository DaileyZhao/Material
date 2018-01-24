package com.zcm.thunder;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.zcm.bbs.BBSActivity;
import com.zcm.discovery.DiscoveryActivity;
import com.zcm.router.Router;
import com.zcm.router.router.IActivityRouteTableInitializer;
import com.zcm.support.base.BaseApplication;

import java.util.Map;

/**
 * Created by zcm on 2017/3/26.
 */

public class MainApp extends BaseApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e(TAG, "attachBaseContext: "+base );
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setRouter();
    }

    private void setRouter(){
        // only if the host is equal and pathes match, it matches.
        // The url "activity://first/kris/26/birthday" is one of the matches.
        // "kris" and 26 are values of key "name" and "age". and the "name" value type is string, the age value type is integer.
        Router.setDebugMode(true);
        Router.initBrowserRouter(getApplicationContext());
        Router.initActivityRouter(getApplicationContext(), new IActivityRouteTableInitializer() {
            @Override
            public void initRouterTable(Map<String, Class<? extends Activity>> router) {
                router.put("activity://discovery/:s{name}/:i{age}/birthday", DiscoveryActivity.class);
                router.put("activity://bbs/", BBSActivity.class);
            }
        });
    }
}
