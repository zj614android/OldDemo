package com.zj.myfuncdemos;

import java.util.ArrayList;
import java.util.List;
import zj.myfuncdemos.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zj.myfuncdemos.fragment.Fragment_1;
import com.zj.myfuncdemos.fragment.Fragment_2;
import com.zj.myfuncdemos.fragment.Fragment_3;
import com.zj.myfuncdemos.fragment.Fragment_4;

public class Fragment_by_fragmentpageradapter extends FragmentActivity {

	private ViewPager pager = null;
	private List<Fragment> datasource = null;
	private mFragPagerAdapter fragPagerAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentpageradapter_activity);

		pager = (ViewPager) findViewById(R.id.vp_by_fragpageradapter);

		datasource = new ArrayList<Fragment>();
		datasource.add(new Fragment_1());
		datasource.add(new Fragment_2());
		datasource.add(new Fragment_3());
		datasource.add(new Fragment_4());

		fragPagerAdapter = new mFragPagerAdapter(getSupportFragmentManager());
		fragPagerAdapter.setDatasource(datasource);
		pager.setAdapter(fragPagerAdapter);

	}

	class mFragPagerAdapter extends FragmentPagerAdapter {

		List<Fragment> datasource = null;

		public void setDatasource(List<Fragment> datasource) {
			this.datasource = datasource;
		}

		public mFragPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {

			if (datasource != null)
				return datasource.get(arg0);

			return null;
		}

		@Override
		public int getCount() {
			if (datasource != null)
				return datasource.size();

			return 0;
		}
	}
}
