package com.cn.seekbar01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private SeekBar mSeekBar;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        mContext=this;
        mSeekBar= (SeekBar) findViewById(R.id.seekBar);
        mTextView= (TextView) findViewById(R.id.textView);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mTextView.setText("当前进度 "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mTextView.setText("开始拖动SeekBar");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mTextView.setText("停止拖动SeekBar");
            }
        });
    }
}
