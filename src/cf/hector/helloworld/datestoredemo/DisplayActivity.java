package cf.hector.helloworld.datestoredemo;


import cf.hector.helloworld.R;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DisplayActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setTitle("查看收藏信息");
		
		DBHelper dBHelper = new DBHelper(this);
		Cursor c = dBHelper.query();
		
		final String[] from = { "_id", "name", "url", "desc" };
		final int[] to = { R.id.site_id, R.id.site_name, R.id.site_url, R.id.site_desc };
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.activity_datastore_display,
				c,
				from,
				to,
				0);
		
		final ListView listView = this.getListView();
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Log.i(MainActivity.TAG, "arg2=" + arg2 + ",arg3=" + arg3);
				
				/**
				 * XXX 为什么这个是 id??
				 */
				final int site_id = (int)arg3;
				AlertDialog.Builder builder = new AlertDialog.Builder(DisplayActivity.this);
				builder.setMessage("确定要删除么？")
				       .setPositiveButton("是", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								DBHelper dBHelper = new DBHelper(DisplayActivity.this);
								dBHelper.del(site_id);
								
								Cursor c = dBHelper.query();
								SimpleCursorAdapter adapter = new SimpleCursorAdapter(DisplayActivity.this,
										R.layout.activity_datastore_display,
										c,
										from,
										to,
										0);
								listView.setAdapter(adapter);
								dBHelper.close();
							}
				       })
				       .setNegativeButton("否", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
							}
				       });
				builder.create().show();
			}
			
		});
		dBHelper.close();
	}

}
