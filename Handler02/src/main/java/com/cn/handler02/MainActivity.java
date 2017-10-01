package com.cn.handler02;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private Handler mHandler;
    public final static int FINISH_ACTIVITY=9527;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mTextView= (TextView) findViewById(R.id.textView);
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==FINISH_ACTIVITY){
                    finish();
                }
            }
        };

        testPostDelayed();
    }

    private void testPostDelayed(){

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try{
                    Message message=new Message();
                    message.what=FINISH_ACTIVITY;
                    mHandler.sendMessage(message);
                }catch (Exception e){
                }
            }
        };

        mHandler.postDelayed(runnable,1000*4);
        //mHandler.removeCallbacks(runnable);
    }
}
