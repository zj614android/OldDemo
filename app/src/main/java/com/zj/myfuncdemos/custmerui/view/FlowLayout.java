package com.zj.myfuncdemos.custmerui.view;

import com.zj.myfuncdemos.MyDemosApplication;
import com.zj.myfuncdemos.util.ScreenUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

	private int child_measuredWidth = 0;
	private View curr_child = null;
	private int linewidth = 0;
	private int lineheight = 0;
	private int child_measuredHeight = 0;

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context) {
		this(context, null);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		/*
		 * 若不changed，onLayout会至少被执行2次
		 */
		if (changed) {

			/*
			 * 循环遍历孩子
			 */
			for (int i = 0; i < getChildCount(); i++) {

				/*
				 * 获取当前孩子
				 */
				curr_child = getChildAt(i);

				/*
				 * 获取当前孩子的测试宽高
				 */
				child_measuredWidth = curr_child.getMeasuredWidth();
				child_measuredHeight = curr_child.getMeasuredHeight();

				/*
				 * 获取margin参数一并计入一个按钮的总宽度
				 */
				MarginLayoutParams layoutParams = (MarginLayoutParams) curr_child
						.getLayoutParams();
				int leftMargin = layoutParams.leftMargin;
				int rightMargin = layoutParams.rightMargin;
				child_measuredWidth = child_measuredWidth + leftMargin + rightMargin;

				/* 放置之前判断下是否超长 超长就重置 */
				if ((linewidth + child_measuredWidth) > ScreenUtils
						.getScreenWidth(MyDemosApplication.mContext)) {
					lineheight += child_measuredHeight;
					linewidth = 0;
				}

				/*
				 * 放置
				 */
				curr_child.layout(linewidth, lineheight, linewidth
						+ child_measuredWidth, lineheight
						+ child_measuredHeight);

				/* 放置之后判断下是否在范围内，若在范围内就叠加长度 */
				if (!((linewidth + child_measuredWidth) > ScreenUtils
						.getScreenWidth(MyDemosApplication.mContext))) {
					linewidth += child_measuredWidth;
				}

			}
		}
	}

	private void init() {

	}

	/**
	 * 测量自己子孩子的宽和高
	 * */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int size_width = MeasureSpec.getSize(widthMeasureSpec);
		int mode_width = MeasureSpec.getMode(widthMeasureSpec);

		int size_height = MeasureSpec.getSize(heightMeasureSpec);
		int mod_height = MeasureSpec.getMode(heightMeasureSpec);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		setMeasuredDimension(size_width, size_height);// 这句必须加 否则报错
	}

	/**
	 * 因为我关心控件内子空间的margin参数，所以这样复写，注意这个方法的名字是generate并非是get
	 * */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

}
