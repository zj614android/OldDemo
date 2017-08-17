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
	 * 请求字符串
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
	 * 请求图片
	 * */
//	ImageRequest的构造函数能接收六个参数，
//	第一个参数就是图片的URL地址。
//	第二个参数是图片请求成功的回调， 
//	这里我们可以把返回的Bitmap参数设置到ImageView中。
//	第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
//	第五个参数用于指定图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，
//	而 RGB_565则表示每个图片像素占据2个字节大小。第六个参数是图片请求失败的回调，这里我们可以在请求失败时在ImageView中显示一张默认图片。
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
