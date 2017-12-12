package com.hurui.customcalendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * @author hurui
 */
public class RuiCalendarAdapter extends BaseAdapter {

	private List<Date> mDates;
	private LayoutInflater mInflater;
	private ViewHolder viewHolder = null;

	public RuiCalendarAdapter(Context context, List<Date> dates){
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
		final Date itemDate = mDates.get(position);
		Date nowDate = new Date();
		if(contentView == null){
			viewHolder = new ViewHolder();
			contentView = mInflater.inflate(R.layout.calendar_day_view, parent,false);
			viewHolder.txtDate =  contentView.findViewById(R.id.txt_calendar_day);
			viewHolder.txtLunarDate =  contentView.findViewById(R.id.txt_lunar_date);
			viewHolder.calendar_day =  contentView.findViewById(R.id.calendar_day_view);
			contentView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) contentView.getTag();
		}
		if(itemDate == null){
			contentView.setVisibility(View.GONE);
		}else {
			contentView.setVisibility(View.VISIBLE);
			RuiLunar lunar = new RuiLunar(itemDate);
			viewHolder.txtDate.setText(String.valueOf(itemDate.getDate()));
			viewHolder.txtLunarDate.setText(RuiLunar.getChinaDayString(lunar.day)+"");
			if(nowDate.getDate() == itemDate.getDate()
					&& nowDate.getYear() == itemDate.getYear()
					&& nowDate.getMonth() == itemDate.getMonth()){
				viewHolder.txtDate.setTextColor(Color.parseColor("#ff0000"));
				viewHolder.txtLunarDate.setTextColor(Color.parseColor("#ff0000"));
				viewHolder.calendar_day.setBackgroundColor(Color.parseColor("#FFE6E4E5"));
			}
		}
		return contentView;
	}

	class ViewHolder{
		TextView txtDate, txtLunarDate;
		LinearLayout calendar_day;
	}
}
