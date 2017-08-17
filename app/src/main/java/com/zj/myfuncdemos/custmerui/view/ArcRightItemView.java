package com.zj.myfuncdemos.custmerui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

public class ArcRightItemView extends View {

	public ArcRightItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ArcRightItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ArcRightItemView(Context context) {
		this(context, null);
	}

	private void init() {
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}
