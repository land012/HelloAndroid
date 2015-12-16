package com.example.helloworld.widget;

import com.example.helloworld.R;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabTestActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TabHost th = this.getTabHost();
		
		LayoutInflater li = LayoutInflater.from(this);
		li.inflate(R.layout.activity_tab, th.getTabContentView(), true);
		
		TabHost.TabSpec ts1 = th.newTabSpec("all").setIndicator("label1").setContent(R.id.tabtest_tv1);
		TabHost.TabSpec ts2 = th.newTabSpec("ok").setIndicator("label2").setContent(R.id.tabtest_tv2);
		TabHost.TabSpec ts3 = th.newTabSpec("cancel").setIndicator("label3").setContent(R.id.tabtest_tv3);
		th.addTab(ts1);
		th.addTab(ts2);
		th.addTab(ts3);
	}

}
