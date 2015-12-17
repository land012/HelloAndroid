package cf.hector.helloworld.widget2.activity;

import cf.hector.helloworld.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GridViewTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_gridview);
		
		GridView gvTest = (GridView)this.findViewById(R.id.gv_fruit);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_gallery_item,
				this.getResources().getStringArray(R.array.fruit));
		
		gvTest.setAdapter(adapter);
		
	}

}
