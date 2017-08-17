package com.zj.myfuncdemos.custmerui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import zj.myfuncdemos.R;
public class CustmerUi_first_activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��
		setContentView(R.layout.custmer_ui_first_activity_layout);

	}

}
