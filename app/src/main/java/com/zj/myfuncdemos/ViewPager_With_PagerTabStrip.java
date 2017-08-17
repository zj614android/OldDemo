package com.zj.myfuncdemos;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import zj.myfuncdemos.R;

public class ViewPager_With_PagerTabStrip extends BaseActivity {

	private ViewPager vp;
	private int[] imgidsarrs;
	private ArrayList<View> viewlist;
	private PagerAdapter pagerAdapter;
	private PagerTabStrip pagerTabStrip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager_with_pagertabstrip);
		vp = (ViewPager) findViewById(R.id.vp_viewpager_2);

		pagerTabStrip = (PagerTabStrip) findViewById(R.id.vp_viewpager_tabstrip);

		View view1 = View.inflate(MyDemosApplication.mContext,
				R.layout.viewpager_tab_1, null);
		View view2 = View.inflate(MyDemosApplication.mContext,
				R.layout.viewpager_tab_2, null);
		View view3 = View.inflate(MyDemosApplication.mContext,
				R.layout.viewpager_tab_3, null);
		View view4 = View.inflate(MyDemosApplication.mContext,
				R.layout.viewpager_tab_4, null);

		viewlist = new ArrayList<View>();
		viewlist.add(view1);
		viewlist.add(view2);
		viewlist.add(view3);
		viewlist.add(view4);

		ArrayList<String> titlelist = new ArrayList<String>();
		titlelist.add("��һҳ");
		titlelist.add("�ڶ�ҳ");
		titlelist.add("����ҳ");
		titlelist.add("����ҳ");

		// tab����
		pagerTabStrip.setTextColor(Color.RED);// ������ɫ
		pagerTabStrip.setTabIndicatorColor(Color.YELLOW);// ����ɫ
		pagerTabStrip.setDrawFullUnderline(false);// �����������

		basePagerAdapter adapter = new basePagerAdapter(viewlist, titlelist);
		vp.setAdapter(adapter);
	}

	class basePagerAdapter extends PagerAdapter {

		ArrayList<View> arrayList = null;
		ArrayList<String> titlelist = null;

		public basePagerAdapter(ArrayList<View> arrayList,
				ArrayList<String> titlelist) {
			super();
			this.arrayList = arrayList;
			this.titlelist = titlelist;
		}

		@Override
		public int getCount() {
			return arrayList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewlist.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = viewlist.get(position);
			container.addView(view);
			return viewlist.get(position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titlelist.get(position);
		}
	}
}
