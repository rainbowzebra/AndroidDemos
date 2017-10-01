package com.cn.service02;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class SearchService extends Service {
    private String[] names = new String[] { "小明", "小王", "小杨", "小李", "小强" };
    SearchBinder mSearchBinder = new SearchBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mSearchBinder;
    }

    private final class SearchBinder extends Binder implements SearchInterface {
        public String searchByNumber(int number) {
            if (number > 0 && number < 6) {
                return names[number - 1];
            }
            return "查询错误，请再次输入";
        }
    }

}

