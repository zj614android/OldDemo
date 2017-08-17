package com.zj.myfuncdemos;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.zj.myfuncdemos.SpinnerActivity.SpinnerSelectedListener;
import com.zj.myfuncdemos.custmerui.view.DynamicAnimProgressView;
import com.zj.myfuncdemos.net.LogicUtil;
import com.zj.myfuncdemos.util.ScreenUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import zj.myfuncdemos.R;

public class PropertiesAnimActivity extends BaseActivity {

	final String translationX = "ObjectAnimator __ translationX";
	final String ValueAnimator = "ValueAnimator __ translation";

	private Spinner spinner;
	private ImageView redball;
	private final String[] datasource = { translationX, ValueAnimator, "null",
			"null", "null" };
	private mSpinnerAdapter mAdapter;
	private Button anim_projectprogressview;
	private View updowntestview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.property_anim_activity);
		anim_projectprogressview = (Button) findViewById(R.id.anim_projectprogressview);

		spinner = (Spinner) findViewById(R.id.animator_spinner);
		
		redball = (ImageView) findViewById(R.id.img_ball);
		redball.measure(redball.getMeasuredWidth(), redball.getMeasuredHeight());
		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_spinner_item, datasource);

		mAdapter = new mSpinnerAdapter();
		mAdapter.setDatasource(datasource);

		// ���������б�ķ��
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ��ӵ�spinner��
		spinner.setAdapter(mAdapter);

		// ����¼�Spinner�¼�����
		spinner.setOnItemSelectedListener(new mSpinerListener());
		
		
		
		updowntestview = findViewById(R.id.updowntestview);
		updowntestview.measure(updowntestview.getMeasuredWidth(), updowntestview.getMeasuredHeight());
		
		anim_projectprogressview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ObjectAnimator
				.ofFloat(
						updowntestview,
						"translationY",
						800f,
						-100f)
				.setDuration(1000).start();
				
				
			}
		});
		
	}

	// spinner �ĵ������
	class mSpinerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> adapter, View view,
				int position, long arg3) {

			if (mAdapter != null) {
				String itemword = (String) mAdapter.getItem(position);
				System.out.println("itemword == " + itemword);
				if (itemword.equals(translationX)) {
					
					ObjectAnimator
							.ofFloat(
									redball,
									"translationX",
									50f,
									ScreenUtils
											.getScreenWidth(MyDemosApplication.mContext)
											- redball.getMeasuredWidth())
							.setDuration(1000).start();

				} else if (itemword.equals(ValueAnimator)) {

					
				}
			} else {
			}

		}

		public void onNothingSelected(AdapterView<?> arg0) {

		}
	}

	/**
	 * spinner��������
	 * */
	class mSpinnerAdapter extends BaseAdapter {

		String[] datasource = null;

		public void setDatasource(String[] datasource) {
			this.datasource = datasource;
		}

		@Override
		public int getCount() {
			return datasource.length;
		}

		@Override
		public Object getItem(int position) {
			return datasource[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TextView tv = new
			// TextView(MyDemosApplication.mContext);//����һ��textview
			// tv.setText("Large Text");
			// tv.setTextSize(20);
			// tv.setTextColor(Color.BLACK);

			View inflateview = View.inflate(MyDemosApplication.mContext,
					R.layout.tv_item, null);

			LinearLayout ll_blue_rec_back = (LinearLayout) inflateview
					.findViewById(R.id.ll_blue_rec_back);

			LayoutParams layoutParams = ll_blue_rec_back.getLayoutParams();
			layoutParams.height = 10;
			ll_blue_rec_back.setLayoutParams(layoutParams);

			TextView word_tv = (TextView) inflateview
					.findViewById(R.id.word_tv);
			LayoutParams tv_layoutParams = word_tv.getLayoutParams();
			tv_layoutParams.height = LogicUtil.TypeValue_Dp_To_Px(30,
					MyDemosApplication.mContext);
			word_tv.setLayoutParams(tv_layoutParams);

			word_tv.setText(datasource[position]);
			return inflateview;
		}
	}

}
