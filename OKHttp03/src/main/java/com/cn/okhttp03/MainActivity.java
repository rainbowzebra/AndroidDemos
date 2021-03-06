package com.cn.okhttp03;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    public final static String TAG="TAG";
    private static final int REQUEST_CODE = 9527;
    private static String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        mContext=this;
        verifyStoragePermissions(this);
    }

    private void testOkHttp(){
        testDownload();
        //testUpload();
    }

    public void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST_CODE);
        }else{
            testOkHttp();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    testOkHttp();
                } else {
                    Toast.makeText(mContext, "您未授权存储权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }


    //下载
    public void testDownload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url="http://img.blog.csdn.net/20161023140032428";
                    final File downloadFile=new File(getExternalCacheDir().toString()+File.separator+"test9527.jpg");
                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG,"onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.i(TAG,"onResponse "+response.code());
                            if(response.isSuccessful()){
                                InputStream inputStream=response.body().byteStream();
                                FileOutputStream fileOutputStream=new FileOutputStream(downloadFile);
                                int len=0;
                                byte b []=new byte[1024*2];
                                while ((len=inputStream.read(b))!=-1){
                                    fileOutputStream.write(b,0,len);
                                }
                                fileOutputStream.close();
                                inputStream.close();
                            }
                            response.body().close();
                            Bitmap bitmap = BitmapFactory.decodeFile(downloadFile.getAbsolutePath());
                            Log.i(TAG,"width="+bitmap.getWidth());
                            Log.i(TAG,"height="+bitmap.getHeight());
                        }
                    });

                }catch (Exception e){
                }
            }
        }).start();
    }

    //上传
    public void testUpload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String url ="http:192.168.0.103:8081/SpringMVC07/testUpload/uploadFile.do";
                    OkHttpClient okHttpClient=new OkHttpClient();
                    MultipartBody.Builder multipartBodyBuilder=new MultipartBody.Builder();
                    //添加文件
                    File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"test.jpg");
                    String fileName=file.getName();
                    MediaType mediaType=MediaType.parse("image/*");
                    RequestBody requestBody=RequestBody.create(mediaType,file);
                    multipartBodyBuilder.setType(MultipartBody.FORM);
                    multipartBodyBuilder.addFormDataPart("photo",fileName,requestBody);
                    //添加参数
                    multipartBodyBuilder.addFormDataPart("name","zxx");
                    multipartBodyBuilder.addFormDataPart("number","9527");
                    multipartBodyBuilder.addFormDataPart("country","China");
                    MultipartBody multipartBody=multipartBodyBuilder.build();
                    Request.Builder requestBuilder=new Request.Builder();
                    requestBuilder.url(url);
                    requestBuilder.post(multipartBody);
                    Request request=requestBuilder.build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG,"onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.i(TAG,"onResponse");
                        }
                    });

                }catch (Exception e){

                }
            }
        }).start();
    }

}
