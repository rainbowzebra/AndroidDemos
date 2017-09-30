package com.cn.intent01;

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
        mContext=this;
        mButton=(Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testIntent();
            }
        });
    }

    //显式Intent
    private void testIntent(){
        Intent intent=new Intent(mContext,AnotherActivity.class);
        intent.putExtra("name","hanmeimei");
        intent.putExtra("age",19);
        startActivity(intent);
    }


}
