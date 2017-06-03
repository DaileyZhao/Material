package com.zcm.thunder.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zcm.support.mvp.BasePresenter;
import com.zcm.thunder.R;
import com.zcm.thunder.THBaseActivity;

/**
 * Created by zcm on 17-6-3.
 */

public class BindActivity extends THBaseActivity {
    LocalService mService;
    boolean mBound = false;
    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            Log.d(TAG, "onServiceConnected: "+mBound);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.d(TAG, "onServiceDisconnected: "+mBound);
        }
    };
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void onClick(View view){
        Log.d(TAG, "onClick: "+mBound);
        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
        }
    }
}
