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
		
		// 标题栏进度条，必须放在 setContentView 前面
		this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		this.setContentView(R.layout.activity_progressbar);
		
		// 对话框进度条
		Button btnDialogProgressbar = (Button)this.findViewById(R.id.dialog_progressbar);
		btnDialogProgressbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProgressBarActivity.this.showDialog(0);
			}
		});
		
		// 标题栏进度条
		Button btnTitleProgressbar = (Button)this.findViewById(R.id.title_progressbar);
		btnTitleProgressbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 显示进度条
				ProgressBarActivity.this.setProgressBarIndeterminateVisibility(true);
			}
		});
		
		// 水平进度条
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
	 * 对话框进度条
	 */
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		ProgressDialog d = new ProgressDialog(this);
		d.setIndeterminate(true);
		d.setCancelable(true);
		d.setTitle("标题");
		d.setMessage("内容加载中...");
		return d;
	}

}
