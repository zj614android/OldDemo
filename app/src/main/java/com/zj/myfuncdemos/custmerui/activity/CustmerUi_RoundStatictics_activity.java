package com.zj.myfuncdemos.custmerui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import zj.myfuncdemos.R;
import com.zj.myfuncdemos.custmerui.view.RoundStatistics;

public class CustmerUi_RoundStatictics_activity extends Activity {

	private RoundStatistics rounds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��
		setContentView(R.layout.roundstaticstics_activity);
		rounds = (RoundStatistics) findViewById(R.id.rounds);

		RoundStatistics.RoundStatisticsBean[] round_items = new RoundStatistics.RoundStatisticsBean[] {
				new RoundStatistics.RoundStatisticsBean(
						Color.parseColor("#ffE3F0FC"), 1, "������Ϣ(Ԫ)"),
				new RoundStatistics.RoundStatisticsBean(
						Color.parseColor("#ffC8E2F8"), 0, "������(Ԫ)"),
				new RoundStatistics.RoundStatisticsBean(
						Color.parseColor("#ff7FC1EF"), 0, "���ձ���(Ԫ)"),
				new RoundStatistics.RoundStatisticsBean(
						Color.parseColor("#ff119AE5"), 0, "������Ϣ(Ԫ)"),
				new RoundStatistics.RoundStatisticsBean(
						Color.parseColor("#ffEDF5FD"), 0, "���ý��(Ԫ)") };

		rounds.setItems(round_items);

	}

}
