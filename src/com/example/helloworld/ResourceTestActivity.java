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
 * SG �й� pdf�̳�
 * 
 * @author asdf
 * ʹ�� colors.xml ��Դ�ļ�
 * ��ȡ xml �ļ�
 * ʹ�� menu/main.xml ���� ѡ��˵�
 */
public class ResourceTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resourcestest);
		
		this.getWindow().setBackgroundDrawableResource(R.color.lightwhite);
		
		Log.i("tag", "�ڴ����������ַ�����Դ��" + this.getString(R.string.mobileshell).toString());
		
		// ʹ�� ԭʼXML�ļ�
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
	 * ��Ӧѡ��˵�����¼�
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.about:
			aboutAlert("�˵���Դʹ�ò��ԣ�");
			break;
		case R.id.exit:
			exitAlert("ȷ��Ҫ�˳�ô��");
			break;
		default:
			break;
		}
		return true;
	}
	
	/**
	 * ���� About ����
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
	 * ���� Exit ����
	 * @param msg
	 */
	private void exitAlert(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this); // ģ̬�Ի���
		builder.setMessage(msg)
		.setCancelable(true) // �����÷��ؼ�����
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
