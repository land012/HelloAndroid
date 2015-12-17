package cf.hector.helloworld.broadcastreceiverdemo;



import cf.hector.helloworld.R;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class DisplayActivity extends Activity {
	
	private NotificationManager nm;
	private Notification n;
	private static final int ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		/*
		 * 显示通知
		 */
		nm = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
		n = new Notification.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setTicker("DisplayActivity Notification Test")
				.setWhen(System.currentTimeMillis()) // 默认值
				.setContentTitle("display notify title")
				.setContentText("display notify content")
				.build();
		
		nm.notify(ID, n);
		
		/**
		 * 取消通知
		 */
		Button notiDisplayCancelTestBtn = (Button)this.findViewById(R.id.noti_display_cancel_test);
		notiDisplayCancelTestBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nm.cancel(ID);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

}
