package cf.hector.helloworld.widget2.fragment;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;
/**
 * ??????????????
 * @author asdf
 * ‘ı√¥ π”√£ø£ø£ø£ø£ø£ø£ø£ø£ø
 *
 */
public class CursorLoaderListFragment extends ListFragment implements
		LoaderCallbacks<Cursor> {
	
	private SimpleCursorAdapter scAdapter = null;
	
	// These are the Contacts rows that we will retrieve
    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};

    // This is the select criteria
    static final String SELECTION = "((" + 
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		this.scAdapter = new SimpleCursorAdapter(this.getActivity(),
				android.R.layout.simple_list_item_1,
				null,
				new String[] { ContactsContract.Contacts.DISPLAY_NAME },
				new int[] { android.R.id.text1 },
				0);
		
		this.setListAdapter(scAdapter);
		this.setListShown(false);
		this.getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(this.getActivity(),
				ContactsContract.Contacts.CONTENT_URI,
				null,
				null,
				null,
				null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		scAdapter.swapCursor(arg1);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		scAdapter.swapCursor(null);
	}

}
