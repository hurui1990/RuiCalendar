package com.hurui.customcalendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author hurui
 * @date 2017/8/14.
 */

public class RuiCalendar extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    private LayoutInflater mLayoutInflater;
    private ImageView mBtnPre;
    private ImageView mBtnNext;
    private TextView mTxtDate;
    private GridView mGridView;
    private Calendar mCalendar = Calendar.getInstance();
    private RuiCalendarAdapter mAdapter;
    private List<Date> dates;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        /**
         * @param date
         */
        void onItemClick(Date date);
    }

    /**
     * @param context
     */
    public RuiCalendar(Context context) {
        super(context);
    }

    public RuiCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCalendar(context);
    }

    public RuiCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCalendar(context);
    }

    /**
     * 初始化日历控件
     * @param context
     */
    private void initCalendar(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        mLayoutInflater.inflate(R.layout.calendar_view,this);

        mBtnPre =  findViewById(R.id.btn_pre);
        mBtnNext =  findViewById(R.id.btn_next);
        mTxtDate =  findViewById(R.id.txt_date);
        mGridView =  findViewById(R.id.grid_calendar);

        mBtnPre.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mTxtDate.setOnClickListener(this);

        renderCalendar();
    }

    /**
     * 获取当前月的数据
     */
    private void renderCalendar(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        mTxtDate.setText(sdf.format(mCalendar.getTime()));

        dates = new ArrayList<>();
        Calendar calendar = (Calendar) mCalendar.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(firstDayWeek == 0){
            firstDayWeek = 7;
        }
        int dayCountInCurrentMonth = getCurrentMonthDay(calendar);
        calendar.add(Calendar.DAY_OF_MONTH, -(dayCountInCurrentMonth-1));
        int allDaysInView = firstDayWeek + dayCountInCurrentMonth - 1;
        for(int i=0; i < allDaysInView; i++){
            if(i<firstDayWeek-1){
                dates.add(null);
                continue;
            }
            dates.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        mAdapter = new RuiCalendarAdapter(getContext(), dates);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
    }

    /**
     * 获取当前月的总天数
     */
    public static int getCurrentMonthDay(Calendar calendar) {
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int maxDate = calendar.get(Calendar.DATE);
        return maxDate;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_pre) {
            mCalendar.add(Calendar.MONTH, -1);
            renderCalendar();
        } else if (i == R.id.btn_next) {
            mCalendar.add(Calendar.MONTH, 1);
            renderCalendar();
        } else if (i == R.id.txt_date) {
            Log.i("选择年", (mCalendar.getTime().getYear()+1900)+"");
            Log.i("选择月", (mCalendar.getTime().getMonth()+1)+"");
        }
    }

    /**
     * 设置日历的点击事件
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(dates.get(i));
        }
        int childCount = mGridView.getChildCount();
        for(int n=0; n< childCount; n++){
            mGridView.getChildAt(n).setBackgroundColor(Color.parseColor("#00E6E4E5"));
        }
        mGridView.getChildAt(i).setBackgroundColor(Color.parseColor("#FFE6E4E5"));
    }
}
