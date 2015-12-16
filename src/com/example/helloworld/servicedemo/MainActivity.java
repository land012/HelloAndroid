package com.example.helloworld.servicedemo;

import com.example.helloworld.R;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
/**
 * Service 测试
 * @author asdf
 *
 */
public class MainActivity extends Activity {
	
	private static final String ACTION_MYSERVICE = "com.umbrella.sg11servicetest.action.SERVICE_MY";
	
	private IPerson iperson;
	
	private ServiceConnection serviceConn = new ServiceConnection() {
		
		@Override
		synchronized public void onServiceConnected(ComponentName nae,
				IBinder service) {
			
			iperson = IPerson.Stub.asInterface(service);
			if(iperson != null) {
				try {
					iperson.setName("小李");
					iperson.setAge(18);
					Log.i("tag", iperson.display());
				} catch (RemoteException e) {
					Log.i("tag", "RemoteException", e);
				}
			}
			
			Log.i("tag", "MyService 连接成功！");
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("tag", "MyService 断开连接！");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_servicedemo);
		
		/*
		 * 启动 MyService
		 */
		Button startMyserviceBtn = (Button)this.findViewById(R.id.start_myservice);
		startMyserviceBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION_MYSERVICE);
				MainActivity.this.startService(intent);
			}
		});
		
		/*
		 * 停止 MyService
		 */
		Button stopMyserviceBtn = (Button)this.findViewById(R.id.stop_myservice);
		stopMyserviceBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION_MYSERVICE);
				MainActivity.this.stopService(intent);
			}
		});
		
		/*
		 * 绑定 MyService
		 */
		Button bindMyserviceBtn = (Button)this.findViewById(R.id.bind_myservice);
		bindMyserviceBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION_MYSERVICE);
				MainActivity.this.bindService(intent, serviceConn, Service.BIND_AUTO_CREATE);
			}
		});
		
		/*
		 * 解绑 MyService
		 */
		Button unbindMyserviceBtn = (Button)this.findViewById(R.id.unbind_myservice);
		unbindMyserviceBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION_MYSERVICE);
				MainActivity.this.unbindService(serviceConn);
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
