package com.zj.myfuncdemos.custmerui.view;

import com.zj.myfuncdemos.MyDemosApplication;
import zj.myfuncdemos.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CenterTextViewViewGroup extends RelativeLayout {

	private Context context;
	private TextView innertext;
	private View layoutview;
	private RelativeLayout rl_father;
	private ViewGroup.LayoutParams innertextlayoutParams;
	private RelativeLayout.LayoutParams rl_layout_par;

	public CenterTextViewViewGroup(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * setContext
	 * */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * ������
	 * */
	public void setText(String text) {
		innertext.setText(text);
	}

	/**
	 * �����ִ�С
	 * */
	public void setTextSize(int size) {
		innertext.setTextSize(size);
	}

	/**
	 * ��������ɫ
	 * */
	public void setTextColor(int colorid) {
		innertext.setTextColor(getResources().getColor(colorid));
	}

	/**
	 * ��ʼ��
	 * */
	private void init() {
		layoutview = View.inflate(MyDemosApplication.mContext,
				R.layout.centertextviewgroup_layout, this);
		innertext = (TextView) layoutview.findViewById(R.id.single_text_center);
		innertextlayoutParams = innertext.getLayoutParams();
		rl_layout_par = new LayoutParams(innertextlayoutParams);
	}

	public void setLayoutCenter() {
		rl_layout_par.addRule(RelativeLayout.CENTER_IN_PARENT,
				R.id.single_text_center);
		innertext.setLayoutParams(rl_layout_par);
	}

	public void setLayoutLeft() {
		rl_layout_par.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				R.id.single_text_center);
		rl_layout_par.addRule(RelativeLayout.CENTER_VERTICAL,
				R.id.single_text_center);
		innertext.setLayoutParams(rl_layout_par);
	}

	public void setLayoutRight() {
		rl_layout_par.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				R.id.single_text_center);
		innertext.setLayoutParams(rl_layout_par);
	}

	public void setLayoutUpon() {
		rl_layout_par.addRule(RelativeLayout.ALIGN_PARENT_TOP,
				R.id.single_text_center);
		innertext.setLayoutParams(rl_layout_par);
	}

	public void setLayoutBottom() {
		rl_layout_par.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				R.id.single_text_center);
		innertext.setLayoutParams(rl_layout_par);
	}

	public CenterTextViewViewGroup(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CenterTextViewViewGroup(Context context) {
		this(context, null);
	}

}
