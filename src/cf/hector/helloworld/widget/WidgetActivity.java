package cf.hector.helloworld.widget;

import cf.hector.helloworld.R;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class WidgetActivity extends Activity {
	
	private EditText etUsername;
	private EditText etUserpwd;
	private RadioButton rdMale;
	private ToggleButton tgMarried;
	private CheckBox cbReading;
	private CheckBox cbSwimming;
	private Spinner spnPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_widget);
		
		Log.i("tag", Build.VERSION.SDK_INT + ""); // 18
		Log.i("tag", "你好");
		
		// 常用View
		spnPosition = (Spinner)this.findViewById(R.id.position); // 下拉列表(职务)
		ArrayAdapter<String> adpPosition = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				this.getResources().getStringArray(R.array.position));
		
//		ArrayAdapter<String> adpPosition = new ArrayAdapter<String>(this,
//				R.array.position,
//				android.R.layout.simple_spinner_item);
		spnPosition.setAdapter(adpPosition);
		
		etUsername = (EditText)this.findViewById(R.id.username);
		etUserpwd = (EditText)this.findViewById(R.id.userpwd);
		rdMale = (RadioButton)this.findViewById(R.id.male);
		tgMarried = (ToggleButton)this.findViewById(R.id.married);
		cbReading = (CheckBox)this.findViewById(R.id.reading);
		cbSwimming = (CheckBox)this.findViewById(R.id.swimming);
		
		Button btnRegister = (Button)this.findViewById(R.id.register);
		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putString("username", "用户名：" + etUsername.getText().toString());
				b.putString("userpwd", "密码：" + etUserpwd.getText().toString());
				if(rdMale.isChecked()) {
					b.putString("gender", "性别：男");
				} else {
					b.putString("gender", "性别：女");
				}
				if(tgMarried.isChecked()) {
					b.putString("married", "婚姻状态：是");
				} else {
					b.putString("married", "婚姻状态：否");
				}
				StringBuilder hobby = new StringBuilder("爱好：");
				if(cbReading.isChecked()) {
					hobby.append(" 读书");
				}
				if(cbSwimming.isChecked()) {
					hobby.append(" 游泳");
				}
				b.putString("hobby", hobby.toString());
				b.putString("position", spnPosition.getSelectedItem().toString());
				
				Intent intent = new Intent(WidgetActivity.this, ResultActivity.class);
				intent.putExtra("data", b);
				WidgetActivity.this.startActivity(intent);
			}
		});
		
		// 自动完成文本框
		AutoCompleteTextView actvPosition = (AutoCompleteTextView)this.findViewById(R.id.actv_position);
		ArrayAdapter<String> adpAnimal = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,
				this.getResources().getStringArray(R.array.animal));
		actvPosition.setAdapter(adpAnimal);
		
		// 选项卡测试
		Button btnTabTest = (Button)this.findViewById(R.id.tab_test);
		btnTabTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WidgetActivity.this, TabTestActivity.class);
				WidgetActivity.this.startActivity(intent);
			}
		});
		
		// 进度条测试
		Button btnProgressbarTest = (Button)this.findViewById(R.id.progressbar_test);
		btnProgressbarTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(WidgetActivity.this, ProgressBarActivity.class);
				WidgetActivity.this.startActivity(i);
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
