package com.example.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.DatePicker;

public class UpdateActivity extends Activity implements DatePicker.OnDateChangedListener {
	OnClickListener listener1 = null;
	OnClickListener listener2 = null;
	OnClickListener listener3 = null;
	Button button1;
	Button button2;
	EditText text;
	CheckBox ctime;
	DatePicker dp;
	TimePicker tp;
	int flag = 0;
	int hourOfDay;
	int minute;
	int year;
	int monthOfYear;
	int dayOfMonth;
	int id;
	String str, things, time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		initListener();
		button1 = (Button) findViewById(R.id.up_button);
		button1.setOnClickListener(listener1);
		button2 = (Button) findViewById(R.id.upcancel_button);
		button2.setOnClickListener(listener2);
		text = (EditText) findViewById(R.id.up_things);
		ctime = (CheckBox) findViewById(R.id.upcheckTime);
		dp = (DatePicker) findViewById(R.id.updatePicker1);
		tp = (TimePicker) findViewById(R.id.uptimePicker1);
		Intent intent = this.getIntent();
		id = intent.getIntExtra("id", 0);
		things = intent.getStringExtra("things");
		time = intent.getStringExtra("time");
		text.setText(things);
		Calendar calendar = Calendar.getInstance();
		hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		year = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		dp.init(year, monthOfYear, dayOfMonth, this);
		ctime.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				flag = arg1 ? 1 : 0;
				if (flag != 0) {
					dp.setVisibility(0);
					tp.setVisibility(0);
				} else {
					dp.setVisibility(8);
					tp.setVisibility(8);
				}
			}

		});
	}

	public void initListener() {
		listener1 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(text.getText())) {
					Toast.makeText(UpdateActivity.this, "不能为空", Toast.LENGTH_LONG).show();
				} else {
					String key = text.getText().toString();
					Intent it = getIntent();
					Bundle bd = new Bundle();
					bd.putInt("id", id);
					bd.putString("things", key);

					if (flag == 0) {
						bd.putString("time", time);
					} else {
						try {
							str = getTime();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bd.putString("time", str);
					}
					it.putExtras(bd);
					setResult(0x001, it);
					finish();
				}
			}
		};
		listener2 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}

		};
	}



	public String getTime() throws ParseException {

		String stime;
		tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int nhourOfDay, int nminute) {
				hourOfDay = nhourOfDay;
				minute = nminute;
			}
		});
		stime = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " " + hourOfDay + ":" + minute;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		stime = sdf.format(sdf1.parse(stime));
		return stime;
	}

	@Override
	public void onDateChanged(DatePicker view, int nyear, int nmonthOfYear, int ndayOfMonth) {
		// TODO Auto-generated method stub
		this.year = nyear;
		this.monthOfYear = nmonthOfYear;
		this.dayOfMonth = ndayOfMonth;
	}
}
