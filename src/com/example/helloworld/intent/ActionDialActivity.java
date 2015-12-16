package com.example.helloworld.intent;


import com.example.helloworld.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActionDialActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_action_dial);
		
		String[] menus = { "�鿴��ϵ����Ϣ",
				"�༭��ϵ����Ϣ",
				"��ʾ����绰����",
				"����绰",
				"���������",
				"���ʵ�ͼ",
				"���ʼ�"};
		ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				menus);
		this.setListAdapter(adapter);
		this.getListView().setTextFilterEnabled(true);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		String data;
		
		switch(position) {
		case 0: // ����Ҫ����Ȩ��
			data = "content://contacts/people/1";
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(data));
			this.startActivity(intent);
			break;
		case 1: // ����Ҫ����Ȩ��
			data = "content://contacts/people/1";
			intent.setAction(Intent.ACTION_EDIT);
			intent.setData(Uri.parse(data));
			this.startActivity(intent);
			break;
		case 2: // ����Ҫ����Ȩ��
			data = "tel:111111";
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(Uri.parse(data));
			this.startActivity(intent);
			break;
		case 3: // ��Ҫ����Ȩ�� android.permission.CALL_PHONE
			data = "tel:111111";
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse(data));
			this.startActivity(intent);
			break;
		case 4: // 
			data = "http://www.baidu.com";
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(data));
			this.startActivity(intent);
			break;
		case 5:
			data = "geo:39, 116";
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
			this.startActivity(intent);
			break;
		case 6:
			String tomail = "shin.12@163.com";
			String emailsubject = "�ʼ�����";
			String emailcontent = "�ʼ�����";
			intent.setAction(Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(Intent.EXTRA_EMAIL, tomail);
			intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
			intent.putExtra(Intent.EXTRA_TEXT, emailcontent);
			this.startActivity(Intent.createChooser(intent, "�����ʼ�..."));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_dial, menu);
		return true;
	}

}
