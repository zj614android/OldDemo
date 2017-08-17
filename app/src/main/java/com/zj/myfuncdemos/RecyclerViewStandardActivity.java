package com.zj.myfuncdemos;

import java.util.ArrayList;

import com.zj.myfuncdemos.RecyclerViewActivity.SpacesItemDecoration;
import com.zj.myfuncdemos.net.LogicUtil;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import zj.myfuncdemos.R;

public class RecyclerViewStandardActivity extends BaseActivity {

	private RecyclerView recyclerView = null;

	private ArrayList<Integer> dataheights = null;
	private ArrayList<String> datasources = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_standard_layout);
		makeDatas();

		recyclerView = (RecyclerView) findViewById(R.id.recycler_standard);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
				StaggeredGridLayoutManager.VERTICAL));

		// ����item֮��ļ��
		MyStandardItemDecoration decoration = new MyStandardItemDecoration(20);
		recyclerView.addItemDecoration(decoration);

		// ���ö���
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		mStandardRecyclerViewAdapter adapter = new mStandardRecyclerViewAdapter();

		// ����adapter
		recyclerView.setAdapter(adapter);

	}

	/**
	 * ��������
	 * */
	private void makeDatas() {

		datasources = new ArrayList<String>();
		for (int i = 0; i < 55; i++) {
			datasources.add("item " + i);
		}

		dataheights = new ArrayList<Integer>();
		int max = LogicUtil.TypeValue_Dp_To_Px(85, MyDemosApplication.mContext);
		int min = LogicUtil.TypeValue_Dp_To_Px(35, MyDemosApplication.mContext);

		for (int i = 0; i < datasources.size(); i++) {
			// (��������)(��Сֵ+Math.random()*(���ֵ-��Сֵ+1))
			dataheights.add((int) (min + (Math.random() * (max - min + 1))));
		}
		
	}

	/**
	 * adapter
	 * */
	class mStandardRecyclerViewAdapter extends
			RecyclerView.Adapter<mStandardViewHolder> {

		@Override
		public int getItemCount() {
			return datasources.size();
		}

		/**
		 * ����holder.textview.settext("XXXX");
		 * */
		@Override
		public void onBindViewHolder(mStandardViewHolder holder, int position) {
			holder.tv_word.setText(datasources.get(position));

			// LayoutParams layoutParams = subview.getLayoutParams();
			// layoutParams.height = dataheights.get(position);
			// subview.setLayoutParams(layoutParams);

			LayoutParams layoutParams = holder.ll_blue_rec_back
					.getLayoutParams();
			layoutParams.height = dataheights.get(position);
			holder.ll_blue_rec_back.setLayoutParams(layoutParams);
		}

		/**
		 * ����߳�ʼ�� view ... layout
		 * */
		@Override
		public mStandardViewHolder onCreateViewHolder(ViewGroup viewgroup,
				int position) {
			View subview = View.inflate(MyDemosApplication.mContext,
					R.layout.tv_item, null);
			return new mStandardViewHolder(subview);
		}
	}

	/**
	 * viewholder
	 * */
	class mStandardViewHolder extends ViewHolder {

		TextView tv_word = null;
		LinearLayout ll_blue_rec_back = null;

		public mStandardViewHolder(View itemview) {
			super(itemview);
			tv_word = (TextView) itemview.findViewById(R.id.word_tv);
			ll_blue_rec_back = (LinearLayout) itemview
					.findViewById(R.id.ll_blue_rec_back);
		}
	}

	/**
	 * �ָ���
	 * */

	class MyStandardItemDecoration extends ItemDecoration {

		int space = 0;

		public MyStandardItemDecoration(int space) {
			super();
			this.space = space;
		}
		
		/**
		 * ���÷ָ���С
		 * */
		@Override
		public void getItemOffsets(Rect outRect, View view,
				RecyclerView parent, RecyclerView.State state) {
			outRect.set(space, space, space, space);
		}

		/**
		 * �ػ�ָ��߷��
		 * */
		@Override
		public void onDrawOver(Canvas c, RecyclerView parent, State state) {
			super.onDrawOver(c, parent, state);
		}

	}

}
