package cf.hector.helloworld.widget2.activity;


import cf.hector.helloworld.R;
import cf.hector.helloworld.widget2.fragment.DateTimePickerDialogFragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
/**
 * 使用 DialogFragment 弹出日期时间选择框
 * 不同版本 Android API 兼容问题????????????
 * @author asdf
 *
 */
public class DateTimePickerActivity extends Activity {
	
	private TextView tvDatePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_datetimepicker);
		
		tvDatePicker = (TextView)this.findViewById(R.id.datepickertv);
		
		// 日期选择对话框
		Button btnDatePicker = (Button)this.findViewById(R.id.datepicker);
		btnDatePicker.setOnClickListener(new View.OnClickListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View v) {
//				DateTimePickerActivity.this.showDialog(0);
				DateTimePickerDialogFragment d = DateTimePickerDialogFragment.newInstance(0, tvDatePicker);
				d.show(DateTimePickerActivity.this.getFragmentManager(), "tag");
			}
		});
		
		// 时间选择对话框
		Button btnTimePicker = (Button)this.findViewById(R.id.timepicker);
		btnTimePicker.setOnClickListener(new View.OnClickListener() {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View v) {
				DateTimePickerDialogFragment d = DateTimePickerDialogFragment.newInstance(1, tvDatePicker);
				d.show(DateTimePickerActivity.this.getFragmentManager(), "tag");
			}
		});
		
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		if (id == 0) {
			return new DatePickerDialog(this, new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					tvDatePicker.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
				}
			}, 2014, 3, 7);
		} else if(id == 1) {
			return new TimePickerDialog(this, new OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
					
				}
			}, 12, 0, false);
		}
		return null;
	}

}
