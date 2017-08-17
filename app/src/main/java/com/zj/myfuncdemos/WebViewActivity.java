package com.zj.myfuncdemos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import zj.myfuncdemos.R;

public class WebViewActivity extends Activity {

	private WebView wbview = null;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		wbview  = (WebView) findViewById(R.id.wbv);
		WebSettings settings = wbview.getSettings();
		settings.setJavaScriptEnabled(true);//js����
		settings.setUseWideViewPort(true);//���ô����ԣ��������������
		settings.setLoadWithOverviewMode(true);//����
		settings.setBuiltInZoomControls(true);//����
		settings.setSupportZoom(true);//����
		settings.setDisplayZoomControls(false); //�����������
//		wbview.loadUrl("http://www.giliwang.com/node/notice/notice43");
//		wbview.loadUrl("http://www.giliwang.com/sendActivityThree");
		wbview.loadUrl("http://www.baidu.com/");
	}
	
}
