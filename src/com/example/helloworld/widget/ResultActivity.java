package com.example.helloworld.widget;

import java.util.ArrayList;
import java.util.List;

import com.example.helloworld.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_result);
		Intent intent = this.getIntent();
		Bundle data = intent.getBundleExtra("data");
		
		List<String> list = new ArrayList<String>();
		list.add(data.getString("username"));
		list.add(data.getString("userpwd"));
		list.add(data.getString("gender"));
		list.add(data.getString("married"));
		list.add(data.getString("hobby"));
		list.add(data.getString("position"));
		
		ArrayAdapter<String> adpRegisterInfo = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked,
				list);
		
		ListView lvRegisterInfo = (ListView)this.findViewById(R.id.register_info);
		lvRegisterInfo.setAdapter(adpRegisterInfo);
	}

}
