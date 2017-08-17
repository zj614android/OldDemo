package com.zj.myfuncdemos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import zj.myfuncdemos.R;

public class NetReq extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_netreq);
		
		volley_4_img();
		volley_4_str();

	}
	

	// ǰ���Ѿ�˵����Volley���÷��ǳ��򵥣���ô���Ǿʹ��������HTTPͨ�ſ�ʼѧϰ�ɣ�������һ��HTTP����Ȼ�����HTTP��Ӧ��
	// ������Ҫ��ȡ��һ��RequestQueue���󣬿��Ե������·�����ȡ����
	// ע�������õ���RequestQueue��һ��������ж��������Ի������е�HTTP����Ȼ����һ�����㷨�����ط�����Щ����
	// RequestQueue�ڲ�����ƾ��Ƿǳ����ʸ߲����ģ�������ǲ���Ϊÿһ��HTTP���󶼴���һ��RequestQueue����
	// ���Ƿǳ��˷���Դ�ģ���������ÿһ����Ҫ�����罻����Activity�д���һ��RequestQueue������㹻�ˡ�
	private void volley_4_img() {
		Button ac1_bt_img = (Button) findViewById(R.id.ac1_bt_img);
		/*��*/final ImageView img = (ImageView) findViewById(R.id.ac1_reqImg);
		/*��*/final RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
		/*��*/ //������Ϊ��Ҫ����һ��HTTP�������ǻ���Ҫ����һ��StringRequest����������ʾ��
		String img_url = "http://c.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=319149e866d9f2d3341c2cbdc885e176/9c16fdfaaf51f3defeeacf6d90eef01f3a29795a.jpg";
		final ImageRequest imgRequest=new ImageRequest(img_url,new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bm) {
				img.setImageBitmap(bm);
			}}, 300, 200, Config.ARGB_8888, new ErrorListener(){
			   @Override
			   public void onErrorResponse(VolleyError arg0) {
			        
			   }            
			});
		
		ac1_bt_img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mQueue.add(imgRequest);
			}
		});
	}
	
	private void volley_4_str() {
		
	}

}
