package com.cn.service01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button mStartButton;
    private Button mStopButton;
    private Intent mIntent;
    public static final String TAG="TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init(){
        Log.i(TAG,"Activity中获取到的线程ID="+Thread.currentThread().getId());
        mIntent =new Intent();
        mIntent.setAction("com.cn.tom");
        mIntent.setPackage(getPackageName());

        //开启服务
        mStartButton=(Button) findViewById(R.id.startButton);
        mStartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(mIntent);
            }
        });

        //终止服务
        mStopButton=(Button) findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(mIntent);
            }
        });
    }


}

