package com.cn.radiobutton01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private RadioGroup mRadioGroup;
    private CheckedChangeListenerImpl mCheckedChangeListenerImpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        mContext=this;
        mRadioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        mCheckedChangeListenerImpl=new CheckedChangeListenerImpl();
        mRadioGroup.setOnCheckedChangeListener(mCheckedChangeListenerImpl);
    }

    private class CheckedChangeListenerImpl implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton radioButton = (RadioButton) findViewById(checkedId);
            String currentSelected = radioButton.getText().toString();
            Toast.makeText(mContext,"现在选中是:" + currentSelected,Toast.LENGTH_SHORT).show();
        }
    }
}
