package com.zj.myfuncdemos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import zj.myfuncdemos.R;

public class SpinnerActivity extends BaseActivity {
	private static final String[] datasource = { "A��", "B��", "O��", "AB��", "����" };
	private TextView testview;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_activity_layout);
		testview = (TextView) findViewById(R.id.spinnerText);
		spinner = (Spinner) findViewById(R.id.Spinner01);

		// ����ѡ������ArrayAdapter��������
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datasource);

		// ���������б�ķ��
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ��ӵ�spinner��
		spinner.setAdapter(adapter);

		// ����¼�Spinner�¼�����
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

		// ����Ĭ��ֵ
		spinner.setVisibility(View.VISIBLE);

	}

	// ʹ��������ʽ����
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> adapter, View view,
				int position, long arg3) {
			testview.setText("���Ѫ���ǣ�" + datasource[position]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

}
