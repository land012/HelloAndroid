package com.example.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activ);
		
		Log.i("lifecycle", "--------- onCreate ---------");
		
		final EditText contactsnameEt = (EditText)this.findViewById(R.id.contactsname);
		
		/*
		 * Activity 之间传递参数
		 */
		Button confirmBtn = (Button)this.findViewById(R.id.confirm);
		confirmBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle data = new Bundle();
				data.putString("contactsname", contactsnameEt.getText().toString());
				
				Intent intent = new Intent(ActivDemoActivity.this, ActivResultActivity.class);
				intent.putExtras(data);
				ActivDemoActivity.this.startActivity(intent);
			}
		});
		
		/*
		 * Activity 生命周期
		 */
		Button activityLifecycleBtn = (Button)this.findViewById(R.id.activity_lifecycle);
		activityLifecycleBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActivDemoActivity.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Log.i("lifecycle", "--------- onStart ---------");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("lifecycle", "--------- onRestart ---------");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("lifecycle", "--------- onResume ---------");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("lifecycle", "--------- onPause ---------");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("lifecycle", "--------- onStop ---------");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("lifecycle", "--------- onDestroy ---------");
	}

}
