package cf.hector.helloworld.widget2.activity;

import cf.hector.helloworld.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * ListView - ArrayAdapter
 * @author asdf
 * ¾²Ì¬ List
 *
 */
public class ListViewTestActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ListView1 - ArrayAdapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				this.getResources().getStringArray(R.array.animal));
		this.setListAdapter(adapter);
	}

}
