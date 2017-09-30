package com.cn.include01;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// <include layout="@layout/coupon_view"></include>
public class AnotherActivity extends AppCompatActivity {
    private Button mButton;
    private Context mContext;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        init();
    }

    private void init(){
        mContext=this;
        mTextView= (TextView) findViewById(R.id.textView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"AnotherActivity中点击TextView",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
