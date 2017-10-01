package com.cn.broadcastreceiver02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverTest extends BroadcastReceiver {
	private final static String TAG="TAG";
	@Override
	public void onReceive(Context context, Intent intent) {
          String name=intent.getStringExtra("name");
          int number=intent.getIntExtra("number", 0);
		  Log.i(TAG,"接收到广播 name="+name+",number="+number);
	}

}
