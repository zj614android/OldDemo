package com.zj.myfuncdemos.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.zj.myfuncdemos.net.LogicUtil.postRequestCallBack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;

public class PostByVolley {

	/**
	 * �����ַ���
	 * */
	public static void postString(RequestQueue mQueue, String Url,
			final postRequestCallBack callback) {

		StringRequest stringRequest = new StringRequest(Url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String result) {
						callback.onSuccess(200, result, "callback.onSuccess");
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
						callback.onFail(error.networkResponse.statusCode,error.getMessage());
					}
				});
		
		mQueue.add(stringRequest);
	}
	
	/**
	 * ����ͼƬ
	 * */
//	ImageRequest�Ĺ��캯���ܽ�������������
//	��һ����������ͼƬ��URL��ַ��
//	�ڶ���������ͼƬ����ɹ��Ļص��� 
//	�������ǿ��԰ѷ��ص�Bitmap�������õ�ImageView�С�
//	�������ĸ������ֱ�����ָ������ͼƬ���Ŀ�Ⱥ͸߶ȣ����ָ��������ͼƬ�Ŀ�Ȼ�߶ȴ�����������ֵ������ͼƬ����ѹ����ָ����0�Ļ��ͱ�ʾ����ͼƬ�ж�󣬶��������ѹ����
//	�������������ָ��ͼƬ����ɫ���ԣ�Bitmap.Config�µļ�������������������ʹ�ã�����ARGB_8888����չʾ��õ���ɫ���ԣ�ÿ��ͼƬ����ռ��4���ֽڵĴ�С��
//	�� RGB_565���ʾÿ��ͼƬ����ռ��2���ֽڴ�С��������������ͼƬ����ʧ�ܵĻص����������ǿ���������ʧ��ʱ��ImageView����ʾһ��Ĭ��ͼƬ��
	public static void postImage(RequestQueue mQueue, String Url,
			final postRequestCallBack callback,int maxWidth,int maxHeight,Config decodeConfig) {
		
		ImageRequest imageRequest = new ImageRequest(Url, new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bitmap_result) {
				callback.onSuccessBitmap(200, bitmap_result, "callback.onSuccess bitmap");
			}
			
		}, maxWidth, maxHeight, decodeConfig, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				callback.onFail(error.networkResponse.statusCode,
						error.getMessage());
			}
		});
		mQueue.add(imageRequest);
	}

}
