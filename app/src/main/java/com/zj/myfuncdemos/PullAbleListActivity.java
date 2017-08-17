package com.zj.myfuncdemos;


import java.util.ArrayList;
import java.util.List;

import com.zj.myfuncdemos.custmerui.view.PullToRefreshLayout;
import com.zj.myfuncdemos.custmerui.view.pull.MyAdapter;
import com.zj.myfuncdemos.custmerui.view.pull.MyListener;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import zj.myfuncdemos.R;

public class PullAbleListActivity extends BaseActivity {
	ListView listView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pullablelistview);
		((PullToRefreshLayout) findViewById(R.id.refresh_root_view)).setOnRefreshListener(new MyListener());
		listView = (ListView) findViewById(R.id.content_view);
		initListView();
		
	}
	
	/**
	 * ListView��ʼ������
	 */
	private void initListView()
	{
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < 30; i++)
		{
			items.add("������item " + i);
		}
		
		MyAdapter adapter = new MyAdapter(this, items);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Toast.makeText(
						PullAbleListActivity.this,
						"LongClick on "
								+ parent.getAdapter().getItemId(position),
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Toast.makeText(PullAbleListActivity.this,
						" Click on " + parent.getAdapter().getItemId(position),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	
}
