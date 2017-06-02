package com.zcm.thunder.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zcm on 17-6-2.
 */

public class TestService extends Service {
    protected static final String TAG=TestService.class.getSimpleName();
    private volatile int count=0;
    private void inc(){
        count++;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        for (int index=0;index<1000;index++){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    inc();
                }
            }.start();
        }
        System.out.println("运行结果:Counter.count=" + count);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
