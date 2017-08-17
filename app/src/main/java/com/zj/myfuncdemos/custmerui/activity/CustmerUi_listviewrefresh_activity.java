package com.zj.myfuncdemos.custmerui.activity;

import com.nineoldandroids.animation.ObjectAnimator;
import com.zj.myfuncdemos.MyDemosApplication;
import com.zj.myfuncdemos.custmerui.view.CustmerUi_listviewrefresh_view;
import zj.myfuncdemos.R;
import com.zj.myfuncdemos.net.LogicUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class CustmerUi_listviewrefresh_activity extends Activity implements
		OnScrollListener, OnTouchListener {

	private String TAG = "CustmerUi_listviewrefresh_activity";
	private ListView listView;
	private int firstitem;
	private int aScreenContainitems;
	private int totalitems;
	private RelativeLayout toplayout;
	private RelativeLayout bottomlayout;
	public boolean isBootoom;
	private View footerview;
	
	private void measureView(View view) {
		// ----------------------my---------------------
		int widthMeasureSpec = view.getMeasuredWidth();
		int heightMeasureSpec = view.getMeasuredHeight();
		view.measure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custmer_ui_lvrefresh_activity_layout);
		listView = (ListView) findViewById(R.id.mylvrefresh);

		MyBaseAdapter mAdapter = new MyBaseAdapter();
		listView.setAdapter(mAdapter);
		listView.setOnScrollListener(this);
		listView.setOnTouchListener(this);
		
		footerview = View.inflate(this,R.layout.custmer_ui_lvrefresh_view_headerlayout, null);
		measureView(footerview);
		listView.addFooterView(footerview);
//		listView.setPadding(0, 0, 0, -footerview.getMeasuredHeight());

		toplayout = (RelativeLayout) findViewById(R.id.rl_ref_top);
		bottomlayout = (RelativeLayout) findViewById(R.id.rl_ref_bottom);

	}

	private class MyBaseAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 15;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return View.inflate(CustmerUi_listviewrefresh_activity.this,
					R.layout.custmer_ui_lvrefresh_item_layout, null);
		}
	}

	// listview��������
	/**
	 * ����ʱ
	 * */
	@Override
	public void onScroll(AbsListView paramAbsListView, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
		// ��ǰ��Ļ�׸���ʾ��item
//		  ("onScroll _" + TAG + "  firstVisibleItem == "
//				+ firstVisibleItem);
		firstitem = firstVisibleItem;

		// һ�������ɵ���Ŀ��һ����Ŀ������¶��һ��㶼�����һ��
//		  ("onScroll _" + TAG + "  visibleItemCount == "
//				+ visibleItemCount);
		aScreenContainitems = visibleItemCount;

		// ��listview���ܹ�����Ŀ��
//		  ("onScroll _" + TAG + "  totalItemCount == "
//				+ totalItemCount);

		totalitems = totalItemCount;

		if (totalitems == (firstitem + aScreenContainitems)) {
			LogicUtil.ToastShow("����ʱ...�������ײ�...");
			isBootoom = true;
		} else if (firstitem == 0) {
			LogicUtil.ToastShow("����ʱ...����������...");
			isBootoom = false;
		}else {
			isBootoom = false;
		}

	}

	/**
	 * �����¼�
	 * */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		 float starttouchy = 0;
		 float movingtouchy = 0;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			starttouchy = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
		
			movingtouchy = event.getY();
			
			if((movingtouchy - starttouchy) < 0){
				int moving = (int) (-footerview.getMeasuredHeight() +(starttouchy - movingtouchy)) ;
				if(isBootoom && moving<= footerview.getMeasuredHeight()){
					listView.setPadding(0, 0, 0, moving);
				}
				
			}
			
			
		
			
			break;
		}
		return false;
	}

	/**
	 * ������ɺ�״̬�ı���
	 * */
	@Override
	public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {

		
		// 0 : ��ֹͣʱ
		if (paramInt == 0) {
			if (totalitems == (firstitem + aScreenContainitems)) {
				LogicUtil.ToastShow("ֹͣ...�������ײ�...");
				// ObjectAnimator.ofFloat(toplayout, "y", values)
				isBootoom = true;
			}else {
				isBootoom = false;
			}

			if (firstitem == 0) {
				LogicUtil.ToastShow("ֹͣ...����������...");
			}
		} else if (paramInt == 1) {// 1 : ����ק��

		}

	}
}
