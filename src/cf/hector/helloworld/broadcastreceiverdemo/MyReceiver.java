package cf.hector.helloworld.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 接收自定义广播，并展示
 * @author asdf
 *
 */
public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Log.i("tag", arg1.getStringExtra("msg"));
		
		Intent intent = new Intent(arg0, DisplayActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startActivity(intent);
	}

}
