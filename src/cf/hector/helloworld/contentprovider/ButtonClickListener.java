package cf.hector.helloworld.contentprovider;


import cf.hector.helloworld.R;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ButtonClickListener implements View.OnClickListener {
	
	Context context;
	ListView listView;
	EditText editText;

	public ButtonClickListener(Context context, ListView listView, EditText editText) {
		super();
		this.context = context;
		this.listView = listView;
		this.editText = editText;
	}

	@Override
	public void onClick(View v) {
		String contactname = editText.getText().toString();
		Toast.makeText(this.context, contactname, Toast.LENGTH_LONG).show();
		String[] projection = {
				BaseColumns._ID,
				ContactsContract.Contacts.DISPLAY_NAME
		};
		String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
		String[] selectionArgs = {
				"%" + contactname + "%"
		};
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;
		
		ContentResolver cr = context.getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
		while(cur.moveToNext()) {
			Log.i(MainActivity.TAG, cur.getString(cur.getColumnIndex(BaseColumns._ID))
					+ "," + cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
		}
		
		ListAdapter adapter = new SimpleCursorAdapter(context,
				R.layout.activity_contentprovider_listcontacts,
				cur,
				new String[]{
					BaseColumns._ID,
					ContactsContract.Contacts.DISPLAY_NAME},
				new int[]{
					R.id.tv_cid,
					R.id.tv_cname},
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		
		listView.setAdapter(adapter);
	}

}
