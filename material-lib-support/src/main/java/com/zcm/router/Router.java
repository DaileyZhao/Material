package com.zcm.router;

import android.content.Context;

import com.zcm.router.interceptor.Interceptor;
import com.zcm.router.route.IRoute;
import com.zcm.router.router.HistoryItem;
import com.zcm.router.router.IActivityRouteTableInitializer;
import com.zcm.router.router.IRouter;

import java.util.Locale;
import java.util.Queue;

import timber.log.Timber;

/**
 * Created by kris on 16/3/17.
 * shell to the user
 * http://www.ctolib.com/AndRouter.html#articleHeader1
 在路由框架中，url应该包含两点功能，一是唯一确定一条路由，二是提供一些参数。我们可以以一个路由的例子来讲解ActivityRouter的路由规则。本规则参考了REST。
 例：activity://main/:i{key1}/path1/:f{key2}

 scheme为activity代表该url可以被ActivityRouter打开。
 host为main一般表示决定的Activity。
 而:{key1}则表示一个值的key，这个path segment在url中会被具体的值替换，:后面的i表示该key对应的值的类型为int型。
 path1为固定的path segment，与上面的key用来传递值不同。用来区分路由，与host功能类似。
 */
public class Router {


    public static synchronized void addRouter(IRouter router){
        RouterManager.getSingleton().addRouter(router);
    }

    public static synchronized void initBrowserRouter(Context context){
       RouterManager.getSingleton().initBrowserRouter(context);
    }


    public static synchronized void initActivityRouter(Context context){
        RouterManager.getSingleton().initActivityRouter(context);
    }

    /**
     * @See
     * @param context
     * @param scheme
     * @param initializer
     */
    @Deprecated
    public static synchronized void initActivityRouter(Context context, String scheme, IActivityRouteTableInitializer initializer){
        RouterManager.getSingleton().initActivityRouter(context, initializer, scheme);
    }


    public static synchronized void initActivityRouter(Context context, IActivityRouteTableInitializer initializer, String ... scheme){
        RouterManager.getSingleton().initActivityRouter(context, initializer, scheme);
    }

    public static synchronized void initActivityRouter(Context context, String ... scheme){
        RouterManager.getSingleton().initActivityRouter(context, scheme);
    }

    public static boolean open(String url, Object ... params){
        String temp = String.format(Locale.ENGLISH, url, params);
        return RouterManager.getSingleton().open(temp);
    }

    public static boolean open(Context context, String url, Object ... params){
        String temp = String.format(Locale.ENGLISH, url, params);
        return RouterManager.getSingleton().open(context, temp);
    }

    /**
     * AndRouter uses Timber to output logs. Timber needs init, so if you don't use Timber and you want to view logs of AndRouter, you may need to
     * use this method, and set the debug as true
     */
    public static void setDebugMode(boolean debug){
        if(debug) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    /**
     * the route of the url, if there is not router to process the url, return null
     * @param url
     * @return
     */
    public static IRoute getRoute(String url, Object ... params){
        String temp = String.format(Locale.ENGLISH, url, params);
        return RouterManager.getSingleton().getRoute(temp);
    }


    public static boolean openRoute(IRoute route){
        return RouterManager.getSingleton().openRoute(route);
    }

    public static Queue<HistoryItem> getActivityChangedHistories(){
        return RouterManager.getSingleton().getActivityChangedHistories();
    }

    public static void setInterceptor(Interceptor interceptor){
        RouterManager.getSingleton().setInterceptor(interceptor);
    }

}
