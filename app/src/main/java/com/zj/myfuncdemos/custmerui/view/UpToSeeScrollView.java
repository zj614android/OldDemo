package com.zj.myfuncdemos.custmerui.view;

import com.zj.myfuncdemos.MyDemosApplication;
import com.zj.myfuncdemos.util.ScreenUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;

public class UpToSeeScrollView extends ScrollView implements OnTouchListener {

	private int boarder;
	private int screenHeight;
	private boolean isDownPage;

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// 水平滚动原点。
		// 垂直滚动原点。
		// 老以前的水平滚动的起源。
		// OLDT以往垂直滚动的起源。
		// System.out.println(l + "—<—l————" + t + "——<——t——" + oldl +
		// "—<——oldl———" + oldt + "<——oldt");
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public UpToSeeScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		screenHeight = ScreenUtils.getScreenHeight(MyDemosApplication.mContext);
		setOnTouchListener(this);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

	public UpToSeeScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public UpToSeeScrollView(Context context) {
		this(context, null);
	}

	@Override
	public boolean onTouch(View v, MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			System.out.println("onTouchEvent  Up");

			if (!isDownPage) {
				if (this.getScrollY() >= screenHeight / 5) {
					this.smoothScrollTo(0, screenHeight);
				} else {
					this.smoothScrollTo(0, 0);
				}
			} else {
				if (this.getScrollY() <= (screenHeight - screenHeight / 5)) {
					this.smoothScrollTo(0, 0);
				} else {
					this.smoothScrollTo(0, screenHeight);
				}
			}
			return true;
		} else if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			System.out.println("downy" + this.getScrollY());
			if (this.getScrollY() == screenHeight) {
				isDownPage = true;
			} else {
				isDownPage = false;
			}
		}

		return super.onTouchEvent(ev);// 这一行不能省 ， 因为你只有up的时候是return true //
										// ，move和down 就得调默认的super
	}

}
