package com.zj.myfuncdemos;

import java.util.ArrayList;

import com.zj.myfuncdemos.custmerui.view.ImageTextTabView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import zj.myfuncdemos.R;

public class MyPicsBottomBarActivity extends BaseActivity {

	private ArrayList<ImageTextTabView> tabs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_mypicstextbottombar);

		// LinearLayout tab1 = (LinearLayout) findViewById(R.id.child_1);
		// LinearLayout tab2 = (LinearLayout) findViewById(R.id.child_2);

		ImageTextTabView tab1 = (ImageTextTabView) findViewById(R.id.imgtexts_tab1);
		ImageTextTabView tab2 = (ImageTextTabView) findViewById(R.id.imgtexts_tab2);
		ImageTextTabView tab3 = (ImageTextTabView) findViewById(R.id.imgtexts_tab3);
		ImageTextTabView tab4 = (ImageTextTabView) findViewById(R.id.imgtexts_tab4);
		tabs = new ArrayList<ImageTextTabView>();
		tabs.add(tab1);
		tabs.add(tab2);
		tabs.add(tab3);
		tabs.add(tab4);

		tab1.setImageResId(R.drawable.home, R.drawable.home_un);
		tab1.setTextColorId(R.color.color_bule, R.color.color_orange, "��ҳ", 12);

		tab2.setImageResId(R.drawable.investlist, R.drawable.invest_un);
		tab2.setTextColorId(R.color.color_bule, R.color.color_orange, "��ҳ", 12);

		tab3.setImageResId(R.drawable.discover, R.drawable.discover_un);
		tab3.setTextColorId(R.color.color_bule, R.color.color_orange, "��ҳ", 12);

		tab4.setImageResId(R.drawable.me, R.drawable.me_un);
		tab4.setTextColorId(R.color.color_bule, R.color.color_orange, "��ҳ", 12);

		refreshTabs(0);

		tab1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshTabs(0);
			}
		});
		
		tab2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshTabs(1);
			}
		});
		
		tab3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshTabs(2);
			}
		});
		
		tab4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshTabs(3);
			}
		});
		
	}

	private void refreshTabs(int i) {
		tabs.get(i).setFoucs();
		for (int j = 0; j < tabs.size(); j++) {
			if (j != i)
				tabs.get(j).setUnFoucs();
		}
	}

}
