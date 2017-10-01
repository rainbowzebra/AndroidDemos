package com.cn.database01;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private Context mContext;
    private Button mAddButton;
    private Button mQueryButton;
    private Button mUpdateButton;
    private Button mDeleteButton;
    private Button mCountButton;
    private Button mPageButton;
    private Button mTransactionButton;
	public static final String TAG="TAG";
	private ClickListenerImpl mClickListenerImpl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init(){
		mContext=this;
		mClickListenerImpl=new ClickListenerImpl();
		mAddButton=(Button) findViewById(R.id.addButton);
		mAddButton.setOnClickListener(mClickListenerImpl);
		
		mQueryButton=(Button) findViewById(R.id.queryButton);
		mQueryButton.setOnClickListener(mClickListenerImpl);
		
		mUpdateButton=(Button) findViewById(R.id.updateButton);
		mUpdateButton.setOnClickListener(mClickListenerImpl);
		
		mDeleteButton=(Button) findViewById(R.id.deleteButton);
		mDeleteButton.setOnClickListener(mClickListenerImpl);
		
		mCountButton=(Button) findViewById(R.id.countButton);
		mCountButton.setOnClickListener(mClickListenerImpl);
		
		mPageButton=(Button) findViewById(R.id.pageButton);
		mPageButton.setOnClickListener(mClickListenerImpl);
		
		mTransactionButton=(Button) findViewById(R.id.transactionButton);
		mTransactionButton.setOnClickListener(mClickListenerImpl);
	}
	
	
	private class ClickListenerImpl implements OnClickListener {
		Person person=null;
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.addButton:
				for (int i = 0; i < 15; i++) {
					person=new Person("xiaoming"+i, "9527"+i);
					Log.i(TAG,"插入数据 "+person.toString());
					DBUtils.add(mContext,person);
				}
				break;
			case R.id.queryButton:
				person=DBUtils.query(mContext,5);
				Log.i(TAG,"查询数据 "+person.toString());
				break;
			case R.id.updateButton:
				person=DBUtils.query(mContext,1);
				Log.i(TAG,"修改前数据 "+person.toString());
				person=new Person(1, "xx", "1234");
				DBUtils.update(mContext,person);
				Person updatedPerson=DBUtils.query(mContext,1);
				Log.i(TAG,"修改后数据 "+updatedPerson.toString());
				break;
			case R.id.deleteButton:
				DBUtils.delete(mContext,2);
				break;
			case R.id.countButton:
				int count=DBUtils.count(mContext);
				Log.i(TAG,"数据总量 "+count);
				break;
			case R.id.pageButton:
				List<Person> list=DBUtils.page(mContext,4, 9);
				for (int i = 0; i < list.size(); i++) {
					 person=list.get(i);
					Log.i(TAG,"分页数据 "+person.toString());
				}
				break;
			case R.id.transactionButton:
				person=new Person(1, "ccc", "8888");
				DBUtils.transaction(mContext,person);
				person=DBUtils.query(mContext,1);
				Log.i(TAG,"事务操作后 "+person.toString());
				break;
			default:
				break;
			}
		}

	}
}
