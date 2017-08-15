package com.hurui.ruicalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hurui.customcalendar.RuiCalendar;

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

	@Override
	public void onItemClick(Date date) {
		mTextView.setText("您选择的时间是："+date.toString());
	}

}
