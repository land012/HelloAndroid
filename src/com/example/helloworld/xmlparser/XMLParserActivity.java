package com.example.helloworld.xmlparser;


import com.example.helloworld.R;
import com.example.helloworld.R.layout;
import com.example.helloworld.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class XMLParserActivity extends Activity {
	
	public static final String TAG = "ic07_xmlparser";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xmlparser);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
