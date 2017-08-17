package com.zj.myfuncdemos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import com.zj.myfuncdemos.custmerui.view.CenterTextViewViewGroup;
import com.zj.myfuncdemos.custmerui.view.DynamicAnimProgressView;
import com.zj.myfuncdemos.net.LogicUtil;
import com.zj.myfuncdemos.util.BitmapUtils;

import zj.myfuncdemos.R;

public class Activity_CardView extends BaseActivity {
	private CardView cardView;
	private com.zj.myfuncdemos.custmerui.view.CenterTextViewViewGroup inner_1;
	private com.zj.myfuncdemos.custmerui.view.CenterTextViewViewGroup inner_2;
	private com.zj.myfuncdemos.custmerui.view.CenterTextViewViewGroup inner_3;

	private DynamicAnimProgressView inner_4;

	private com.zj.myfuncdemos.custmerui.view.CenterTextViewViewGroup inner_5;
	private com.zj.myfuncdemos.custmerui.view.CenterTextViewViewGroup inner_6;
	private com.zj.myfuncdemos.custmerui.view.CenterTextViewViewGroup inner_7;
	private ImageView imgyes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cardviewlayout);
		cardView = (CardView) findViewById(R.id.view_cardview);
		findviewbyids();
		setTexts();
		setTextsizes();
		setTextColor();
		layouts();

		imgyes = (ImageView) findViewById(R.id.img_yes_yes);
		Bitmap bitmap = BitmapFactory.decodeResource(
				Activity_CardView.this.getResources(), R.drawable.welcome);
		Bitmap roundedCornerBitmap = BitmapUtils.fillet(BitmapUtils.LEFT,
				bitmap,
				LogicUtil.TypeValue_Dp_To_Px(350, MyDemosApplication.mContext));
		imgyes.setImageBitmap(roundedCornerBitmap);

		// Button button_yes_yes = (Button) findViewById(R.id.button_yes_yes);
		// button_yes_yes.setBackgroundResource(R.drawable.welcome);
	}

	private void layouts() {
		inner_1.setLayoutCenter();
		inner_2.setLayoutCenter();
		inner_3.setLayoutCenter();
		inner_5.setLayoutCenter();
		inner_6.setLayoutLeft();
		inner_7.setLayoutCenter();
	}

	private void findviewbyids() {
		inner_1 = (CenterTextViewViewGroup) findViewById(R.id.inner_1);
		inner_2 = (CenterTextViewViewGroup) findViewById(R.id.inner_2);
		inner_3 = (CenterTextViewViewGroup) findViewById(R.id.inner_3);
		inner_4 = (DynamicAnimProgressView) findViewById(R.id.inner_4);
		inner_5 = (CenterTextViewViewGroup) findViewById(R.id.inner_bootom_1);
		inner_6 = (CenterTextViewViewGroup) findViewById(R.id.inner_bootom_2);
		inner_7 = (CenterTextViewViewGroup) findViewById(R.id.inner_bootom_3);
	}

	private void setTextColor() {
		inner_1.setTextColor(R.color.color_black);
		inner_2.setTextColor(R.color.color_black);
		inner_3.setTextColor(R.color.color_black);
		// inner_4.setTextColor(R.color.color_black);
		inner_5.setTextColor(R.color.color_black);
		inner_6.setTextColor(R.color.color_black);
		inner_7.setTextColor(R.color.color_black);
	}

	private void setTextsizes() {
		inner_1.setTextSize(LogicUtil.TypeValue_Sp_To_Px(8,
				Activity_CardView.this));
		inner_2.setTextSize(LogicUtil.TypeValue_Sp_To_Px(6,
				Activity_CardView.this));
		inner_3.setTextSize(LogicUtil.TypeValue_Sp_To_Px(6,
				Activity_CardView.this));
		// inner_4.setTextSize(LogicUtil.TypeValue_Sp_To_Px(4,
		// Activity_CardView.this));
		inner_5.setTextSize(LogicUtil.TypeValue_Sp_To_Px(3,
				Activity_CardView.this));
		inner_6.setTextSize(LogicUtil.TypeValue_Sp_To_Px(3,
				Activity_CardView.this));
		inner_7.setTextSize(LogicUtil.TypeValue_Sp_To_Px(3,
				Activity_CardView.this));
	}

	private void setTexts() {
		inner_1.setText("1");
		inner_2.setText("90");
		inner_3.setText("50");
		// inner_4.setText("�ٷֱȶ���");
		inner_5.setText("");
		inner_6.setText("");
		inner_7.setText("");
	}

}
