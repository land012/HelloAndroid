package cf.hector.helloworld;


import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class EventTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventtest);
		
		final EditText etUsername, etUserpwd;
		final CheckBox cbIsautologin;
//		final Button btnLogin;
		
		etUsername = (EditText)this.findViewById(R.id.username);
		etUsername.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Toast.makeText(getApplicationContext(), etUsername.getText(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		etUserpwd = (EditText)this.findViewById(R.id.userpwd);
		etUserpwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(EventTestActivity.this.getApplicationContext(),
						etUserpwd.getText().toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
		
		cbIsautologin = (CheckBox)this.findViewById(R.id.isautologin);
		cbIsautologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(getApplicationContext(), cbIsautologin.isChecked() + "", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
