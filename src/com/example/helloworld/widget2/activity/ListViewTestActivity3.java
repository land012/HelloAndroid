package com.example.helloworld.widget2.activity;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

/**
 * ListView - CursorLoader
 * @author asdf
 * 不明白为什么这么实现？？？？？？？？？？？？？？
 *
 */
public class ListViewTestActivity3 extends ListActivity implements LoaderCallbacks<Cursor> {
	
	SimpleCursorAdapter scAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ListView3 - CursorLoader
		scAdapter = new SimpleCursorAdapter(this,
			android.R.layout.simple_list_item_1,
			null,
			new String[]{ ContactsContract.Contacts.DISPLAY_NAME },
			new int[]{ android.R.id.text1 },
			0);
		
		this.setListAdapter(scAdapter);
		this.getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(this,
				ContactsContract.Contacts.CONTENT_URI,
				null,
				null,
				null,
				null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		this.scAdapter.swapCursor(arg1);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		this.scAdapter.swapCursor(null);
	}

}
