package com.cn.activity01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

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
                testIntent2();
            }
        });
    }

    private void testIntent1(){
        Intent intent=new Intent(mContext,AnotherActivity.class);
        intent.putExtra("name","hanmeimei");
        intent.putExtra("age",19);
        startActivity(intent);
    }

    private void testIntent2(){
        Intent intent=new Intent(mContext,AnotherActivity.class);
        Student student=new Student();
        student.setName("lilei");
        student.setAge(22);
        intent.putExtra("student",student);
        startActivity(intent);
    }

    private void testIntent3(){
        Intent intent=new Intent(mContext,AnotherActivity.class);
        ArrayList<Student> arrayList=new ArrayList<>();
        Student student1=new Student();
        student1.setName("lilei");
        student1.setAge(22);

        Student student2=new Student();
        student2.setName("xiaoming");
        student2.setAge(18);
        arrayList.add(student1);
        arrayList.add(student2);

        intent.putExtra("list",arrayList);
        startActivity(intent);
    }

}
