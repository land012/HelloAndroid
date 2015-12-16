package com.example.helloworld.contentprovider;

import com.example.helloworld.R;
import android.annotation.TargetApi;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * 异步加载
 * 使用 LoaderManager 的方式加载联系人列表
 * @author 大洲
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CursorLoaderListFragment extends ListFragment implements
		View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
	
	ListView listView;
	EditText editText;
	
	SimpleCursorAdapter adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new SimpleCursorAdapter(this.getActivity(),
				R.layout.activity_contentprovider_listcontacts,
				null,
				new String[] { BaseColumns._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER},
				new int[] { R.id.tv_cid, R.id.tv_cname, R.id.tv_hasPhone },
				0);
		
		listView.setAdapter(adapter);
		this.getLoaderManager().initLoader(0, null, this);
	}
	
	public CursorLoaderListFragment(ListView listView, EditText editText) {
		super();
		this.listView = listView;
		this.editText = editText;
	}
	
	
	String[] projection = {
			BaseColumns._ID,
			ContactsContract.Contacts.DISPLAY_NAME,
			ContactsContract.Contacts.HAS_PHONE_NUMBER
	};
	String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
	String[] selectionArgs;
	String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		CursorLoader loader = new CursorLoader(this.getActivity(),
				ContactsContract.Contacts.CONTENT_URI,
				projection,
				selection,
				selectionArgs,
				sortOrder);
		return loader;
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
	}
	
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}
	
	@Override
	public void onClick(View v) {
		String name = "";
		if(editText.getText()!=null) {
			name = editText.getText().toString();
		}
		this.selectionArgs = new String[] {
				"%" + name + "%"
		};
		this.getLoaderManager().restartLoader(0, null, this);
	}

}