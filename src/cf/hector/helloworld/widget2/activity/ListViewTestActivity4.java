package cf.hector.helloworld.widget2.activity;



import cf.hector.helloworld.R;
import cf.hector.helloworld.widget2.fragment.CursorLoaderListFragment;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ListViewTestActivity4 extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_listview4);
		
		CursorLoaderListFragment fragment = new CursorLoaderListFragment();
		fragment.setListShown(true);
		
	}

}
