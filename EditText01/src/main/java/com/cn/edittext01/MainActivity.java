package com.cn.edittext01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mContext=this;
        mEditText= (EditText) findViewById(R.id.editText);
        mButton= (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=mEditText.getText().toString().trim();
                if(!TextUtils.isEmpty(content)){
                    Toast.makeText(mContext,content,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
