package com.zj.myfuncdemos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zj.myfuncdemos.R;

public class ViewPagersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pagers_layout);
		Button pager_base = (Button) findViewById(R.id.viewpager_type_1);
		pager_base.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(ViewPagersActivity.this,
						ViewPager_Base.class));
			}
		});

		Button pager_titlestrip = (Button) findViewById(R.id.viewpager_type_2);
		pager_titlestrip.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(ViewPagersActivity.this,
						ViewPager_With_PagerTitleStrip.class));
			}
		});

		Button pager_pagertabstrip = (Button) findViewById(R.id.viewpager_type_3);
		pager_pagertabstrip.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(ViewPagersActivity.this,
						ViewPager_With_PagerTabStrip.class));
			}
		});

	}

}
