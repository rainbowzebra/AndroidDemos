package com.cn.broadcastreceiver01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG="TAG";
    private IntentFilter mIntentFilter;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(mNetworkChangeReceiver, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        if(mNetworkChangeReceiver!=null){
            unregisterReceiver(mNetworkChangeReceiver);
        }
        super.onDestroy();
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "网络可用", Toast.LENGTH_LONG).show();
                Log.i(TAG,"网络可用");
            } else {
                Toast.makeText(context, "网络不可用", Toast.LENGTH_LONG).show();
                Log.i(TAG,"网络不可用");
            }

        }

    }

}
