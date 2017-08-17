package com.zj.myfuncdemos;

import com.zj.myfuncdemos.custmerui.view.ImgEditext;
import com.zj.myfuncdemos.net.LogicUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import zj.myfuncdemos.R;

public class ImgEditTextActivity extends Activity {

	private ImgEditext imgEditext;
	private EditText myeditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_imgedittext);
		imgEditext = (ImgEditext) findViewById(R.id.myimgedittext);
		// imgEditext.setRightSize(LogicUtil.TypeValue_Dp_To_Px(35,
		// MyDemosApplication.mContext), LogicUtil.TypeValue_Dp_To_Px(35,
		// MyDemosApplication.mContext));
		ImageView imageView = new ImageView(ImgEditTextActivity.this);
		imageView.setImageResource(R.drawable.ic_launcher);
		imgEditext.addContentView(imageView);

		myeditText = (EditText) findViewById(R.id.edittext_test);
		myeditText.setInputType(0x81);

		// .setInputType(0x81) //EditText��Ϊ����״̬��
		// .setInputType(0x90) //EditText��Ϊ������״̬��

		findViewById(R.id.bt_pass_what).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						myeditText.setInputType(0x90);
					}
				});

	}

}
