package com.zj.myfuncdemos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import zj.myfuncdemos.R;

public class SwipeRefActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipereflayout);
		final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.sw_test);
		refreshLayout.setColorSchemeResources(R.color.color_bule);
		refreshLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						Toast.makeText(SwipeRefActivity.this,
								" onRefresh test", 0).show();

						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								refreshLayout.setRefreshing(false);
							}
						}, 1500);

					}

				});

		listView = (ListView) findViewById(R.id.sw_list);

		listView.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				TextView textView = new TextView(SwipeRefActivity.this);
				textView.setText("������");
				return textView;
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public Object getItem(int arg0) {
				return arg0;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 10;
			}
		});
	}

}
