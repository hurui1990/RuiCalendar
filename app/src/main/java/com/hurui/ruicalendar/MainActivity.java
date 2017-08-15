package com.hurui.ruicalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hurui.customcalendar.RuiCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements RuiCalendar.OnItemClickListener {

	private RuiCalendar mRuiCalendar;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRuiCalendar = (RuiCalendar) findViewById(R.id.ruicalendar);
		mTextView = (TextView) findViewById(R.id.txt_selcet);

		mRuiCalendar.setOnItemClickListener(this);
	}

	//日历点击事件的回调
	@Override
	public void onItemClick(Date date) {
		Log.i("=========", date.toString());
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("您选择的时间是：")
				.append(date.getYear()+1900)
				.append("年")
				.append(date.getMonth()+1)
				.append("月")
				.append(date.getDate())
				.append("日  ").append(new SimpleDateFormat("EEEE").format(date));
		mTextView.setText(stringBuilder.toString());
	}
}
