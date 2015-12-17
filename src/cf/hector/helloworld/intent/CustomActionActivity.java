package cf.hector.helloworld.intent;



import cf.hector.helloworld.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class CustomActionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_action);
        
        Intent intent = this.getIntent();
        if(intent.getAction()!= null) {
        	Log.i("tag", intent.getAction());
        }
        if(intent.getData() != null) {
        	Log.i("tag", intent.getData().toString());
        }
        if(intent.getCategories() != null) {
        	for(String c : intent.getCategories()) {
            	Log.i("tag", c);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_action, menu);
        return true;
    }
    
}
