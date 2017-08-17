package com.zj.myfuncdemos.custmerui.view;

import com.zj.myfuncdemos.MyDemosApplication;
import zj.myfuncdemos.R;

import android.content.Context;
import android.support.v4.view.ScrollingView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class FooterListView extends ListView implements OnScrollListener{
	
	private View footerview;
	
	public FooterListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		 init();
	}
	
	public FooterListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		 init();
	}
	
	public FooterListView(Context context) {
		super(context);
		 init();
	}
	
	public void init(){
		footerview = View.inflate(MyDemosApplication.mContext,R.layout.custmer_ui_lvrefresh_view_headerlayout, null);
		this.addFooterView(footerview);
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}
}
