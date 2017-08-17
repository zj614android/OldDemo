package com.zj.myfuncdemos;

import java.util.ArrayList;
import java.util.List;
import com.zj.myfuncdemos.net.LogicUtil;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import zj.myfuncdemos.R;

/*
 RecyclerView�����Ѿ���һ��ʱ���ˣ����Ŵ�ҿ϶���İ���ˣ���ҿ���ͨ������support-v7�������ʹ�á� 
 �ݹٷ��Ľ��ܣ��ÿؼ����������޵Ĵ�����չʾ�������ݼ�����ʵ�������ܵĿؼ����ǲ���İ�������磺ListView��GridView��
 ��ô����ListView��GridViewΪʲô����ҪRecyclerView�����Ŀؼ��أ������Ͽ�RecyclerView�ܹ����ṩ��һ�ֲ��ʽ�����飬�߶ȵĽ���쳣����ͨ���������ṩ�Ĳ�ͬLayoutManager��ItemDecoration , ItemAnimatorʵ�������Ŀ��Ч����
 ����Ҫ��������ʾ�ķ�ʽ����ͨ�����ֹ�����LayoutManager
 ����Ҫ����Item��ļ�����ɻ��ƣ�����ͨ��ItemDecoration
 ����Ҫ����Item��ɾ�Ķ�������ͨ��ItemAnimator
 ����Ҫ���Ƶ���������¼������Լ�д������������ꡣ��
 */
public class RecyclerViewActivity extends BaseActivity {

	private RecyclerView recyclerView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_activity_layout);
		init();
	}

	/**
	 * viewholder
	 * */
	class mViewHolder extends ViewHolder {

		TextView tv_word = null;
		LinearLayout ll_blue_rec_back = null;

		public mViewHolder(View itemview) {
			super(itemview);
			tv_word = (TextView) itemview.findViewById(R.id.word_tv);
			ll_blue_rec_back = (LinearLayout) itemview
					.findViewById(R.id.ll_blue_rec_back);
		}

	}

	/**
	 * adapter
	 * */
	class mRecyclerViewAdapter extends RecyclerView.Adapter<mViewHolder> {

		private List<String> datas = null;
		private int counttest;

		public mRecyclerViewAdapter(Context context, List<String> datas) {
			this.datas = datas;
		}

		@Override
		public int getItemCount() {
			return datas.size();
		}

		/**
		 * ����viewholder
		 **/
		@Override
		public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

			System.out.println("onCreateViewHolder");

			View inflateview = View.inflate(RecyclerViewActivity.this,
					R.layout.tv_item, null);

			LinearLayout ll_blue_rec_back = (LinearLayout) inflateview
					.findViewById(R.id.ll_blue_rec_back);

			int height = LogicUtil.TypeValue_Dp_To_Px(100,
					MyDemosApplication.mContext);

			LayoutParams layoutParams = ll_blue_rec_back.getLayoutParams();

			// �ٲ�����˼����������ɱ�׼��gridview Ȼ���ȡһ�����������Ϊ���
			// (��������)(��Сֵ+Math.random()*(���ֵ-��Сֵ+1))

			int randomHeight = (int) ((height / 2) + Math.random()
					* (height - (height / 2) + 1));
			if (randomHeight < (height / 2)) {
				randomHeight = height;
			}

			layoutParams.height = randomHeight;
			ll_blue_rec_back.setLayoutParams(layoutParams);

			return new mViewHolder(inflateview);
		}

		/**
		 * ��view
		 * */
		@Override
		public void onBindViewHolder(mViewHolder holder, int position) {

			holder.tv_word.setText(datas.get(position));
			System.out.println("onBindViewHolder  counttest== " + counttest++);

			// LayoutParams layoutParams =
			// holder.ll_blue_rec_back.getLayoutParams();
			// int height = layoutParams.height;
			// // �ٲ�����˼����������ɱ�׼��gridview Ȼ���ȡһ�����������Ϊ���
			// // (��������)(��Сֵ+Math.random()*(���ֵ-��Сֵ+1))
			//
			// int randomHeight = (int) ((height / 2) + Math.random() * (height
			// - (height / 2) + 1));
			// if (randomHeight < (height / 2)) {
			// randomHeight = height;
			// }
			//
			// layoutParams.height = randomHeight;
			// holder.ll_blue_rec_back.setLayoutParams(layoutParams);

		}
	}

	/**
	 * ��ʼ��
	 * */
	private void init() {
		recyclerView = (RecyclerView) findViewById(R.id.id_recycleview);
		
		// ����layoutManager
		/**
		 * 
		 * StaggeredGridLayoutManager.setLayoutManager �������� int spanCount = 3;
		 * ����
		 * 
		 * int orientation ���� StaggeredGridLayoutManager.VERTICAL;
		 * 
		 * */
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
		mRecyclerViewAdapter adapter = new mRecyclerViewAdapter(RecyclerViewActivity.this, makeDatas());

		// ����item֮��ļ��
		SpacesItemDecoration decoration = new SpacesItemDecoration(20);
		recyclerView.addItemDecoration(decoration);

		// ���ö���
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		// ����adapter
		recyclerView.setAdapter(adapter);
	}

	/**
	 * �Զ���ָ���
	 * */
	public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

		private int space = 0;

		public SpacesItemDecoration(int space) {
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

	/**
	 * ������
	 * */
	private List<String> makeDatas() {
		List<String> mDatas = null;
		if (mDatas == null)
			mDatas = new ArrayList<String>();

		for (int i = 0; i < 35; i++) {
			mDatas.add("item " + i);
		}

		return mDatas;
	}

}

// ��ע����������ʹ��RecyclerViewʱ���������ͨ����������õ����ݣ������ڼ���ͼƬ�����б���ʱ��//
// ����������Ҫ�жϵ�ǰ��Ļ����ʾ�ĵ�һ��item��postion�Ƕ��٣����һ��item��postion�Ƕ��٣�//
// ��ǰ��Ļ����ʾ�˼���item����ʹ��ListView����GridView���жϵ�Ȼ���жϣ�//
// ֻҪʵ��AbsListView.OnScrollListener����ӿھͿ��Եõ���//
// ��ô��RecyclerView����εõ��أ�RecyclerView��ֻ�ṩ��һ������������addOnScrollListener��//
// ���Ҳ���ʵ��AbsListView.OnScrollListener����ӿڣ�����ô��ȡ�أ�//
// ����ͨ��RecyclerView�е�LayoutManager��//
// ����ʹ��RecyclerViewʱ�򶼱�������һ��LayoutManager���ֹ�������//
// ͨ�������ǿ������ɵĵõ���ǰ��Ļ���м���item����һ��item��postion�����ݣ�����//
// ---------------
// ���ص�ǰ��Ļ��һ����ʾ��item��postion
// int firstIndex = mLayoutManager.findFirstVisibleItemPosition();
// ���ص�ǰ��Ļ��һ����ȫ��ʾ��item��postion
// int firstComIndex = mLayoutManager.findFirstCompletelyVisibleItemPosition();
// ���ص�ǰ��Ļ���һ����ʾ��item��position
// int lastIndex = mLayoutManager.findLastVisibleItemPosition();
// ���ص�ǰ��Ļ���һ����ȫ��ʾ��item��posion
// int lastComIndex = mLayoutManager.findLastCompletelyVisibleItemPosition();
// ���ض�Ӧpostion��itemView
// View view = mLayoutManager.findViewByPosition(int postion);