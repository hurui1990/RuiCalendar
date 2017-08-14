package com.hurui.customcalendar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by hurui on 2017/8/14.
 */

public class RuiCalendarAdapter extends BaseAdapter {

	private Context mContext;
	private List<Date> mDates;
	private LayoutInflater mInflater;

	public RuiCalendarAdapter(Context context, List<Date> dates){
		mContext = context;
		mDates = dates;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mDates.size();
	}

	@Override
	public Object getItem(int i) {
		return mDates.get(i);
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		Date itemDate = mDates.get(position);
		Log.i("===========",""+itemDate);
		ViewHolder viewHolder = null;
		if(contentView == null){
			viewHolder = new ViewHolder();
			contentView = mInflater.inflate(R.layout.calendar_day_view, parent,false);
			viewHolder.txtDate = (TextView) contentView.findViewById(R.id.txt_calendar_day);
			contentView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) contentView.getTag();
		}
		if(itemDate == null){
			contentView.setVisibility(View.GONE);
		}else {
			contentView.setVisibility(View.VISIBLE);
			viewHolder.txtDate.setText(String.valueOf(itemDate.getDate()));
		}
		return contentView;
	}

	class ViewHolder{
		TextView txtDate;
	}
}
