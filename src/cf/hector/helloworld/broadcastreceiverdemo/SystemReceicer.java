package cf.hector.helloworld.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SystemReceicer extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("tag", "ϵͳ������ϡ�����������");
	}

}
