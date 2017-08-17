package com.zj.myfuncdemos.custmerui.view;

import zj.myfuncdemos.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleBottmLineTab extends LinearLayout {

	private TextView simple_bottom_line_tab_text;
	private View bottomline;

	public SimpleBottmLineTab(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public SimpleBottmLineTab(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SimpleBottmLineTab(Context context) {
		this(context, null);
	}

	/**
	 * ��ʼ��
	 * */
	private void init(Context context) {
		View inflate = View.inflate(context,
				R.layout.simplebottomlinetablayout, this);
		simple_bottom_line_tab_text = (TextView) inflate
				.findViewById(R.id.simple_bottom_line_tab_text);

		bottomline = inflate.findViewById(R.id.simple_bottom_line_tab_line);
	}

	public TextView getTextView() {
		return simple_bottom_line_tab_text;
	}

	public View getLine() {
		return bottomline;
	}
}
