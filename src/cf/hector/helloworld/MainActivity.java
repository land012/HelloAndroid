package cf.hector.helloworld;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
/**
 * ITCAST ���ǲ��ͽ̳�
 * @author ����
 *
 */
public class MainActivity extends Activity {
	
	public static final String EXTRA_MESSAGE = "com.example.helloworld.MESSAGE";
	
	/**
	 * ����������ʾ����
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * ��ʾ  Action Bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    /**
     * ����� ActionBar �ϵĲ˵�ʱ����Ӧ
     */
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_search:
				openSearch();
				return true;
			case R.id.action_settings:
				openSettings();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
    
    private void openSearch() {
    	
    }
    
    private void openSettings() {
    	
    }

	/**
     * ��Ӧ Send ��ť
     */
    public void sendMessage(View view) {
    	EditText editText = (EditText)findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
}
