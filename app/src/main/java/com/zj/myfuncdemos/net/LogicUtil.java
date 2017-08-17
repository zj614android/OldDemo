package com.zj.myfuncdemos.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import com.zj.myfuncdemos.MyDemosApplication;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

/**
 * 全局工具类 内含一些回调接口的定义
 * */
public class LogicUtil {
	
	public static void getCarlendar(){
		  int YEAR = Calendar.getInstance().get(Calendar.YEAR);
		  int DATE = Calendar.getInstance().get(Calendar.DATE);
		  int MONTH = Calendar.getInstance().get(Calendar.MONTH);
		  int AM = Calendar.getInstance().get(Calendar.AM);
		  int AM_PM = Calendar.getInstance().get(Calendar.AM_PM);
		  
		  
		  int HOUR = Calendar.getInstance().get(Calendar.HOUR);
		  int MINUTE = Calendar.getInstance().get(Calendar.MINUTE);
		  int SECOND = Calendar.getInstance().get(Calendar.SECOND);
		  int HOUR_OF_DAY = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		  
		  System.out.println("Carlendar YEAR   ==" + YEAR);
		  System.out.println("Carlendar DATE  ==" + DATE);
		  System.out.println("Carlendar MONTH  ==" + MONTH);
		  System.out.println("Carlendar AM  ==" + AM);
		  System.out.println("Carlendar AM_PM  ==" + AM_PM);
		  
		  //时间
		  System.out.println("Carlendar HOUR  ==" + HOUR);
		  System.out.println("Carlendar MINUTE  ==" + MINUTE);
		  System.out.println("Carlendar SECOND  ==" + SECOND);
		  System.out.println("Carlendar HOUR_OF_DAY  ==" + HOUR_OF_DAY);
	}
	
	
	
	// 获取wifi ip 地址
	public static String getWifiIP() {

		WifiManager wifiManager = (WifiManager) MyDemosApplication.mContext
				.getSystemService(Context.WIFI_SERVICE);
		// 判断wifi是否开启
		if (!wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();
		String ip = intToIp(ipAddress);
		return ip;
	}

	private static String intToIp(int i) {

		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}

	public static boolean checkNetWorkStatus(Context context) {
		boolean result;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected()) {
			result = true;
			Log.i("", "The net was connected");
		} else {
			result = false;
			Log.i("", "The net was bad!");
		}
		return result;
	}

	// 网络监测
	public static boolean checkURL(String url) {
		boolean value = false;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();
			int code = conn.getResponseCode();
			System.out.println(">>>>>>>>>>>>>>>> " + code
					+ " <<<<<<<<<<<<<<<<<<");
			if (code != 200) {
				value = false;
			} else {
				value = true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * dp 转 px
	 * */
	public static int TypeValue_Dp_To_Px(int mDip, Context context) {
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				mDip, context.getResources().getDisplayMetrics());
		return px;
	}

	/**
	 * 顶部栏的回调接口
	 * */
	public interface TitlebarUiClickCallBack {

		/**
		 * 顶部条的左边点击事件
		 * */
		public void titlebar_left_clicked();

		/**
		 * 顶部条的右边点击事件
		 * */
		public void titlebar_right_clicked();
	}

	/**
	 * 网络请求的回调接口
	 * */
	public interface postRequestCallBack {
		void onSuccess(int responceCode, String result, String responceMessage);

		void onSuccessBitmap(int responceCode, Bitmap result,
				String responceMessage);

		void onFail(int responceCode, String responceMessage);

		void onDisconnect();
	}

	/**
	 * toast
	 * */
	public static void toast(String string, Context context) {
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}

	// /**
	// * 判断进程是否存在
	// * */
	// public static boolean isBackground(Context context) {
	// ActivityManager activityManager = (ActivityManager) context
	// .getSystemService(Context.ACTIVITY_SERVICE);
	// List<RunningAppProcessInfo> appProcesses = activityManager
	// .getRunningAppProcesses();
	// for (RunningAppProcessInfo appProcess : appProcesses) {
	// if (appProcess.processName.equals(context.getPackageName())) {
	// /*
	// BACKGROUND=400 EMPTY=500 FOREGROUND=100
	// GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
	// */
	// Log.i(context.getPackageName(), "此appimportace ="
	// + appProcess.importance
	// + ",context.getClass().getName()="
	// + context.getClass().getName());
	// if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
	// {
	// Log.i(context.getPackageName(), "处于后台"
	// + appProcess.processName);
	// return true;
	// } else {
	// Log.i(context.getPackageName(), "处于前台"
	// + appProcess.processName);
	// return false;
	// }
	// }
	// }
	//
	// return false;
	// }

	/**
	 * 返回app运行状态 1:程序在前台运行 2:程序在后台运行 3:程序未启动 注意：需要配置权限<uses-permission
	 * android:name="android.permission.GET_TASKS" />
	 */
	public static int getAppSatus(Context context, String pageName) {

		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

		// 判断程序是否在栈顶
		if (list.get(0).topActivity.getPackageName().equals(pageName)) {
			System.out.println("费尔斯通 __ 程序在前台运行 ");
			return 1;
		} else {
			// 判断程序是否在栈里
			System.out.println("费尔斯通 __ 程序在后台运行 ");
			for (ActivityManager.RunningTaskInfo info : list) {
				if (info.topActivity.getPackageName().equals(pageName)) {
					return 2;
				}
			}
			System.out.println("费尔斯通 __ 程序未启动 ");
			return 3;// 栈里找不到，返回3
		}
	}

	public static int TypeValue_Sp_To_Px(int mDip, Context context) {
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				mDip, context.getResources().getDisplayMetrics());
		return px;
	}

	/**
	 * 解除重复
	 * */
	private static Toast toastinfo = null;

	public static void ToastShow(String text) {
		if (toastinfo == null) {
			toastinfo = Toast.makeText(MyDemosApplication.mContext, text,
					Toast.LENGTH_SHORT);
		} else {
			toastinfo.setText(text);
		}
		toastinfo.show();
	}

}
