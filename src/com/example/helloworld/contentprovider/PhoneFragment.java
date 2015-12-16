package com.example.helloworld.contentprovider;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * 获取通讯录电话号
 * 当同一个人，有两个电话号码时，会显示两条记录
 * 只显示有电话号码的记录
 * @author 大洲
 *
 */
public class PhoneFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = {
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER
		};
		
		String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
		
		CursorLoader loader = new CursorLoader(this.getActivity(),
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				projection,
				null,
				null,
				sortOrder
				);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		Log.i(MainActivity.TAG, "I am PhoneFragment.onLoadFinished");
		while(data.moveToNext()) {
			Log.i(MainActivity.TAG, data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
					+ "," + data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		
	}

}
