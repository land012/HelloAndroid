package cf.hector.helloworld;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * 
 * @author asdf
 * RelativeLayout 布局
 * 
 *
 */
public class LayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);
		
	}
	
	/**
	 * 创建选项菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
