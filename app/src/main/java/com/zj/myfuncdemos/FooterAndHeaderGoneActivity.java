package com.zj.myfuncdemos;

import java.util.ArrayList;
import java.util.List;
import com.zj.myfuncdemos.net.LogicUtil;
import com.zj.myfuncdemos.util.ScreenUtils;
import zj.myfuncdemos.R;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FooterAndHeaderGoneActivity extends BaseActivity implements
		OnTouchListener ,OnScrollListener{

	private ListView listviews = null;
	private List<String> datalist = null;
	private View headerview = null;
	private View footerview = null;
	private View itelview_for_one = null;
	private View itelview_for_last = null;
	private int moveY = 0;
	private int totalcount;
	private int lastcount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.footerandheader_layout);

		headerview = View.inflate(this,
				R.layout.custmer_ui_lvrefresh_view_headerlayout, null);
		footerview = View.inflate(this,
				R.layout.custmer_ui_lvrefresh_view_headerlayout, null);
		
		
		listviews = (ListView) findViewById(R.id.listviews);
		listviews.setOnScrollListener(this);
		listviews.addHeaderView(headerview);
		
		footerview.findViewById(R.id.root_foot).setVisibility(View.GONE);
//		footerview.setVisibility(View.GONE);
		listviews.addFooterView(footerview);
	

		listviews.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, makeData()));

		measureView(headerview);
		listviews.setPadding(0, -headerview.getMeasuredHeight(), 0, 0);
		listviews.setOnTouchListener(this);
	}

	/**
	 * getmeasureviewΪ0 ��ԭ�������Լ���û��measure����ô���Ǿ��ֶ�����measureһ��
	 * */
	private void measureView(View view) {
		// ----------------------my---------------------
		int widthMeasureSpec = view.getMeasuredWidth();
		int heightMeasureSpec = view.getMeasuredHeight();
		view.measure(widthMeasureSpec, heightMeasureSpec);
	}

	private List<String> makeData() {
		datalist = null;
		datalist = new ArrayList<String>();
		for (int i = 0; i < 15; i++) {
			datalist.add("item " + i);
		}
		return datalist;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {

		if (itelview_for_one == null)
			itelview_for_one = listviews.getChildAt(1);
		
		if(itelview_for_last == null)
			itelview_for_last = listviews.getChildAt(datalist.size());
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			moveY = -headerview.getMeasuredHeight();
			break;
		case MotionEvent.ACTION_UP:
			moveY = -headerview.getMeasuredHeight();
			listviews.setPadding(0, -headerview.getMeasuredHeight(), 0, 0);
			break;
		case MotionEvent.ACTION_MOVE:
			
			// �ƶ���ʱ���ж�item1�Ƿ�����Ļ��,����ڵĻ��������ƶ�����listview��
			// ÿ���ƶ�10������㣬ֱ��headerview�ľ���˥�����Ϊֹ
			
			if (itelview_for_one.getTop() >= 0) {
				LogicUtil.ToastShow("�ѵ�����   " + itelview_for_one.getTop());
				if (moveY <= 0) {
					moveY += 10;
					listviews.setPadding(0, moveY, 0, 0);
				}
			}
			
			
			break;
		}
		
		return false;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(scrollState == 0){//IDLEʱ
			if(totalcount == lastcount){
				
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		totalcount = totalItemCount;
		lastcount = firstVisibleItem+visibleItemCount;
	}

}
