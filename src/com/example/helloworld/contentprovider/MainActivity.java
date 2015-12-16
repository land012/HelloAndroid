package com.example.helloworld.contentprovider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import com.example.helloworld.R;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore.Images;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	
	public static final String TAG = "sg14contentprovidertest";
	public static final int FRAG0 = 0;
	public static final int FRAG1 = 1;
	
	ContentResolver cr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_contentprovider);
		
		cr = this.getContentResolver();
		
		/**
		 * ������ģ����ѯ��ϵ��
		 * �����б���ʾ
		 */
		EditText etContactname = (EditText)this.findViewById(R.id.et_contactname);
		ListView lvContacts = (ListView)this.findViewById(R.id.lv_contacts);
		Button btnSearch = (Button)this.findViewById(R.id.btn_search);
		
//		btnSearch.setOnClickListener(new ButtonClickListener(this, lvContacts, etContactname));
		
		FragmentManager fm = this.getFragmentManager();
		if(fm.findFragmentById(FRAG0) == null) {
			CursorLoaderListFragment frag = new CursorLoaderListFragment(lvContacts, etContactname);
			fm.beginTransaction().add(FRAG0, frag).commit();
			btnSearch.setOnClickListener(frag);
		}
		// add Fragment ʱ�����붼�� 0
		if(fm.findFragmentById(FRAG0) == null) {
			PhoneFragment frag = new PhoneFragment();
			fm.beginTransaction().add(FRAG0, frag).commit();
		}
		
		/**
		 * ��ѯϵͳ ContentProvider
		 */
//		query();
		
		/**
		 * ��ϵͳContentProvider�������
		 */
//		insert();
		
		/**
		 * ��ϵͳ ContentProvider ���ͼƬ����
		 */
//		insert2();
		
		
		/**
		 * ���Զ������ݲ�������
		 */
//		ctInsert();
//		ctQuery();
//		ctUpdate();
		
//		ctDelete();
//		
//		ctQuery();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * ��ѯ��ϵ���б�
	 */
	public void query() {
		Log.i(TAG, "��ѯ��ϵ���б�");
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		// ��ѯ���ֶ�
		String[] projection = { ContactsContract.Data._ID,
				ContactsContract.Data.DISPLAY_NAME,
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME };
		
//		String selection = null;
//		String[] selectionArgs = { };
//		String selection = ContactsContract.Contacts.DISPLAY_NAME + " = ?"; 
//		String[] selectionArgs = { "���������" };
		// ��ѯ����
		String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
		// ��������
		String[] selectionArgs = { "%" + "��" + "%" };
		// ����
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;
		
		Cursor c = cr.query(uri, projection, selection, selectionArgs, sortOrder);
		while(c.moveToNext()) {
			String log = "id=" + c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))
					+ ",name=" + c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
					+ ",[0]=" + c.getString(0) // _ID
					+ ",[1]=" + c.getString(1) // DISPLAY_NAME
					+ ",[2]=" + c.getString(2) // _ID
					+ ",[3]=" + c.getString(3); // DISPLAY_NAME
			Log.i(TAG, log);
		}
	}
	
	/**
	 * �����ϵ��
	 */
	public void insert() {
		/**
		 * XXX ��Ȼ������Ϊʲô�����������
		 * ֻ�����������������Ӻ���
		 */
		Uri rawUri = cr.insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
		long rawContactId = ContentUris.parseId(rawUri);
		
		ContentValues values = new ContentValues();
		values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
		values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
		values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "jerry");
		// ������仰��ֻ����Ӻ��룬�����������
//		values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "222222");
		Uri uri = ContactsContract.Data.CONTENT_URI;
		Uri res = cr.insert(uri, values);
		Log.i(TAG, "��ϵ���������ˣ�" + res.toString());
	}
	
	/**
	 * ���ͼƬ
	 */
	public void insert2() {
		File sdcard = Environment.getExternalStorageDirectory(); // /storage/sdcard
		try {
			Log.i(TAG, "sdcard·��=" + sdcard.getCanonicalPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		File pic = new File(sdcard, "Download/1210091I03.jpg");
		Log.i(TAG, pic.getAbsolutePath());
		try {
			Log.i(TAG, pic.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i(TAG, pic.getPath());
		Log.i(TAG, pic.toString());
		
		ContentValues values = new ContentValues();
		values.put(Images.Media.DISPLAY_NAME, "pic");
		values.put(Images.Media.DESCRIPTION, "pic_desc");
		values.put(Images.Media.MIME_TYPE, "image/jpeg");
//		values.put(Images.Media.DATA, pic.getAbsolutePath());
		// Images.Media.EXTERNAL_CONTENT_URI content://media/external/images/media
		Uri uri = cr.insert(Images.Media.EXTERNAL_CONTENT_URI, values);
		if(uri != null) {
			Log.i(TAG, uri.toString()); // content://media/external/images/media/29
		} else {
			Log.i(TAG, "uri is null");
		}
		
		Bitmap srcBitmap = BitmapFactory.decodeFile(pic.getAbsolutePath());
		OutputStream os = null;
		try {
			os = cr.openOutputStream(uri);
			srcBitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���Զ��� ContentProvider ��������
	 */
	public void ctInsert() {
		ContentValues values = new ContentValues();
		values.put(Employees.Employee.NAME, "jim");
		values.put(Employees.Employee.GENDER, "male");
		values.put(Employees.Employee.AGE, "20");
		this.getContentResolver().insert(Employees.Employee.CONTENNT_URI, values);
	}
	
	/**
	 * 
	 */
	public void ctQuery() {
		Uri uri1 = Uri.parse(Employees.Employee.CONTENNT_URI.toString() + "/1");
		Cursor c1 = this.getContentResolver().query(uri1, null, null, null, null);
		while(c1.moveToNext()) {
			Log.i(TAG, "c1=" + c1.getString(c1.getColumnIndex(Employees.Employee._ID))
					+ "|" + c1.getString(c1.getColumnIndex(Employees.Employee.NAME)));
		}
		
		Cursor c2 = this.getContentResolver().query(Employees.Employee.CONTENNT_URI, null, null, null, null);
		while(c2.moveToNext()) {
			Log.i(TAG, "c2=" + c2.getString(c2.getColumnIndex(Employees.Employee._ID))
					+ "|" + c2.getString(c2.getColumnIndex(Employees.Employee.NAME)));
		}
	}
	
	public void ctUpdate() {
		ContentValues values = new ContentValues();
		values.put(Employees.Employee.NAME, "david");
		int count = this.getContentResolver().update(Employees.Employee.CONTENNT_URI, values, null, null);
		Log.i(TAG, "count=" + count);
	}
	
	public void ctDelete() {
		Uri uri = Uri.parse(Employees.Employee.CONTENNT_URI.toString() + "/1");
		int count = this.getContentResolver().delete(uri, null, null);
		Log.i(TAG, "count=" + count);
	}
	
}
