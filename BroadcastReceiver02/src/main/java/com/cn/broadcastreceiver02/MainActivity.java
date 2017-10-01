package com.cn.broadcastreceiver02;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mSendBroadcastButton;
    private Button mRegisterReceiverButton;
    private BroadcastReceiverTest mBroadcastReceiverTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mRegisterReceiverButton= (Button) findViewById(R.id.registerReceiverButton);
        mRegisterReceiverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerReceiver();
            }
        });

        mSendBroadcastButton= (Button) findViewById(R.id.sendBroadcastButton);
        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcast();
            }
        });
    }

    private void registerReceiver(){
        mBroadcastReceiverTest=new BroadcastReceiverTest();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.cn.andy");
        registerReceiver(mBroadcastReceiverTest, intentFilter);
    }

    private void sendBroadcast(){
        Intent intent = new Intent();
        intent.setAction("com.cn.andy");
        intent.putExtra("name", "周星驰");
        intent.putExtra("number", 9527);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        if (mBroadcastReceiverTest!=null) {
            unregisterReceiver(mBroadcastReceiverTest);
        }
        super.onDestroy();
    }

}
