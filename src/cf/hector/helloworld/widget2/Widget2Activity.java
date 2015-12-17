package cf.hector.helloworld.widget2;

import cf.hector.helloworld.R;
import cf.hector.helloworld.widget2.activity.DateTimePickerActivity;
import cf.hector.helloworld.widget2.activity.GridViewTestActivity;
import cf.hector.helloworld.widget2.activity.GridViewTestActivity2;
import cf.hector.helloworld.widget2.activity.ListViewTestActivity;
import cf.hector.helloworld.widget2.activity.ListViewTestActivity2;
import cf.hector.helloworld.widget2.activity.ListViewTestActivity3;
import cf.hector.helloworld.widget2.activity.ListViewTestActivity4;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Widget2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget2);
		
		// »’∆⁄ ±º‰—°‘ÒøÚ
		Button btnDateTimerPickerTest = (Button)this.findViewById(R.id.test_datetimedialog);
		btnDateTimerPickerTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Widget2Activity.this, DateTimePickerActivity.class);
				Widget2Activity.this.startActivity(i);
			}
		});
		
		// ListView ≤‚ ‘
		Button btnListViewTest = (Button)this.findViewById(R.id.test_listview);
		btnListViewTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Widget2Activity.this, ListViewTestActivity.class);
				Widget2Activity.this.startActivity(i);
			}
		});
		
		// ListView ≤‚ ‘2
		Button btnListViewTest2 = (Button)this.findViewById(R.id.test_listview2);
		btnListViewTest2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Widget2Activity.this, ListViewTestActivity2.class);
				Widget2Activity.this.startActivity(i);
			}
		});
		// ListView ≤‚ ‘3
		Button btnListViewTest3 = (Button)this.findViewById(R.id.test_listview3);
		btnListViewTest3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Widget2Activity.this, ListViewTestActivity3.class);
				Widget2Activity.this.startActivity(i);
			}
		});
		
		// ListView ≤‚ ‘4
		Button btnListViewTest4 = (Button)this.findViewById(R.id.test_listview4);
		btnListViewTest4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Widget2Activity.this, ListViewTestActivity4.class);
				Widget2Activity.this.startActivity(i);
			}
		});
		
		// GridView ≤‚ ‘
		Button btnGridViewTest = (Button)this.findViewById(R.id.test_gridview);
		btnGridViewTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Widget2Activity.this, GridViewTestActivity.class);
				Widget2Activity.this.startActivity(i);
			}
		});
		// GridView ≤‚ ‘2
		Button btnGridViewTest2 = (Button)this.findViewById(R.id.test_gridview2);
		btnGridViewTest2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Widget2Activity.this, GridViewTestActivity2.class);
				Widget2Activity.this.startActivity(i);
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
