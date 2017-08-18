package com.zcm.thunder.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.zcm.support.widget.ActionSheetDialog;
import com.zcm.thunder.MainApp;

import java.util.Random;

/**
 * Created by zcm on 17-6-3.
 */

public class LocalService extends Service {
    private final String TAG=this.getClass().getName();
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();

    @Override
    public void onCreate() {
        super.onCreate();
        new ActionSheetDialog(LocalService.this).builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("haha", ActionSheetDialog.SheetItemColor.Blue,null)
                .show();
        Toast.makeText(MainApp.getAppContext(),"hahahah",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onCreate: " );
    }

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
