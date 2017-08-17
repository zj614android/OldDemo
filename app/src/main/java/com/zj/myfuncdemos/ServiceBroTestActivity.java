package com.zj.myfuncdemos;
//package com.zj.myfuncdemos.ServiceBroTestActivity.mBroadcast;

import com.zj.myfuncdemos.net.LogicUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import zj.myfuncdemos.R;

/**
 * ��򵥵�ͨ���㲥��ˢ��ҳ�� ������activity����߶���һ���ڲ��㲥��������onrecive������Բ�����в���
 * */
public class ServiceBroTestActivity extends BaseActivity {

	private TextView tvbro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicebroadcastlayout);
		tvbro = (TextView) findViewById(R.id.tv_bro);
		registerBroadcast();
		
		//��̬�㲥 �ǳ�ס�� 
//		Intent intent = new Intent();
//		intent.setAction("yes oh no !_,.");
//		sendOrderedBroadcast(intent, null);
		
		//��̬�㲥 xmlע�� ��פ��
		Intent intent2 = new Intent();
		intent2.setAction("rectR");
		sendOrderedBroadcast(intent2, null);
		
	}
	
	/**
	 * ��̬ע��һ���㲥
	 * */
	private void registerBroadcast() {
		mBroadcast br = new mBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.addAction("yes oh no !_,.");
		filter.setPriority(1000);
		
		//������ϵͳ���㲥
//		filter.addAction(Intent.ACTION_MEDIA_REMOVED);//sd ���Ѿ����Ƴ�����  
//		filter.addAction(Intent.ACTION_MEDIA_EJECT);//sd ��������   || ���˸о� ACTION_MEDIA_EJECT ��   ACTION_MEDIA_UNMOUNTED��  
//		filter.addAction(Intent.ACTION_MEDIA_MOUNTED);//sd ������  
//		filter.addAction(Intent.ACTION_MEDIA_SHARED);//ѡ��ͨ�� usb ����  
		
		//һ����������ղ����㲥
//		filter.addDataScheme("file");
		
		//��̬ע��㲥
		registerReceiver(br, filter);
	}

	class mBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			LogicUtil.ToastShow("�յ���򵥵Ķ�̬ע��㲥��~");
			tvbro.setText("hello kitty!___"+intent.getScheme());
		}
	}
	
	public static class mXmlBroadcast extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			LogicUtil.ToastShow("�յ���򵥵�XML�㲥��~");
//			tvbro.setText("hello gucci!___"+intent.getScheme());
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
