package com.cn.intent02;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class AnotherActivity extends AppCompatActivity {
    public static final String TAG="TAG";
    private Button mButton;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        init();
    }

    private void init(){
        mContext=this;
        mButton=(Button)findViewById(R.id.button);
        testIntent();
    }

    private void testIntent(){
        String name=this.getIntent().getStringExtra("name");
        int age=this.getIntent().getIntExtra("age",0);
        Log.i(TAG,"name="+name);
        Log.i(TAG,"age="+age);
    }

}
