package com.zj.myfuncdemos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.zj.myfuncdemos.fragment.Fragment_1;
import com.zj.myfuncdemos.fragment.Fragment_2;
import com.zj.myfuncdemos.fragment.Fragment_3;
import com.zj.myfuncdemos.fragment.Fragment_4;

import zj.myfuncdemos.R;

public class Fragment_by_fragMngReplace extends FragmentActivity implements
		OnClickListener {
	private RadioButton rd1;
	private RadioButton rd2;
	private RadioButton rd3;
	private RadioButton rd4;
	private Fragment_1 frg_1;
	private Fragment_2 frg_2;
	private Fragment_3 frg_3;
	private Fragment_4 frg_4;
	private Fragment_2 frg_22;
	private Fragment_3 frg_32;
	private Fragment_4 frg_42;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		rd1 = (RadioButton) findViewById(R.id.rd_1);
		rd2 = (RadioButton) findViewById(R.id.rd_2);
		rd3 = (RadioButton) findViewById(R.id.rd_3);
		rd4 = (RadioButton) findViewById(R.id.rd_4);

		rd1.setOnClickListener(this);
		rd2.setOnClickListener(this);
		rd3.setOnClickListener(this);
		rd4.setOnClickListener(this);

		setDefaultFrag();
	}

	private void setDefaultFrag() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction trans = fm.beginTransaction();
		frg_1 = new Fragment_1();
		frg_2 = new Fragment_2();
		frg_3 = new Fragment_3();
		frg_4 = new Fragment_4();
		trans.replace(R.id.content_framelayout, frg_1);
		trans.commit();
	}

	@Override
	public void onClick(View v) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction trans = fm.beginTransaction();

		switch (v.getId()) {
		case R.id.rd_1:
			trans.replace(R.id.content_framelayout, frg_1);
			trans.commit();
			break;
		case R.id.rd_2:
			trans.replace(R.id.content_framelayout, frg_2);
			trans.commit();
			break;
		case R.id.rd_3:
			trans.replace(R.id.content_framelayout, frg_3);
			trans.commit();
			break;
		case R.id.rd_4:
			trans.replace(R.id.content_framelayout, frg_4);
			trans.commit();
			break;
		default:
			break;
		}
	}

}
