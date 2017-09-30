package com.cn.intent02;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
   private Button mButton;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mContext=this;
        mButton=(Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testIntent();
            }
        });
    }

    //隐式Intent
    private void testIntent(){
        Intent intent=new Intent();
        intent.setAction("com.cn.action");
        intent.addCategory("com.cn.category");
        intent.putExtra("name","lilei");
        intent.putExtra("age",21);
        startActivity(intent);
    }



}
