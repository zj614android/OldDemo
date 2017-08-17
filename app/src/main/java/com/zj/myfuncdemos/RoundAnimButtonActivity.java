package com.zj.myfuncdemos;

import java.util.Timer;

import com.zj.myfuncdemos.custmerui.view.DynamicAnimProgressView;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.widget.Button;

import zj.myfuncdemos.R;

public class RoundAnimButtonActivity extends BaseActivity {
	
	private DynamicAnimProgressView proview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.roundanimbutton_activity);
		proview = (DynamicAnimProgressView) findViewById(R.id.item_product_projectprogressview);
		proview.setProgressValue(80);
		proview.startAnim();
		
	}

}
