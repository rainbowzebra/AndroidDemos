package com.cn.handler01;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private Handler mHandler;
    public final static int DOWNLOAD_FINISH=9527;
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
                if(msg.what==DOWNLOAD_FINISH){
                    mTextView.setText("下载完成");
                }
            }
        };

        downLoad();
    }

    private void downLoad(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000*8);
                    Message message=new Message();
                    message.what=DOWNLOAD_FINISH;
                    mHandler.sendMessage(message);
                }catch (Exception e){
                }
            }
        }).start();
    }
}
