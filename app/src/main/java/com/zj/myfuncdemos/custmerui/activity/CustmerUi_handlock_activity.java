package com.zj.myfuncdemos.custmerui.activity;
import zj.myfuncdemos.R;
import com.zj.myfuncdemos.custmerui.view.GestureLock;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class CustmerUi_handlock_activity extends Activity implements
		GestureLock.GestureCallback {

	private GestureLock gestureLock = null;
	private TextView tv_titlecontent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��
		setContentView(R.layout.custmer_ui_handlock_activity_layout);
		gestureLock = (GestureLock) findViewById(R.id.custmerui_handlock_view);
		tv_titlecontent = (TextView) findViewById(R.id.titletext);

		gestureLock.setCallback(this);
		gestureLock.setActivity(this);
	}

	@Override
	public void refreshTitleText(int type, int times) {
		switch (type) {
		case -1:
			tv_titlecontent.setText("У��ʧ�ܣ��˳��˻�");
			break;
		case GestureLock.GESTURE_TITLE_CONTENT_TYPE_CHECK_FALSE:
			tv_titlecontent.setText("У��ʧ��,��������" + times + "��");
			break;
		case GestureLock.GESTURE_TITLE_CONTENT_TYPE_CHECK_OK:
			tv_titlecontent.setText("����ɹ�");
			break;
		case GestureLock.GESTURE_TITLE_CONTENT_TYPE_ENTER:
			tv_titlecontent.setText("��δ�����������룬����������");
			break;
		case GestureLock.GESTURE_TITLE_CONTENT_TYPE_CHECK:
			tv_titlecontent.setText("�Ѿ����ù��������룬����������");
			break;
		case GestureLock.GESTURE_TITLE_CONTENT_TYPE_ENTER_AGAIN:
			tv_titlecontent.setText("���ٴ�����");
			break;
		case GestureLock.GESTURE_TITLE_CONTENT_TYPE_ENTER_TWICEERROR:
			tv_titlecontent.setText("2���������벻һ��");
			break;
		case GestureLock.GESTURE_TITLE_CONTENT_TYPE_ENTER_SHORTERROR:
			tv_titlecontent.setText("���볤�ȱ������3");
			break;
		}
	}

}
