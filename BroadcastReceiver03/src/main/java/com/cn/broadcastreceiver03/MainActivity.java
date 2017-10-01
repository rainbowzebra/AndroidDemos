package com.cn.broadcastreceiver03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mSendBroadcastButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){

        mSendBroadcastButton= (Button) findViewById(R.id.sendBroadcastButton);
        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcast();
            }
        });
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
        super.onDestroy();
    }

}
