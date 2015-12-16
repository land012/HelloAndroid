package com.example.helloworld.intent;

import com.example.helloworld.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * 8种 Intent使用方式
 * @author 大洲
 *
 */
public class IntentActivity extends Activity {
	
	private static final String ACTION_MY = "com.umbrella.sg10intenttest.ACTION_MY";
	private static final String CATEGORY_F = "com.umbrella.sg10intenttest.CATEGROY_F";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * ComponentName 测试 —— 显式Intent
		 */
		Button componentnameTestBtn = (Button)this.findViewById(R.id.componentname_test);
		componentnameTestBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ComponentName cn = new ComponentName(IntentActivity.this,
						"com.umbrella.sg10intenttest.activity.ComponentNameActivity");
				Intent intent = new Intent();
				intent.setComponent(cn);
				IntentActivity.this.startActivity(intent);
			}
		});
		
		/*
		 * 自定义Action测试 —— 隐式Intent
		 */
		Button customActionTestBtn = (Button)this.findViewById(R.id.custom_action_test);
		customActionTestBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION_MY);
				IntentActivity.this.startActivity(intent);
			}
		});
		
		/*
		 * 系统Action测试 —— 读取通讯录列表
		 * 不需要声明权限
		 */
		Button systemActionTestBtn = (Button)this.findViewById(R.id.system_action_test);
		systemActionTestBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.setType("vnd.android.cursor.item/phone");
				IntentActivity.this.startActivity(intent);
			}
		});
		
		/*
		 * Category测试 —— 回到Home
		 */
		Button homeBtn = (Button)this.findViewById(R.id.home);
		homeBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				IntentActivity.this.startActivity(intent);
			}
		});
		
		/*
		 * 打电话
		 */
		Button systemActionDialBtn = (Button)this.findViewById(R.id.system_action_dial);
		systemActionDialBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(IntentActivity.this, ActionDialActivity.class);
				IntentActivity.this.startActivity(intent);
			}
		});
		
		
		/**
		 * 自定义 Data —— 隐式Intent
		 * CustomActionActivity
		 */
		Button customDataTestBtn = (Button)this.findViewById(R.id.custom_data_test);
		customDataTestBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				String data = "content://com.umbrella.sg10intenttest/caa";
				intent.setData(Uri.parse(data));
				IntentActivity.this.startActivity(intent);
			}
		});
		
		
		/**
		 * 自定义 Category —— 隐式Intent
		 * 单独指定 Category 是不行的，会报异常，需要同时指定 Data
		 * 就像下面的测试，如果只指定 Data 的话，也会同时找到系统浏览器，但指定 Category 后，就不会
		 */
		Button customCategoryTestBtn = (Button)this.findViewById(R.id.custom_category_test);
		customCategoryTestBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setData(Uri.parse("http://www.baidu.com"));
				intent.addCategory(CATEGORY_F);
				IntentActivity.this.startActivity(intent);
			}
		});
		
		
		/*
		 * 隐式Intent —— 重复
		 */
		Button implicitIntentClashBtn = (Button)this.findViewById(R.id.implicit_intent_clash);
		implicitIntentClashBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.baidu.com"));
				IntentActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
