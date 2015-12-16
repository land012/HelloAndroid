package com.example.helloworld.widget2.fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
//import android.support.v4.app.DialogFragment;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;

import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DateTimePickerDialogFragment extends DialogFragment {
	
	private static TextView textView;
	
	public static DateTimePickerDialogFragment newInstance(int type, TextView tv) {
		DateTimePickerDialogFragment d = new DateTimePickerDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		d.setArguments(bundle);
		textView = tv;
		return d;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		int type = this.getArguments().getInt("type");
		
		if(type == 0) {
			return new DatePickerDialog(this.getActivity(),new OnDateSetListener(){
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					Calendar c = Calendar.getInstance();
					c.set(year, monthOfYear, dayOfMonth);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
					textView.setText(sdf.format(c.getTime()));
				}
			}, 2014, 2, 7);
		} else if(type == 1) {
			return new TimePickerDialog(this.getActivity(), new OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					textView.setText(hourOfDay + ":" + minute);
				}
			}, 12, 00, false);
		}
		return null;
		
		
	}

}
