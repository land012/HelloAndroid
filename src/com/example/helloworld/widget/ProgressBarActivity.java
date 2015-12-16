package com.example.helloworld.widget;

import com.example.helloworld.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ��������������������� setContentView ǰ��
		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		this.setContentView(R.layout.activity_progressbar);
		
		// �Ի��������
		Button btnDialogProgressbar = (Button)this.findViewById(R.id.dialog_progressbar);
		btnDialogProgressbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProgressBarActivity.this.showDialog(0);
			}
		});
		
		// ������������
		Button btnTitleProgressbar = (Button)this.findViewById(R.id.title_progressbar);
		btnTitleProgressbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��ʾ������
				ProgressBarActivity.this.setProgressBarIndeterminateVisibility(true);
			}
		});
		
		// ˮƽ������
		final ProgressBar pbHorizen = (ProgressBar)this.findViewById(R.id.horizen_progressbar);
		Button btnIncrease = (Button)this.findViewById(R.id.increase);
		btnIncrease.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pbHorizen.incrementProgressBy(10);
			}
		});
		Button btnDecrease = (Button)this.findViewById(R.id.decrease);
		btnDecrease.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pbHorizen.incrementProgressBy(-10);
			}
		});
		
	}
	
	/**
	 * �Ի��������
	 */
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		ProgressDialog d = new ProgressDialog(this);
		d.setIndeterminate(true);
		d.setCancelable(true);
		d.setTitle("����");
		d.setMessage("���ݼ�����...");
		return d;
	}

}
