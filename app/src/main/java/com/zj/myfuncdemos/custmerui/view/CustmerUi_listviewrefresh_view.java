package com.zj.myfuncdemos.custmerui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import zj.myfuncdemos.R;

public class CustmerUi_listviewrefresh_view extends ListView implements
		OnScrollListener {
	private View header;
	private int toppadding = 0;
	private boolean scrollstop = false;

	public CustmerUi_listviewrefresh_view(Context context) {
		this(context, null, 0);
	}

	public CustmerUi_listviewrefresh_view(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustmerUi_listviewrefresh_view(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater Inflater = LayoutInflater.from(context);
		header = Inflater.inflate(R.layout.custmer_ui_lvrefresh_view_headerlayout, null);
		measureView(header);
		toppadding = header.getMeasuredHeight();
		topPadding(-toppadding);
		this.addHeaderView(header, null, false);
		this.setOnScrollListener(this);
	}

	/**
	 * ����header
	 * */
	private void topPadding(int toppadding) {
		header.setPadding(header.getPaddingLeft(), toppadding,header.getPaddingRight(), header.getPaddingBottom());
		header.invalidate();
	}

	/**
	 * ֪ͨ�����֣�ռ�õĿ��ߣ�
	 * 
	 * @param view
	 */
	private void measureView(View view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		int height;
		int tempHeight = p.height;
		if (tempHeight > 0) {
			height = MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}

	/**
	 * ���������������Ƿ񻬶�������
	 * 
	 * @param scrollState
	 *            ֹͣ����ʱ����0 ����ʱ����1
	 * */
	// OnScrollListener.SCROLL_STATE_IDLE://����״̬
	// OnScrollListener.SCROLL_STATE_FLING://����״̬
	// OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://���������
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		String str = "onScrollStateChanged___scrollState___" + scrollState;
		System.out.println(str);
		if (scrollState == 0) {
			scrollstop = true;
		} else {
			scrollstop = false;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		String str = "onScroll___firstVisibleItem___" + firstVisibleItem
				+ "___visibleItemCount___" + visibleItemCount
				+ "___totalItemCount___" + totalItemCount;
		System.out.println(str);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return super.onTouchEvent(ev);
	}

}
