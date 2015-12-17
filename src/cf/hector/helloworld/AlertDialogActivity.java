package cf.hector.helloworld;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alertdialog);
		
		// ɾ��
		Button btnDelete = (Button)this.findViewById(R.id.delete);
		btnDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
				builder.setMessage("ȷ��Ҫɾ��ô��")
					.setCancelable(false)
					.setPositiveButton("��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(AlertDialogActivity.this, "ɾ���ɹ�", Toast.LENGTH_LONG).show();
						}
					})
					.setNegativeButton("��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(AlertDialogActivity.this, "ȡ��ɾ��", Toast.LENGTH_LONG).show();
						}
					});
				builder.create().show();
			}
		});
		
		// ���
		final String[] dishes = { "�¶������ȱ�", "�������ȱ�", "���׻�" };
		
		Button btnOrder1 = (Button)this.findViewById(R.id.order1);
		btnOrder1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
				builder.setTitle("����")
				.setItems(dishes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(AlertDialogActivity.this, which + "-" + dishes[which], Toast.LENGTH_LONG).show();
					}
				});
				builder.create().show();
			}
		});
		
		Button btnOrder2 = (Button)this.findViewById(R.id.order2);
		btnOrder2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
				builder.setTitle("����")
				.setSingleChoiceItems(R.array.dishes, -1, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String[] arr1 = AlertDialogActivity.this.getResources().getStringArray(R.array.dishes);
						Toast.makeText(AlertDialogActivity.this, which + "-" + arr1[which], Toast.LENGTH_LONG).show();
					}
				});
				builder.create().show();
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
