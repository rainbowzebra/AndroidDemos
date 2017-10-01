package com.cn.sharedpreferences01;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private EditText mAgeEditText;
    private Button mSaveButton;
    private Button mGetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mNameEditText=(EditText) findViewById(R.id.nameEditText);
        mAgeEditText=(EditText) findViewById(R.id.ageEditText);
        mSaveButton=(Button) findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(new ClickListenerImpl());
        mGetButton=(Button) findViewById(R.id.getButton);
        mGetButton.setOnClickListener(new ClickListenerImpl());
    }

    private class ClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.saveButton:
                    saveToSP();
                    break;
                case R.id.getButton:
                    getFromSP();
                    break;
                default:
                    break;
            }

        }

    }
    private void saveToSP(){
        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String name=mNameEditText.getText().toString();
        String age=mAgeEditText.getText().toString();
        editor.putString("name", name);
        editor.putString("age", age);
        editor.commit();
        Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        mNameEditText.setText("");
        mAgeEditText.setText("");
    }
    private void getFromSP(){
        SharedPreferences
                preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String name=preferences.getString("name", "defaultname");
        String age=preferences.getString("age", "0");
        Toast.makeText(MainActivity.this, "name="+name+",age="+age, Toast.LENGTH_LONG).show();
    }
}
