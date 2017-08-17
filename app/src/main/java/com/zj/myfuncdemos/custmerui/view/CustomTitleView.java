package com.zj.myfuncdemos.custmerui.view;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.zj.myfuncdemos.MyDemosApplication;
import zj.myfuncdemos.R;
import com.zj.myfuncdemos.net.LogicUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomTitleView extends View {

	private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
			'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static final int PicsCheckCodeLength = 4;

	/**
	 * �ı�
	 */
	private String mTitleText;
	/**
	 * �ı�����ɫ
	 */
	private int mTitleTextColor;
	/**
	 * �ı��Ĵ�С
	 */
	private int mTitleTextSize;

	/**
	 * ����ʱ�����ı����Ƶķ�Χ
	 */
	private Rect mBound;
	private Paint mPaint;

	private Paint xopoint;

	public String getCode() {
		return mTitleText;
	}

	public CustomTitleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomTitleView(Context context) {
		this(context, null);
	}

	/**
	 * ������Զ������ʽ����
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomTitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		/**
		 * ���������������Զ�����ʽ����
		 */
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CustomTitleView, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTitleView_titleText:
				mTitleText = a.getString(attr);
				break;
			case R.styleable.CustomTitleView_titleTextColor:
				// Ĭ����ɫ����Ϊ��ɫ
				mTitleTextColor = a.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomTitleView_titleTextSize:
				// Ĭ������Ϊ16sp��TypeValueҲ���԰�spת��Ϊpx
				mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
								getResources().getDisplayMetrics()));
				break;

			}

		}
		a.recycle();

		/**
		 * ��û����ı��Ŀ�͸�
		 */
		mPaint = new Paint();
		mPaint.setTextSize(mTitleTextSize);
		// mPaint.setColor(mTitleTextColor);
		mBound = new Rect();
		mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

		xopoint = new Paint();
		xopoint.setColor(Color.RED);

		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mTitleText = randomText();
				postInvalidate();
			}

		});

	}

	private String randomText() {

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < PicsCheckCodeLength; i++) {
			sb.append(CHARS[random.nextInt(CHARS.length)]);
		}

		return sb.toString();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = 0;
		int height = 0;

		/**
		 * ���ÿ��
		 */
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		switch (specMode) {
		case MeasureSpec.EXACTLY:// ��ȷָ����
			width = getPaddingLeft() + getPaddingRight() + specSize;
			break;
		case MeasureSpec.AT_MOST:// һ��ΪWARP_CONTENT
			width = getPaddingLeft() + getPaddingRight() + mBound.width();
			break;
		}

		/**
		 * ���ø߶�
		 */
		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		switch (specMode) {
		case MeasureSpec.EXACTLY:// ��ȷָ����
			height = getPaddingTop() + getPaddingBottom() + specSize;
			break;
		case MeasureSpec.AT_MOST:// һ��ΪWARP_CONTENT
			height = getPaddingTop() + getPaddingBottom() + mBound.height();
			break;
		}

		setMeasuredDimension(width, height);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		mPaint.setColor(Color.WHITE);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

		mPaint.setColor(mTitleTextColor);
		canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2,
				getHeight() / 2 + mBound.height() / 2, mPaint);

		for (int i = 0; i < 20; i++) {
			int randomX = (int) (1 + Math.random() * (getWidth() - 1 + 1));
			int randomY = (int) (1 + Math.random() * (getHeight() - 1 + 1));
			canvas.drawCircle(randomX, randomY, LogicUtil.TypeValue_Dp_To_Px(1,
					MyDemosApplication.mContext), xopoint);
		}

	}
}
