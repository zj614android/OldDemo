package com.zj.myfuncdemos;

import com.zj.myfuncdemos.custmerui.view.SignCalendar;
import zj.myfuncdemos.R;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class CarlendarActivity extends BaseActivity {
	
	private String date = "2016-02-29";
	private SignCalendar popupwindow_calendar;
	private TextView popupwindow_calendar_month;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// CarlendarActivity
		setContentView(R.layout.carlendaractivity);
		 getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		popupwindow_calendar = (SignCalendar) findViewById(R.id.popupwindow_calendar);
		popupwindow_calendar_month = (TextView) findViewById(R.id.popupwindow_calendar_month);
		popupwindow_calendar_month.setText(date);
		
		popupwindow_calendar.showCalendar(2016, 2);
		popupwindow_calendar.addMark(date,R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2016-03-01",R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2016-02-02",R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2016-02-03",R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2016-02-04",R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2016-02-05",R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2016-02-06",R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2016-02-07",R.drawable.calendar_bg_tag);
		popupwindow_calendar.addMark("2015-01-24",R.drawable.calendar_bg_tag);
		
		popupwindow_calendar.setOnCalendarDateChangedListener(new SignCalendar.OnCalendarDateChangedListener() {
			
			@Override
			public void onCalendarDateChanged(int year, int month) {
				popupwindow_calendar_month.setText(year + "��" + month + "��");
			}
		});

	}

}
