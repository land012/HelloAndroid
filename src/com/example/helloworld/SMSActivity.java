package com.example.helloworld;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		Button sendButton = (Button)this.findViewById(R.id.sendbutton);
		sendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText mobileText = (EditText)SMSActivity.this.findViewById(R.id.mobile);
				EditText contentText = (EditText)SMSActivity.this.findViewById(R.id.content);
				String mobile = mobileText.getText().toString();
				String content = contentText.getText().toString();
				
				SmsManager smsManager = SmsManager.getDefault();
				List<String> contents = smsManager.divideMessage(content);
				for(String text : contents) {
					smsManager.sendTextMessage(mobile, null, text, null, null);
				}
				Toast toast = Toast.makeText(SMSActivity.this, R.string.sendcomplete, Toast.LENGTH_LONG);
				toast.show();
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
