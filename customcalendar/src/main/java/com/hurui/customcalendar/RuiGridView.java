package com.hurui.customcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by hurui on 2017/8/14.
 */

public class RuiGridView extends GridView {

	//手指按下的点为(down_x, down_y)手指离开屏幕的点为(up_x, up_y)
	float down_x = 0;
	float up_x = 0;
	float down_y = 0;
	float up_y = 0;
	float offsetY = 0;
	private int maxRowNums = 0;
	private int minHeight;
	private int maxHeight;
	private int finalOffset;
	private boolean isCollopse = false;
	private ViewGroup.LayoutParams layoutParams;

	public RuiGridView(Context context) {
		super(context);
	}

	public RuiGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		post(new Runnable() {
			@Override
			public void run() {
				if(getChildCount() % 7 == 0){
					maxRowNums = getChildCount() / 7;
				}else {
					maxRowNums = getChildCount() / 7 + 1;
				}
				minHeight = (int) getResources().getDimension(R.dimen.day_item_height);
				maxHeight = minHeight * maxRowNums;
			}
		});
	}

	public RuiGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(ev.getAction() == MotionEvent.ACTION_DOWN) {
			//当手指按下的时候
			down_x = ev.getX();
			down_y = ev.getY();
			finalOffset = getScrollY();
		}
		if(ev.getAction() == MotionEvent.ACTION_UP) {
			//当手指离开的时候
			up_x = ev.getX();
			up_y = ev.getY();
			if(offsetY <= 0){
				scrollTo(0, (maxRowNums-1)*minHeight);
			}else{
				scrollTo(0, 0);
			}
		}
		if (ev.getAction() == MotionEvent.ACTION_MOVE){
			//当手指移动的时候
			up_x = ev.getX();
			up_y = ev.getY();
			offsetY = up_y - down_y;
			setGridViewHeight();
		}
		return true;
	}

	private void setGridViewHeight(){
		Log.i("============",offsetY+"");
		if (offsetY < 0 && -offsetY < (maxRowNums-1)*minHeight  && finalOffset == 0) {
			scrollTo(0, (int) -offsetY);
		} else if(offsetY > 0 && offsetY < (maxRowNums-1)*minHeight && finalOffset == (maxRowNums-1)*minHeight){
			scrollTo(0, (int) (finalOffset - offsetY));
		}
	}
}
