package com.cn.database01;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBUtils {
	public static DataBaseOpenHelper openHelper;
	public static SQLiteDatabase db;
	
	public static void add(Context context,Person person){
		openHelper=DataBaseOpenHelper.getDBInstance(context);
		db=openHelper.getWritableDatabase();
        db.execSQL("insert into person (name,phone) values(?,?)",
        		    new Object[]{person.getName(),person.getPhone()});
		
	}
	

	/**
	 * 1 rawQuery()返回的结果是一个Cursor类的对象
	 * 2 查询结束后需关闭cursor即cursor.close();
	 */
	public  static Person query(Context context,int id){
		openHelper=DataBaseOpenHelper.getDBInstance(context);
		db=openHelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from person where personid=?", new String[]{String.valueOf(id)});
		while(cursor.moveToFirst()){
			int personid=cursor.getInt(cursor.getColumnIndex("personid"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String phone=cursor.getString(cursor.getColumnIndex("phone"));
			return new Person(personid, name, phone);
		}
		cursor.close();
		return null;
	}
	
	
	//因为name和phone的类型都是String,但是id是整型的
	//所以这里的数组写成了Object类型的
	public  static void update(Context context,Person person){
		openHelper=DataBaseOpenHelper.getDBInstance(context);
		db=openHelper.getWritableDatabase();
		db.execSQL("update person set name=?,phone=? where personid=?",
				    new Object[]{person.getName(),person.getPhone(),person.getId()});
	}
	
	
	public  static void delete(Context context,int id){
		openHelper=DataBaseOpenHelper.getDBInstance(context);
		db=openHelper.getWritableDatabase();
        db.execSQL("delete from person where personid=?",new Object[]{String.valueOf(id)});
	}
	
	//在while循环里要注意终止循环,否则是个死循环
	//因为如果cursor不为空那么
	//cursor.moveToFirst()总是返回true
	public  static int count(Context context){
		openHelper=DataBaseOpenHelper.getDBInstance(context);
		db=openHelper.getReadableDatabase(); 
		Cursor cursor=db.rawQuery("select count(*) from person", null);
		int i=0;
		while(cursor.moveToFirst()){
		     i=cursor.getInt(0);
		     break;
		}
		return i;
	}
	
	public  static List<Person> page(Context context,int offset,int resuletNumber){
		openHelper=DataBaseOpenHelper.getDBInstance(context);
		db=openHelper.getWritableDatabase();
		ArrayList<Person> persons=new ArrayList<Person>();	
		Person person=null;
		Cursor  cursor=db.rawQuery("select * from person limit ?,?",
				                    new String []{String.valueOf(offset),String.valueOf(resuletNumber)});
		while(cursor.moveToNext()){
			int personid=cursor.getInt(cursor.getColumnIndex("personid"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String phone=cursor.getString(cursor.getColumnIndex("phone"));
			person=new Person(personid, name, phone);
			persons.add(person);
		}
		return persons;
	  }
	
	/**
	 * 结束事务有两种:提交事务和回滚事务.
	 * 默认情况是回滚事务!!!!
	 * 事务是否提交是由事务的标志来决定:
	 * 如果事务的标志位失败(false),就回滚事务;否则(true)提交事务。
	 * 所以默认情况下事务的标志为失败(false)即回滚事务.
	 */
	public  static void transaction(Context context,Person person){
		openHelper=DataBaseOpenHelper.getDBInstance(context);
        db = openHelper.getWritableDatabase();
		//开启事务
		db.beginTransaction();
		try{
			db.execSQL("update person set name=? where personid=?",
					    new Object[]{person.getName(),person.getId()});
			db.execSQL("update person set phone=? where personid=?",
				    new Object[]{person.getPhone(),person.getId()});
			//设置事务的标志为成功
			db.setTransactionSuccessful();
		}finally{
			//结束事务,默认情况下是回滚事务
			db.endTransaction();
		}
	}
	
}
