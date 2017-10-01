package com.cn.okhttp02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public final static String TAG="TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        testGet();
        //testPost();
    }

    //Get异步请求
    public void testGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://blog.csdn.net/lfdfhl";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            int code=response.code();
                            String result=response.body().string();
                            response.body().close();
                            Log.i(TAG,"code="+code);
                            Log.i(TAG,"测试Get异步请求 "+result);
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
    }

    //POST异步请求
    public void testPost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://106.14.136.52:8080/user/login";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request.Builder requestBuilder=new Request.Builder();
                    FormBody.Builder formBodyBuilder=new FormBody.Builder();
                    formBodyBuilder.add("username","andy");
                    formBodyBuilder.add("password","123456");
                    requestBuilder.url(url);
                    RequestBody requestBody=formBodyBuilder.build();
                    requestBuilder.post(requestBody);
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            int code=response.code();
                            String result=response.body().string();
                            response.body().close();
                            Log.i(TAG,"code="+code);
                            Log.i(TAG,"测试Post异步请求 "+result);
                        }
                    });

                }catch (Exception e){

                }
            }
        }).start();
    }


}
