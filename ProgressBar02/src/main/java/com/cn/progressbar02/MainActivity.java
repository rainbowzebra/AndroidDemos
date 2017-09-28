package com.cn.progressbar02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        mContext=this;
        mTextView= (TextView) findViewById(R.id.textView);
        mProgressBar= (ProgressBar) findViewById(R.id.progressBar);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentProgress=mProgressBar.getProgress();
                currentProgress=currentProgress+10;
                mProgressBar.setProgress(currentProgress);
                Toast.makeText(mContext,"当前进度="+currentProgress,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
