//package com.zj.myfuncdemos.custmerui.view;
//
//import com.zj.myfuncdemos.R;
//
//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Point;
//import android.graphics.Rect;
//import android.view.View;
//
//public class CustmerUi_handlock_view extends View {
//
//	private int screenHeight;
//	private int screenWidth;
//	private Paint paint;
//	Point[][] points= new Point[3][3];
//	
//	public CustmerUi_handlock_view(Context context) {
//		super(context);
//
//		paint = new Paint();
//	}
//
//	@Override
//	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
//
//		screenHeight = getHeight();
//		screenWidth = getWidth();
//
//		//º”‘ÿÕº∆¨
//		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.handlock_blue), 50, 50, paint);
//
//		if (screenHeight > screenWidth) {//  ˙∆¡
//			
//			
//		} else {// ∫·∆¡
//			
//			
//		}
//
//	}
//
//	@Override
//	protected void onLayout(boolean changed, int left, int top, int right,
//			int bottom) {
//		super.onLayout(changed, left, top, right, bottom);
//	}
//
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	}
//
//}
