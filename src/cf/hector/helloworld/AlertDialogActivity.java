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
		
		// É¾³ý
		Button btnDelete = (Button)this.findViewById(R.id.delete);
		btnDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
				builder.setMessage("È·¶¨ÒªÉ¾³ýÃ´£¿")
					.setCancelable(false)
					.setPositiveButton("ÊÇ", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(AlertDialogActivity.this, "É¾³ý³É¹¦", Toast.LENGTH_LONG).show();
						}
					})
					.setNegativeButton("·ñ", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(AlertDialogActivity.this, "È¡ÏûÉ¾³ý", Toast.LENGTH_LONG).show();
						}
					});
				builder.create().show();
			}
		});
		
		// µã²Í
		final String[] dishes = { "°Â¶ûÁ¼¼¦ÍÈ±¤", "ÏãÀ±¼¦ÍÈ±¤", "¼¦Ã×»¨" };
		
		Button btnOrder1 = (Button)this.findViewById(R.id.order1);
		btnOrder1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
				builder.setTitle("Çëµã²Í")
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
				builder.setTitle("Çëµã²Í")
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
