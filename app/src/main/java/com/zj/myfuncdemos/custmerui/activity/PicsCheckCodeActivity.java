package com.zj.myfuncdemos.custmerui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zj.myfuncdemos.BaseActivity;
import zj.myfuncdemos.R;
import com.zj.myfuncdemos.custmerui.view.CustomTitleView;
import com.zj.myfuncdemos.custmerui.view.PicsCheckCode;

public class PicsCheckCodeActivity extends BaseActivity {

	private ImageView checkimg;
	private CustomTitleView checkcodes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picscheckcodelayout);
		checkimg = (ImageView) findViewById(R.id.img_check_code);

		PicsCheckCode instance = PicsCheckCode.getInstance();
		checkimg.setImageBitmap(instance.createBitmap());

		TextView tv = (TextView) findViewById(R.id.checkscodes);
		tv.setText(instance.getPicsCheckCode());

		checkcodes = (CustomTitleView) findViewById(R.id.checkcodes);
		String code = checkcodes.getCode();
		tv.setText(code);
	}

}
