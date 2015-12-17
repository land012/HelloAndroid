package cf.hector.helloworld;



import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ActivResultActivity extends Activity {
	
	private static final String[] PROJECTION = {
		ContactsContract.Contacts._ID,
		ContactsContract.Contacts.DISPLAY_NAME
	};
	
	private static final String SELECTION = ContactsContract.Contacts.DISPLAY_NAME + "=?";
	
	private static String[] args;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activ_result);
		
		TextView contactsinfoTv = (TextView)this.findViewById(R.id.contactsinfo);
		
		Bundle data = this.getIntent().getExtras();
		String contactsnameP = data.getString("contactsname");
		
		args = new String[] {
			contactsnameP
		};
		
		Cursor contactsCursor = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
				PROJECTION,
				SELECTION,
				args,
				ContactsContract.Contacts.DISPLAY_NAME + " ASC");
		if(contactsCursor != null) {
			if(contactsCursor.moveToNext()) {
				String contactsId = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts._ID));
				String contactsName = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				
				Log.i("tag", contactsId + "-" + contactsName);
				
				Cursor phoneCursor = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
						new String[] { contactsId },
						null);
				if(phoneCursor != null) {
					if(phoneCursor.moveToNext()) {
						String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						String res = contactsId + "-" + contactsName + "-" + phone;
						Log.i("tag", "联系人信息：" + res);
						contactsinfoTv.setText(res);
					} else {
						Log.i("tag", "找不到联系人：" + contactsnameP + " 的手机号码2");
					}
				} else {
					Log.i("tag", "找不到联系人：" + contactsnameP + " 的手机号码1");
				}
			} else {
				Log.i("tag", "找不到联系人：" + contactsnameP + " 2");
			}
		} else {
			Log.i("tag", "找不到联系人：" + contactsnameP + " 1");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
