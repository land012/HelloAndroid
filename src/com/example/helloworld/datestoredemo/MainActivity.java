package com.example.helloworld.datestoredemo;

import com.example.helloworld.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText smsContentEt;
	private static final String TEMP_SMS = "temp_sms"; // 文件标志
	public static final String TAG = "sg13datastoretest";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datastore);
		
		/**
		 * Preference 测试
		 * 获取之前已经保存的临时文本
		 */
		smsContentEt = (EditText)this.findViewById(R.id.sms_content);
		SharedPreferences prefer = this.getSharedPreferences(TEMP_SMS, Context.MODE_PRIVATE);
		String sms_content = prefer.getString("sms_content", "");
		Log.i(TAG, sms_content);
		smsContentEt.setText(sms_content);
		
		/**
		 * SQLiteOpenHelper 测试
		 */
//		MyDbHelper myDbHelper = new MyDbHelper(this);
//		myDbHelper.insert();
//		myDbHelper.query();
		
		/**
		 * SQLiteOpenHelper 测试 —— 收藏管理
		 * 添加
		 */
		Button siteAddBtn = (Button)this.findViewById(R.id.site_add);
		siteAddBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText nameEt = (EditText)MainActivity.this.findViewById(R.id.site_name);
				String name = nameEt.getText().toString();
				EditText urlEt = (EditText)MainActivity.this.findViewById(R.id.site_url);
				String url = urlEt.getText().toString();
				EditText descEt = (EditText)MainActivity.this.findViewById(R.id.site_desc);
				String desc = descEt.getText().toString();
				
				ContentValues values = new ContentValues();
				values.put("name", name);
				values.put("url", url);
				values.put("desc", desc);
				Log.i(TAG, "save-" + values.toString());
				DBHelper dbHelper = new DBHelper(MainActivity.this);
				dbHelper.insert(values);
				Toast.makeText(MainActivity.this.getApplicationContext(), "添加成功", Toast.LENGTH_LONG).show();
			}
		});
		
		// 查看
		Button siteViewBtn = (Button)this.findViewById(R.id.site_view);
		siteViewBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "view");
				Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		/**
		 * Preference 测试
		 * 将文本临时保本到 xml文件中
		 * 关闭时，保存没有处理完的内容
		 */
		SharedPreferences.Editor editor = this.getSharedPreferences(TEMP_SMS, Context.MODE_PRIVATE).edit();
		String sms_content = smsContentEt.getText().toString();
		Log.i(TAG, "onStop:" + sms_content);
		editor.putString("sms_content", smsContentEt.getText().toString());
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class MyDbHelper extends SQLiteOpenHelper {
		
//		private SQLiteDatabase db;
		private static final String CREATE_TABLE = "create table usertbl(_id integer, name text)";
		
		public MyDbHelper(Context context) {
			super(context, "test.db", null, 2);
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			arg0.execSQL(CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}
		
		private void insert() {
			String sql = "insert into usertbl(_id, name) values(2, 'jim')";
			this.getWritableDatabase().execSQL(sql);
		}
		
		private void query() {
			Cursor c = this.getWritableDatabase().query("usertbl", null, null, null, null, null, "_id DESC");
			if(c.moveToFirst()) {
				for(int i=0; i<c.getCount(); i++) {
					c.move(i);
					Log.i(TAG, "id=" + c.getInt(0) + ",name=" + c.getString(1));
				}
			}
			Log.i(TAG, "-------------------------------------------");
			Cursor c2 = this.getWritableDatabase().query("usertbl", null, null, null, null, null, "_id DESC");
			while(c2.moveToNext()) {
				Log.i(TAG, "id=" + c2.getInt(0) + ",name=" + c2.getString(1));
			}
		}
	}

}
