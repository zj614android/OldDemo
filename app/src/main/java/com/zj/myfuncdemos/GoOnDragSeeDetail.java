package com.zj.myfuncdemos;

import com.zj.myfuncdemos.util.ScreenUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import zj.myfuncdemos.R;

public class GoOnDragSeeDetail extends BaseActivity {

	private RelativeLayout root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goonseedetaillayout);
		/**
		 * up
		 * */
		View scroll_inner_1 = findViewById(R.id.scroll_inner_1);
		android.view.ViewGroup.LayoutParams lay_scroll_inner_1 = scroll_inner_1
				.getLayoutParams();
		lay_scroll_inner_1.width = LayoutParams.FILL_PARENT;
		lay_scroll_inner_1.height = ScreenUtils
				.getScreenHeight(MyDemosApplication.mContext);
		scroll_inner_1.setLayoutParams(lay_scroll_inner_1);

		/**
		 * dwon
		 * */
		View scroll_inner_2 = findViewById(R.id.scroll_inner_2);
		android.view.ViewGroup.LayoutParams lay_scroll_inner_2 = scroll_inner_2
				.getLayoutParams();
		lay_scroll_inner_2.width = LayoutParams.FILL_PARENT;
		lay_scroll_inner_2.height = ScreenUtils
				.getScreenHeight(MyDemosApplication.mContext);
		scroll_inner_2.setLayoutParams(lay_scroll_inner_2);

		/**
		 * root
		 * */
		root = (RelativeLayout) findViewById(R.id.scollcontentroot);
		android.view.ViewGroup.LayoutParams root_layoutParams = root
				.getLayoutParams();
		root_layoutParams.width = LayoutParams.MATCH_PARENT;
		root_layoutParams.height = ScreenUtils
				.getScreenHeight(MyDemosApplication.mContext) * 2;

		root.setLayoutParams(root_layoutParams);

	}

}
