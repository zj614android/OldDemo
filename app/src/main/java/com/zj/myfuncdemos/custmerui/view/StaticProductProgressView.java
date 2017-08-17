package com.zj.myfuncdemos.custmerui.view;

import com.nineoldandroids.animation.ValueAnimator;
import zj.myfuncdemos.R;
import com.zj.myfuncdemos.net.LogicUtil;
import com.zj.myfuncdemos.net.PostByVolley;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

public class StaticProductProgressView extends View implements AnimatorUpdateListener{

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

	/**
	 * 开启动画
	 * */
	public void enableAnim(){
		
	}
	
	
	public StaticProductProgressView(Context context) {
		this(context, null);
	}

	public StaticProductProgressView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public StaticProductProgressView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		
//		ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f);
//		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//			
//			@Override
//			public void onAnimationUpdate(ValueAnimator arg0) {
//				
//			}
//		});
		
		
		
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
		mViewRadius = mViewCenterX - mHalfPaintWidth;

		mOvalRectF.left = mHalfPaintWidth;
		mOvalRectF.top = mHalfPaintWidth;
		mOvalRectF.right = mViewSize - mHalfPaintWidth;
		mOvalRectF.bottom = mViewSize - mHalfPaintWidth;

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public void setProgressValue(double value) {
		mSweepAngle = (float) (value * 360 / MAX_ANNUAL_EAENINGS_VALUE);
		mText = value + "";
		String[] split = mText.split("\\.");
		mText = split[0] + "%";
//		postInvalidate();
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(mViewCenterX, mViewCenterY, mViewRadius, mBackPaint);
		
		canvas.drawArc(mOvalRectF, mStartAngle, mSweepAngle, false, mFrontPaint);

		FontMetrics topMetrics = mTextPaint.getFontMetrics();
		float topY = mViewCenterY + topMetrics.bottom;
		float topX = mViewCenterX - mTextPaint.measureText(mText) / 2;
		canvas.drawText(mText, topX, topY, mTextPaint);
	}

	@Override
	public void onAnimationUpdate(android.animation.ValueAnimator animation) {
		invalidate();
	}
	
}
