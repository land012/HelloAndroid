package com.example.helloworld;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);
		
		System.out.println("程序启动！");
		
		EditText txtPhonenum = (EditText)this.findViewById(R.id.phonenum);
		
		Button btnDial = (Button)this.findViewById(R.id.dial);
		btnDial.setOnClickListener(new BtnDialListen(txtPhonenum));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private final class BtnDialListen implements View.OnClickListener {
		
		private EditText txtPhonenum;
		
		public BtnDialListen(EditText txtPhonenum) {
			this.txtPhonenum = txtPhonenum;
		}

		@Override
		public void onClick(View v) {
			String phonenum = this.txtPhonenum.getText().toString();
			System.out.println("要拨打的手机号码：" + phonenum);
//			Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phonenum));
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenum));
			startActivity(intent);
		}
		
	}

}
