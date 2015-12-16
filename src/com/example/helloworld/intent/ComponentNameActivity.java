package com.example.helloworld.intent;


import com.example.helloworld.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class ComponentNameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_component_name);
		
		Intent intent = this.getIntent();
		ComponentName component = intent.getComponent();
		
		Log.i("tag", component.getPackageName());    // com.umbrella.sg10intenttest
		Log.i("tag", component.getClassName());      // com.umbrella.sg10intenttest.activity.ComponentNameActivity
		Log.i("tag", component.getShortClassName()); // .activity.ComponentNameActivity
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.component_name, menu);
		return true;
	}

}
