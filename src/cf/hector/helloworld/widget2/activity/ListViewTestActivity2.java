package cf.hector.helloworld.widget2.activity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
/**
 * ListView - Cursor
 * @author asdf
 * ��ϵ���б�
 * Deprecated �Ĵ���ʽ
 *
 */
@SuppressWarnings("deprecation")
public class ListViewTestActivity2 extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ListView2 - SimpleCursorAdapter ����
		// @Deprecated
		Cursor c = this.getContentResolver().query(People.CONTENT_URI, null, null, null, null);
		this.startManagingCursor(c);
		ListAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1,
				c,
				new String[]{People.NAME},
				new int[]{android.R.id.text1});
		
		this.setListAdapter(adapter);
	}

}
