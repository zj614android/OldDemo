package com.zj.myfuncdemos;

import android.app.Application;
import android.content.Context;

public class MyDemosApplication extends Application {

	public static Context mContext = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mContext = getApplicationContext();
		
//		JPushInterface.setDebugMode(true);
//	    JPushInterface.init(this);
	}
	
}
