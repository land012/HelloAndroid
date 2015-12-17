package cf.hector.helloworld.filedemo;



import cf.hector.helloworld.R;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * IC itcast 传智播客 视频教程
 * @author 大洲
 *
 */
public class FileActivity extends Activity {
	
	private FileService fileService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		
		fileService = new FileService(this);
		
		Button save = (Button)this.findViewById(R.id.save);
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText filename = (EditText)FileActivity.this.findViewById(R.id.filename);
				EditText filecontent = (EditText)FileActivity.this.findViewById(R.id.filecontent);
				try {
					fileService.save(filename.getText().toString(), filecontent.getText().toString());
					Toast.makeText(FileActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Log.i("tag", e.toString(), e);
					Toast.makeText(FileActivity.this, R.string.fail, Toast.LENGTH_SHORT).show();
				}
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
