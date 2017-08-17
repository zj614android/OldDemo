package com.zj.myfuncdemos.custmerui.view;

import zj.myfuncdemos.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ImgEditext extends FrameLayout {

	private Context context;
	private View inflate;
	private LinearLayout rightlayout;
	private LayoutParams rightlayoutParams;

	public ImgEditext(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	private void init() {
		inflate = View.inflate(context, R.layout.layout_imgedittextview,this);
		rightlayout = (LinearLayout) inflate.findViewById(R.id.right_view);
	}
	
	
	public void setRightSize(int widthpx,int heightpx){
		rightlayoutParams = (LayoutParams) rightlayout.getLayoutParams();
		rightlayoutParams.width = widthpx;
		rightlayoutParams.height = heightpx;
		rightlayout.setLayoutParams(rightlayoutParams);
	}

	public ImgEditext(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ImgEditext(Context context) {
		this(context, null);
	}
	
	public void addContentView(View child) {
		rightlayout.addView(child);
	}

}
