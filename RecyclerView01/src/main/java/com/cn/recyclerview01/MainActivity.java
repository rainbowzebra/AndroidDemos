package com.cn.recyclerview01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        mContext=this;
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mList=new ArrayList<String>();
        for(int i=0;i<20;i++){
            mList.add("This is "+i);
        }
        mRecyclerViewAdapter=new RecyclerViewAdapter(mContext,mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"点击位置="+position +"，内容="+mList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerViewAdapter.setOnItemLongClickListener(new RecyclerViewAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext,"长按位置="+position +"，内容="+mList.get(position),Toast.LENGTH_SHORT).show();
                mRecyclerViewAdapter.deleteItem(position);
            }
        });

    }
}

