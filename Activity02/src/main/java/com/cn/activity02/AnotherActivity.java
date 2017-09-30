package com.cn.activity02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnotherActivity extends Activity {
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
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        testSetResult();
    }

    private void testSetResult(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,MainActivity.class);
                intent.putExtra("name","一个小胖子");
                intent.putExtra("age",23);
                setResult(Constant.RESULT_CODE_TEST,intent);
                finish();
            }
        });
    }
}
