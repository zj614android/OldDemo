package com.zj.myfuncdemos.custmerui.view;

import com.zj.myfuncdemos.MyDemosApplication;
import zj.myfuncdemos.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageTextTabView extends LinearLayout {

	private View view;
	private ImageView tabimg;
	private TextView tabtv;
	private int focusid;
	private int unfocusid;
	int focusColor;
	int unfoucsColor;
	int textsize;
	String text;

	public ImageTextTabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		view = View.inflate(MyDemosApplication.mContext,R.layout.iamgetexttabview, this);
		tabimg = (ImageView) view.findViewById(R.id.picstabimg);
		tabtv = (TextView) view.findViewById(R.id.picstabtexts);
	}

	public void setImageResId(int focusid, int unfocusid) {
		this.focusid = focusid;
		this.unfocusid = unfocusid;
	}

	public void setTextColorId(int focusColor, int unfoucsColor, String text,
			int textsize) {
		this.focusColor = focusColor;
		this.unfoucsColor = unfoucsColor;
		this.text = text;
		this.textsize = textsize;
	}

	public ImageTextTabView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ImageTextTabView(Context context) {
		this(context, null);
	}

	private void setTexts(String text, int textsizesp, int colorid) {
		tabtv.setTextSize(textsizesp);
		tabtv.setText(text);
		tabtv.setTextColor(getResources().getColor(colorid));
	}

	private void setImageRes(int resid) {
		tabimg.setImageDrawable(getResources().getDrawable(resid));
	}

	public void setFoucs() {
		setTexts(this.text, this.textsize, this.focusColor);
		setImageRes(this.focusid);
	}

	public void setUnFoucs() {
		setTexts(this.text, this.textsize, this.unfoucsColor);
		setImageRes(this.unfocusid);
	}

}
