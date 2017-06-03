package com.zcm.thunder.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.storage.StorageManager;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zcm on 17-6-2.
 */

public class TestService extends Service {
    protected static final String TAG=TestService.class.getSimpleName();
    private volatile int count=0;
    private boolean stop =false;
    public void increase() {
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
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                while (!stop){
//                    System.out.println("运行结果:Counter.count=" + count++);
//                }
//            }
//        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                stop=true;
//            }
//        }.start();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            }.start();
        }
        while (Thread.activeCount() > 1) { //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(count);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
