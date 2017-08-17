package com.zj.myfuncdemos.custmerui.view;

import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.zj.myfuncdemos.net.LogicUtil;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import zj.myfuncdemos.R;

public class DynamicAnimProgressView extends View /*
												 * implements
												 * AnimatorUpdateListener
												 */{

	public static final double MAX_ANNUAL_EAENINGS_VALUE = 100;
	private final int mPaintWidth = 8;
	private final int mHalfPaintWidth = mPaintWidth / 2;
	private int mViewSize = 0;
	private float mViewCenterX = 0f;
	private float mViewCenterY = 0f;
	private float mViewRadius = 0f;
	private Paint mBackPaint = null;
	private Paint mFrontPaint = null;
	private RectF mOvalRectF = null;
	private Paint mTextPaint = null;
	private String mText = "";
	private float mStartAngle = 270f;
	private float mSweepAngle = 0f;
	private float mCurrSweepAngle = 0f;
	public static float progressvalue;
	/**
	 * 开启动画
	 * */
	public void startAnim() {
		ValueAnimator ofObjectValueAnimator = com.nineoldandroids.animation.ValueAnimator
				.ofObject(new SweepEvaluator(), mStartAngle, mSweepAngle);
		ofObjectValueAnimator
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

					@Override
					public void onAnimationUpdate(ValueAnimator animator) {
						mCurrSweepAngle = (Float) animator.getAnimatedValue();
						invalidate();
					}
				});
		ofObjectValueAnimator.setDuration(2000);
		ofObjectValueAnimator.start();
	}

	public DynamicAnimProgressView(Context context) {
		this(context, null);
	}

	public DynamicAnimProgressView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DynamicAnimProgressView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		mBackPaint = new Paint();
		mBackPaint.setStyle(Paint.Style.STROKE);
		mBackPaint.setAntiAlias(true);
		mBackPaint.setStrokeWidth(mPaintWidth);
		mBackPaint.setColor(context.getResources().getColor(
				R.color.color_more_light_grey_2));

		mFrontPaint = new Paint();
		mFrontPaint.setStyle(Paint.Style.STROKE);
		mFrontPaint.setAntiAlias(true);
		mFrontPaint.setStrokeWidth(mPaintWidth);
		// mFrontPaint.setColor(context.getResources().getColor(R.color.color_green));
		mFrontPaint.setColor(context.getResources().getColor(
				R.color.color_orange));
		mOvalRectF = new RectF();

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextSize(LogicUtil.TypeValue_Sp_To_Px(10, context));
		mTextPaint.setColor(context.getResources().getColor(
				R.color.color_more_light_grey_2));

		mText = "0%";
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mViewSize = MeasureSpec.getSize(widthMeasureSpec);
		mViewCenterX = mViewSize / 2;
		mViewCenterY = mViewCenterX;

		mViewRadius = mViewCenterX - mHalfPaintWidth - 5;
		
		mOvalRectF.left = mViewCenterX - mViewRadius;
		mOvalRectF.top = mViewCenterY - mViewRadius;
		mOvalRectF.right = mViewCenterX + mViewRadius;
		mOvalRectF.bottom = mViewCenterY + mViewRadius;
		
		System.out.println("囧司徒 mOvalRectF.left == " + mOvalRectF.left);
		System.out.println("囧司徒 mOvalRectF.top == " + mOvalRectF.top);
		System.out.println("囧司徒 mOvalRectF.right == " + mOvalRectF.right);
		System.out.println("囧司徒 mOvalRectF.bottom  == " + mOvalRectF.bottom);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public void setProgressValue(double value) {
		mSweepAngle = (float) (value * 360 / MAX_ANNUAL_EAENINGS_VALUE);
		DynamicAnimProgressView.progressvalue = (float) value;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(mViewCenterX, mViewCenterY, mViewRadius, mBackPaint);
		canvas.drawArc(mOvalRectF, mStartAngle, mCurrSweepAngle, false,mFrontPaint);
		System.out.println("囧司徒 mViewRadius  == " + mViewRadius);
		FontMetrics topMetrics = mTextPaint.getFontMetrics();
		float topY = mViewCenterY + topMetrics.bottom;
		float topX = mViewCenterX - mTextPaint.measureText(mText) / 2;
		canvas.drawText(mText, topX, topY, mTextPaint);
	}

	/**
	 * 动画取值器
	 * */

	public class SweepEvaluator implements TypeEvaluator {
		@Override
		public Object evaluate(float fraction, Object startValue,
				Object endValue) {

			float currvalue = DynamicAnimProgressView.progressvalue * fraction;

			mText = currvalue + "";
			String[] split = mText.split("\\.");
			mText = split[0] + "%";

			return mSweepAngle * fraction;
		}

	}

}
