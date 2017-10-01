package com.cn.database01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="test.db";
    private static DataBaseOpenHelper mDataBaseOpenHelper;
    //super(context, DATABASE_NAME, null, 1)方法:
    //若不存在DATABASE_NAME数据,则执行onCreate(SQLiteDatabase db)方法
    //若已经存在则不再新建数据库
    //方法中第三个参数为:version 版本号
    //当version变大时会自动调用
    //onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)方法
	public DataBaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	
	//注意:
	//将DataBaseOpenHelper写成单例的.
	//否则当在一个for循环中频繁调用openHelper.getWritableDatabase()时
	//会报错,提示数据库没有执行关闭操作
	static synchronized DataBaseOpenHelper getDBInstance(Context context) {
		if (mDataBaseOpenHelper == null) {
			mDataBaseOpenHelper = new DataBaseOpenHelper(context);
		}
		return mDataBaseOpenHelper;
	} 
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person(personid integer primary key autoincrement,name varchar(20),phone VARCHAR(12))");
	}

	//为person增加一个address字段,默认值为null
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE person ADD address VARCHAR(12) NULL");
	}

}
