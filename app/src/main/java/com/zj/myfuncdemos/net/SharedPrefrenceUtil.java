package com.zj.myfuncdemos.net;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceUtil {

	/**
	 * д��ĳ���ֶ�
	 * */
	public static void write_2_setting_sharedparefrence(Context context,
			String key, String value) {
		// 1����Preferences������Ϊsetting�������������������򴴽��µ�Preferences

		if(null != context){
			SharedPreferences settings = context.getSharedPreferences("settings", 0);
			// 2����setting���ڱ༭״̬
			SharedPreferences.Editor editor = settings.edit();
			// 3���������
			editor.putString(key, value);
			// 4������ύ
			editor.commit();
		}

	}

	/**
	 * ��ȡĳ���ֶ�
	 * */
	public static String read_4_setting_sharedparefrence(Context context,
			String key) {
		if(null != context){
			// 1����ȡPreferences
			SharedPreferences settings = context
					.getSharedPreferences("settings", 0);
			// 2��ȡ������
			return settings.getString(key, "null");
			// ���Ͼ���Android��SharedPreferences��ʹ�÷��������д�����Preferences�ļ����λ�ÿ�����Eclipse�в鿴��
		}
		return null;

	}

	/**
	 * ���ĳ���ֶ�
	 * */
	public static String clear_4_setting_sharedparefrence(Context context,
			String key) {
		write_2_setting_sharedparefrence(context, key, "null");
		return read_4_setting_sharedparefrence(context, key);
	}

}
