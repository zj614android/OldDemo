package com.zj.myfuncdemos;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import zj.myfuncdemos.R;

public class Fragment_by_viewpager extends Activity {
	private ViewPager pager;
	private RadioButton radioButton1 = null;
	private RadioButton radioButton2 = null;
	private RadioButton radioButton3 = null;
	private RadioButton radioButton4 = null;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_by_viewpager);
		pager = (ViewPager) findViewById(R.id.m_viewpager);
		mPagerAdapter mpageradapter = new mPagerAdapter();
		List<View> viewlist = new ArrayList<View>();
		viewlist.add(View.inflate(Fragment_by_viewpager.this,
				R.layout.fragment_content_framlayout_1, null));
		viewlist.add(View.inflate(Fragment_by_viewpager.this,
				R.layout.fragment_content_framlayout_2, null));
		viewlist.add(View.inflate(Fragment_by_viewpager.this,
				R.layout.fragment_content_framlayout_3, null));
		viewlist.add(View.inflate(Fragment_by_viewpager.this,
				R.layout.fragment_content_framlayout_4, null));
		mpageradapter.setData(viewlist);
		pager.setAdapter(mpageradapter);

		radioButton1 = (RadioButton) findViewById(R.id.rd_1);
		radioButton2 = (RadioButton) findViewById(R.id.rd_2);
		radioButton3 = (RadioButton) findViewById(R.id.rd_3);
		radioButton4 = (RadioButton) findViewById(R.id.rd_4);

		radioGroup = (RadioGroup) findViewById(R.id.radio_group);

		onclick();

	}

	/**
	 * ����ʹ����¼� �Ĵ���
	 * */
	private void onclick() {

		// ����Ϊ radiobutton���� ������
		// final RadioButton red_radio = (RadioButton)
		// findViewById(R.id.red_radio);
		// final RadioButton gray_radio = (RadioButton)
		// findViewById(R.id.gray_radio);
		// gray_radio.setOnCheckedChangeListener(new
		// CompoundButton.OnCheckedChangeListener() {
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked) {
		// red_radio.setChecked(false);
		// }
		// });
		radioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (checkedId == radioButton1.getId()) {
							pager.setCurrentItem(0);

						} else if (checkedId == radioButton2.getId()) {
							pager.setCurrentItem(1);

						} else if (checkedId == radioButton3.getId()) {
							pager.setCurrentItem(2);

						} else if (checkedId == radioButton4.getId()) {
							pager.setCurrentItem(3);
						}
					}
				});

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				Toast.makeText(Fragment_by_viewpager.this,
						"��ǰ�ǵ�" + (arg0 + 1) + "ҳ", 0).show();

				switch (arg0) {
				case 0:
					radioButton1.setChecked(true);
					break;
				case 1:
					radioButton2.setChecked(true);
					break;
				case 2:
					radioButton3.setChecked(true);
					break;
				case 3:
					radioButton4.setChecked(true);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		RadioButton rd1 = (RadioButton) findViewById(R.id.rd_1);
		rd1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

	}

	private class mPagerAdapter extends PagerAdapter {

		private List<View> viewlist;

		public void setData(List<View> viewlist) {
			this.viewlist = viewlist;
		}

		@Override
		public int getCount() {
			return viewlist.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) { // ʵ����item
			container.addView(viewlist.get(position));
			return viewlist.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewlist.get(position));
		}

	}

}
