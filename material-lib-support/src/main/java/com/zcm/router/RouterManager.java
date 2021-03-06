package com.zcm.router;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zcm.router.interceptor.Interceptor;
import com.zcm.router.route.IRoute;
import com.zcm.router.router.ActivityRouter;
import com.zcm.router.router.BrowserRouter;
import com.zcm.router.router.HistoryItem;
import com.zcm.router.router.IActivityRouteTableInitializer;
import com.zcm.router.router.IRouter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kris on 16/3/17.
 *
 * router 应该是个单例
 */
public class RouterManager {

    private static final String TAG=RouterManager.class.getSimpleName();
    private static final RouterManager singleton = new RouterManager();

    //注意这是个list是有顺序的，所以排在前面的优先级会比较高
    static List<IRouter> mRouters = new LinkedList<>();

    private RouterManager(){}

    static RouterManager getSingleton(){
        return singleton;
    }

    public synchronized void addRouter(IRouter router){
        if(router != null){
            //first remove all the duplicate routers
            List<IRouter> duplicateRouters = new ArrayList<>();
            for(IRouter r : mRouters){
                if(r.getClass().equals(router.getClass())){
                    duplicateRouters.add(r);
                }
            }
            mRouters.removeAll(duplicateRouters);
            mRouters.add(router);
        } else {
            Log.e(TAG, "router is null!!!");
        }
    }

    public void setInterceptor(Interceptor interceptor){
        for(IRouter router : mRouters){
            router.setInterceptor(interceptor);
        }
    }

    public synchronized void initBrowserRouter(Context context){
        BrowserRouter browserRouter = BrowserRouter.getInstance();
        browserRouter.init(context);
        addRouter(browserRouter);
    }


    public synchronized void initActivityRouter(Context context){
        ActivityRouter activityRouter = ActivityRouter.getInstance();
        activityRouter.init(context);
        addRouter(activityRouter);
    }

    public synchronized void initActivityRouter(Context context, String ... schemes){
        initActivityRouter(context, null, schemes);
    }

    public synchronized void initActivityRouter(Context context, IActivityRouteTableInitializer initializer, String ... schemes){
        ActivityRouter router = ActivityRouter.getInstance();
        if(initializer == null) {
            router.init(context);
        } else {
            router.init(context, initializer);
        }
        if(schemes != null && schemes.length > 0){
            router.setMatchSchemes(schemes);
        }
        addRouter(router);
    }

    public List<IRouter> getRouters(){
        return mRouters;
    }


    public boolean open(String url){
        for(IRouter router : mRouters){
            if(router.canOpenTheUrl(url)){
                return router.open(url);
            }
        }
        return false;
    }

    /**
     * the route of the url, if there is not router to process the url, return null
     * @param url
     * @return
     */
    @Nullable
    public IRoute getRoute(String url){
        for(IRouter router : mRouters){
            if(router.canOpenTheUrl(url)){
                return router.getRoute(url);
            }
        }
        return null;
    }

    public boolean open(Context context, String url){
        for(IRouter router : mRouters){
            if(router.canOpenTheUrl(url)){
                return router.open(context, url);
            }
        }
        return false;
    }


    public boolean openRoute(IRoute route){
        for(IRouter router : mRouters){
            if(router.canOpenTheRoute(route)){
                return router.open(route);
            }
        }
        return false;
    }

    public Queue<HistoryItem> getActivityChangedHistories(){
        ActivityRouter aRouter = null;
        for(IRouter router : mRouters){
            if(router instanceof ActivityRouter){
                aRouter = (ActivityRouter) router;
                break;
            }
        }
        return aRouter != null ? aRouter.getRouteHistories() : null;
    }




}
