package com.example.helloworld.broadcastreceiverdemo;

import java.util.Calendar;

import com.example.helloworld.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private static final String RECEIVER_MY = "com.umbrella.sg12broadcastreceivertest.action.RECEIVER_MY";
	
	private NotificationManager nm;
	private Notification n;
	private static final int ID = 1;
	
	private static final String RECEIVER_ALARM = "com.umbrella.sg12broadcastreceivertest.action.RECEIVER_ALARM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * 自定义 Broeadcast Receiver 测试
		 */
		Button sendBroadcastBtn = (Button)this.findViewById(R.id.send_broadcast);
		sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setAction(RECEIVER_MY);
				intent1.putExtra("msg", "地瓜，地瓜，我是土豆！");
				MainActivity.this.sendBroadcast(intent1);
			}
		});
		
		/**
		 * Notification 测试
		 */
		nm = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
		n = new Notification();
		n.icon = R.drawable.ic_launcher;
		n.tickerText = "Notification Test";
		n.when = System.currentTimeMillis();
		
		Button notificationTestSendBtn = (Button)this.findViewById(R.id.notification_test_send);
		notificationTestSendBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				n.setLatestEventInfo(MainActivity.this, "my title", "my content", pi);
				nm.notify(ID, n);
			}
		});
		
		Button notificationTestCancelBtn = (Button)this.findViewById(R.id.notification_test_cancel);
		notificationTestCancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nm.cancel(ID);
			}
		});
		
		/**
		 * AlarmManager 测试
		 */
		final AlarmManager am = (AlarmManager)MainActivity.this.getSystemService(ALARM_SERVICE); // 闹钟管理
		Intent intent3 = new Intent();
		intent3.setAction(MainActivity.RECEIVER_ALARM);
		intent3.putExtra("msg", "闹钟响了！");
		final PendingIntent pi3 = PendingIntent.getBroadcast(MainActivity.this, 0, intent3, 0);
		
		Button alarmSetBtn = (Button)this.findViewById(R.id.alarm_set);
		alarmSetBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 10);
				cal.set(Calendar.MINUTE, 12);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				long starttime = cal.getTimeInMillis(); // 发动时间
				long interval = 5 * 1000; // 发动间隔
				am.setRepeating(AlarmManager.RTC_WAKEUP, starttime, interval, pi3);
			}
		});
		
		Button alarmCancelBtn = (Button)this.findViewById(R.id.alarm_cancel);
		alarmCancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				am.cancel(pi3);
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
