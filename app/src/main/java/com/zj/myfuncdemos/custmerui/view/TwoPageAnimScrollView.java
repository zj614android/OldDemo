package com.zj.myfuncdemos.custmerui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class TwoPageAnimScrollView extends ScrollView {

	private View child_up;
	private View child_down;
	private RelativeLayout childroot;
	private LayoutParams layoutParams_childroot;

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// 水平滚动原点。
		// 垂直滚动原点。
		// 老以前的水平滚动的起源。
		// OLDT以往垂直滚动的起源。
		System.out.println(l + "—<—l————" + t + "——<——t——" + oldl
				+ "—<——oldl———" + oldt + "<——oldt");
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public TwoPageAnimScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int widthsize = MeasureSpec.getSize(widthMeasureSpec);
		int heightsize = MeasureSpec.getSize(heightMeasureSpec);
		
		childroot = (RelativeLayout) getChildAt(0);
	
		layoutParams_childroot = (LayoutParams) childroot.getLayoutParams();
		layoutParams_childroot.width = widthsize;
		layoutParams_childroot.height = heightsize*2;
		childroot.setLayoutParams(layoutParams_childroot);
		
		child_up = childroot.getChildAt(0);
		android.view.ViewGroup.LayoutParams layoutParams_up = child_up.getLayoutParams();
		layoutParams_up.width = widthsize;
		layoutParams_up.height = heightsize;
		child_up.setLayoutParams(layoutParams_up);
		
		child_down = childroot.getChildAt(1);
		android.view.ViewGroup.LayoutParams layoutParams_down = child_down.getLayoutParams();
		layoutParams_down.width = widthsize;
		layoutParams_down.height = heightsize;
		child_down.setLayoutParams(layoutParams_up);
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

	public TwoPageAnimScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TwoPageAnimScrollView(Context context) {
		this(context, null);
	}

	// ----------其他方法----------
	/**
	 * 获得屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	private int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	private int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return
	 */
	private int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

}
