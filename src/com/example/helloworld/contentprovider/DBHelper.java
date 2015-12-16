package com.example.helloworld.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "test.db";
	private static final int DB_VERSION = 1;
	
	public static final String TBL_EMPLOYEES = "employee";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + TBL_EMPLOYEES + " (" +
				Employees.Employee._ID + " INTEGER PRIMARY KEY," +
				Employees.Employee.NAME + " TEXT," +
				Employees.Employee.GENDER + " TEXT," +
				Employees.Employee.AGE + " INTEGER" +
				")";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TBL_EMPLOYEES);
		onCreate(db);
	}

}
