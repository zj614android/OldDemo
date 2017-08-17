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
		 * ����changed��onLayout�����ٱ�ִ��2��
		 */
		if (changed) {

			/*
			 * ѭ����������
			 */
			for (int i = 0; i < getChildCount(); i++) {

				/*
				 * ��ȡ��ǰ����
				 */
				curr_child = getChildAt(i);

				/*
				 * ��ȡ��ǰ���ӵĲ��Կ��
				 */
				child_measuredWidth = curr_child.getMeasuredWidth();
				child_measuredHeight = curr_child.getMeasuredHeight();

				/*
				 * ��ȡmargin����һ������һ����ť���ܿ��
				 */
				MarginLayoutParams layoutParams = (MarginLayoutParams) curr_child
						.getLayoutParams();
				int leftMargin = layoutParams.leftMargin;
				int rightMargin = layoutParams.rightMargin;
				child_measuredWidth = child_measuredWidth + leftMargin + rightMargin;

				/* ����֮ǰ�ж����Ƿ񳬳� ���������� */
				if ((linewidth + child_measuredWidth) > ScreenUtils
						.getScreenWidth(MyDemosApplication.mContext)) {
					lineheight += child_measuredHeight;
					linewidth = 0;
				}

				/*
				 * ����
				 */
				curr_child.layout(linewidth, lineheight, linewidth
						+ child_measuredWidth, lineheight
						+ child_measuredHeight);

				/* ����֮���ж����Ƿ��ڷ�Χ�ڣ����ڷ�Χ�ھ͵��ӳ��� */
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
	 * �����Լ��Ӻ��ӵĿ�͸�
	 * */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int size_width = MeasureSpec.getSize(widthMeasureSpec);
		int mode_width = MeasureSpec.getMode(widthMeasureSpec);

		int size_height = MeasureSpec.getSize(heightMeasureSpec);
		int mod_height = MeasureSpec.getMode(heightMeasureSpec);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		setMeasuredDimension(size_width, size_height);// ������� ���򱨴�
	}

	/**
	 * ��Ϊ�ҹ��Ŀؼ����ӿռ��margin����������������д��ע�����������������generate������get
	 * */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

}
