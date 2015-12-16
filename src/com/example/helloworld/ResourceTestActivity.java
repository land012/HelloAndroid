package com.example.helloworld;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 
 * SG 尚观 pdf教程
 * 
 * @author asdf
 * 使用 colors.xml 资源文件
 * 读取 xml 文件
 * 使用 menu/main.xml 创建 选项菜单
 */
public class ResourceTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resourcestest);
		
		this.getWindow().setBackgroundDrawableResource(R.color.lightwhite);
		
		Log.i("tag", "在代码中引入字符串资源：" + this.getString(R.string.mobileshell).toString());
		
		// 使用 原始XML文件
		XmlResourceParser xrp = this.getResources().getXml(R.xml.students);
		try {
			while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if(xrp.getEventType() == XmlResourceParser.START_TAG) {
					String name = xrp.getName();
					if("student".equals(name)) {
						Log.i("student", "name=" + xrp.getAttributeValue(0) + ",age=" + xrp.getAttributeValue(1));
					}
				} else if(xrp.getEventType() == XmlPullParser.END_TAG) {
					
				} else if(xrp.getEventType() == XmlPullParser.TEXT) {
					
				}
				xrp.next();
			}
		} catch (XmlPullParserException e) {
			Log.e("tag", e.toString(), e);
		} catch (IOException e) {
			Log.e("tag", e.toString(), e);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * 响应选项菜单点击事件
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.about:
			aboutAlert("菜单资源使用测试！");
			break;
		case R.id.exit:
			exitAlert("确定要退出么？");
			break;
		default:
			break;
		}
		return true;
	}
	
	/**
	 * 弹出 About 窗口
	 * @param msg
	 */
	private void aboutAlert(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		.setCancelable(false)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	/**
	 * 弹出 Exit 窗口
	 * @param msg
	 */
	private void exitAlert(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this); // 模态对话框
		builder.setMessage(msg)
		.setCancelable(true) // 可以用返回键返回
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ResourceTestActivity.this.finish();
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
