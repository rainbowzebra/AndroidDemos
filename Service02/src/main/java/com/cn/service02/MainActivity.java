package com.cn.service02;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Button mSearchButton;
    private TextView mNumberTextView;
    private TextView mResultTextView;
    private SearchInterface mBinder;
    private Button mBindServiceButton;
    private Button mStopButton;
    private ClickListenerImpl mClickListenerImpl;
    private ServiceConnectionImpl mServiceConnectionImpl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mContext=this;
        mClickListenerImpl=new ClickListenerImpl();
        mNumberTextView=(TextView) findViewById(R.id.numberEditText);
        mResultTextView=(TextView) findViewById(R.id.resultTextView);
        mSearchButton=(Button) findViewById(R.id.searchButton);
        mSearchButton.setOnClickListener(mClickListenerImpl);
        mBindServiceButton= (Button) findViewById(R.id.bindServiceButton);
        mBindServiceButton.setOnClickListener(mClickListenerImpl);
        mStopButton = (Button) findViewById(R.id.stopServiceButton);
        mStopButton.setOnClickListener(mClickListenerImpl);
    }

    private class ClickListenerImpl implements OnClickListener{
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bindServiceButton:
                    startServiceByBind();
                    break;
                case R.id.searchButton:
                    search();
                    break;
                case R.id.stopServiceButton:
                    stopService();
                    break;
                default:
                    break;
            }
        }
    }

    private void startServiceByBind(){
        Intent intent=new Intent(this,SearchService.class);
        mServiceConnectionImpl=new ServiceConnectionImpl();
        bindService(intent, mServiceConnectionImpl, this.BIND_AUTO_CREATE);
        Toast.makeText(mContext,"服务已经启动",Toast.LENGTH_SHORT).show();
    }

    private final class ServiceConnectionImpl implements ServiceConnection{
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder=(SearchInterface) service;
        }
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void search(){
        if(mBinder!=null){
            String number=mNumberTextView.getText().toString();
            String result=mBinder.searchByNumber(Integer.valueOf(number));
            mResultTextView.setText(result);
        }
    }

    private void stopService(){
        unbindService(mServiceConnectionImpl);
        Toast.makeText(mContext,"终止服务",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
