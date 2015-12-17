package cf.hector.helloworld.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	
	private IPerson.Stub iperson = new IPersonImpl();

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("tag", "----- onBind -----");
		return iperson;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("tag", "----- onCreate -----");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("tag", "----- onStartCommand -----");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i("tag", "----- onStart -----");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("tag", "----- onUnbind -----");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("tag", "----- onDestroy -----");
	}

}
