package com.zj.myfuncdemos.custmerui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import zj.myfuncdemos.R;


public class CustmerUi_first_view extends View {
	private Paint paint;
	private String textword;
	private float textSize;
	private int color;
	
	private Bitmap bitmap = null;

	public CustmerUi_first_view(Context context, AttributeSet attribute) {
		this(context, attribute, 0);
	}

	public CustmerUi_first_view(Context context) {
		this(context, null);
	}

	public CustmerUi_first_view(Context context, AttributeSet attribute,
			int defStyle) {
		super(context, attribute, defStyle);

		paint = new Paint();

		// TypedArray��һ�����������context.obtainStyledAttributes��õ����Ե�����
		TypedArray array = context.obtainStyledAttributes(attribute,
				R.styleable.CustmerUi_first_view);

		// System.out.println("array.getIndexCount() == "+array.getIndexCount());
		// array.getDimensionPixelSize(index, defValue);

		color = array.getColor(R.styleable.CustmerUi_first_view_textColor, 0);
		textSize = array.getDimensionPixelSize(R.styleable.CustmerUi_first_view_textSize, 0);
		textword = array.getString(R.styleable.CustmerUi_first_view_textWord);

		// Rect rect = new Rect();

		String strtmp = "__color___" + color + "__textSize___" + textSize + "__textword___" + textword;
		System.out.println(strtmp);
		paint.setTextSize(textSize);
		array.recycle();// һ��Ҫ���ã�������ε��趨����´ε�ʹ�����Ӱ��
	}

	/*
	 * 1��ʲôʱ�����onMeasure������ ���ؼ��ĸ�Ԫ����Ҫ���øÿؼ�ʱ����
	 * .��Ԫ�ػ����ӿؼ�һ�����⣬������Ҫ�ö��ط���������Ȼ����������������widthMeasureSpec��heightMeasureSpec.
	 * ����������ָ���ؼ��ɻ�õĿռ��Լ���������ռ�������Ԫ����.
	 * 
	 * ���õķ������㴫��View�ĸ߶ȺͿ�ȵ�setMeasuredDimension������,��������ֱ�Ӹ��߸��ؼ�����Ҫ���ط������ӿؼ�.
	 * 
	 * widthMeasureSpec��heightMeasureSpec��2���������������ǳ���Ч�ʵĿ��ǣ����Ծ���Ҫ���ľ��Ƕ������=>
	 * 
	 * ʶ��ģʽ int specMode = MeasureSpec.getMode(measureSpec);
	 * ����ģʽ��UNSPECIFIED��δָ���� - EXACTLY����ȷ�� - AT_MOST�����ɻ�ȡ��
	 * 
	 * ʶ���� int specSize = MeasureSpec.getSize(measureSpec);
	 */

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// /**
		// * ����
		// * */
		// //width
		// int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		// int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		// //height
		// int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		// int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		//
		// int width=0;
		// int height=0;
		//
		// if (widthMode == MeasureSpec.EXACTLY) {
		// width = widthSize;
		// }else {
		//
		// }
		//
		// if (heightMode == MeasureSpec.EXACTLY) {
		// height = heightSize;
		// }else {
		//
		// }
		//
		// setMeasuredDimension(width, height);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		paint.setTextSize(textSize);
		// paint.setColor(Color.BLUE);

		paint.setColor(color);

		canvas.drawRect(getMeasuredWidth(), getMeasuredHeight(), 0, 0, paint);
		// canvas.drawRect(r, paint);//void
		// android.graphics.Canvas.drawRect(RectF rect, Paint paint)
		super.onDraw(canvas);
		paint.setColor(Color.WHITE);
		canvas.drawText(textword, 110, 110, paint);
	}

}
