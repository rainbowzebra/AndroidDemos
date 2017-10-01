package com.cn.service01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceSubclass extends Service {
    public static final String TAG="TAG";
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onCreate() {
        Log.i(TAG,"Service onCreate()");
        Log.i(TAG,"服务中获取到的线程ID="+Thread.currentThread().getId());
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG,"Service onStart()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"Service onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Service onDestroy()");
        super.onDestroy();
    }


}

