package com.cn.activity01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AnotherActivity extends AppCompatActivity {
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
        testIntent3();
    }

    private void testIntent1(){
        String name=this.getIntent().getStringExtra("name");
        int age=this.getIntent().getIntExtra("age",0);
        Log.i(Constant.TAG,"name="+name);
        Log.i(Constant.TAG,"age="+age);
    }

    private void testIntent2(){
        Student student=(Student) this.getIntent().getSerializableExtra("student");
        Log.i(Constant.TAG,student.toString());
    }

    private void testIntent3(){
        ArrayList<Student> studentList=(ArrayList<Student>) this.getIntent().getSerializableExtra("list");
        for(Student student:studentList){
            Log.i(Constant.TAG,student.toString());
        }
    }

}
