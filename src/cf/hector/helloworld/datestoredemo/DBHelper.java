package cf.hector.helloworld.datestoredemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	private SQLiteDatabase db;
	private static final String DB_NAME = "test.db";
	private static final String TBL_FAVOR = "favor";
	private static final String CREATE_TABLE = "create table " + TBL_FAVOR + "(_id integer primary key autoincrement, name text, url text, desc text)";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, 2);
	}
	
	/**
	 * 当数据库已经创建时，此方法不会被执行
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(MainActivity.TAG, "DBHelper-onCreate" + CREATE_TABLE);
		this.db = db;
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(MainActivity.TAG, "DBHelper-onUpgrade");
	}
	
	public void insert(ContentValues values) {
		if(this.db == null) this.db = this.getWritableDatabase();
		db.insert(TBL_FAVOR, null, values);
		db.close();
	}
	
	public Cursor query() {
		if(this.db == null) this.db = this.getWritableDatabase();
		return this.db.query(TBL_FAVOR, null, null, null, null, null, "_id ASC");
	}
	
	public void del(int id) {
		if(this.db == null) this.db = this.getWritableDatabase();
		this.db.delete(TBL_FAVOR, "_id=?", new String[]{ String.valueOf(id) });
	}
	
	public void close() {
		if(this.db != null) this.db.close();
	}

}
