package com.cn.activity02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mToAnotherActivityButton;
    private Button mToThirdActivityButton;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mContext=this;
        mToAnotherActivityButton =(Button)findViewById(R.id.toAnotherActivityButton);
        mToAnotherActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAnotherActivity();
            }
        });
        mToThirdActivityButton= (Button) findViewById(R.id.toThirdActivityButton);
        mToThirdActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toThirdActivity();
            }
        });
    }

    /**
     * 利用startActivityForResult()的方式打开一个新的Activity
     *
     * 注意:
     * startActivityForResult(intent,requestCode)方法的参数
     * requestCode表示本次startActivityForResult()操作的请求码
     * 因为在同一个Activity中根据业务的不同,可能需要在多处
     * 调用startActivityForResult()方法.所以为了区分每次
     * startActivityForResult()的操作为其设置一个requestCode
     */
    private void toAnotherActivity(){
        Intent intent=new Intent(mContext,AnotherActivity.class);
        startActivityForResult(intent,Constant.REQUEST_CODE_ANOTHER);
    }

    private void toThirdActivity(){
        Intent intent=new Intent(mContext,ThirdActivity.class);
        startActivityForResult(intent,Constant.REQUEST_CODE_THIRD);
    }

    /**
     * 利用onActivityResult()处理新Activity关闭时的回传数据
     *
     * 注意:
     * onActivityResult(int requestCode,int resultCode,Intent data)方法的参数
     * 1 requestCode区分了不同的startActivityForResult()调用
     * 2 resultCode表示新Activity中处理结果的状态.
     *   比如某项操作是否成功,是否有异常等
     * 3 data新Activity回传的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constant.REQUEST_CODE_ANOTHER){
            if(resultCode==Constant.RESULT_CODE_ANOTHER){
                String name=data.getStringExtra("name");
                int age=data.getIntExtra("age",0);
                Log.i(Constant.TAG,"name="+name+",age="+age);
            }
        }

        if(requestCode==Constant.REQUEST_CODE_THIRD){
            if(resultCode==Constant.RESULT_CODE_THIRD){
                String name=data.getStringExtra("name");
                int age=data.getIntExtra("age",0);
                Log.i(Constant.TAG,"name="+name+",age="+age);
            }
        }

    }
}
