package com.zcm.thunder.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by zcm on 17-6-3.
 */

public class LocalService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
    public class LocalBinder extends Binder{
        LocalService getService(){
            return LocalService.this;
        }
    }
}
