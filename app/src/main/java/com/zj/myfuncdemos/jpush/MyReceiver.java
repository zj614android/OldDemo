package com.zj.myfuncdemos.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.zj.myfuncdemos.Index_Activity;
import com.zj.myfuncdemos.MyDemosApplication;
import com.zj.myfuncdemos.NetReq;
import zj.myfuncdemos.R;
import com.zj.myfuncdemos.net.LogicUtil;

import java.util.Iterator;


/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";
	private static String currInnerText = null;
	static String type = null;
	static String params = null;

	@Override
	public void onReceive(Context context, Intent intent) {
//		Bundle bundle = intent.getExtras();
//		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction()
//				+ ", extras: " + printBundle(bundle));
//
//		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//			String regId = bundle
//					.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//			Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
//			// send the Registration Id to your server...
//
//		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
//				.getAction())) {
//
//			Log.d(TAG,"[MyReceiver] 接收到推送下来的自定义消息: "
//							+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
//			// processCustomMessage(context, bundle);
//
//			//
//			// if (appSatus == 3) {
//			// Intent mIntent = new Intent();
//			// mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			// ComponentName comp = new ComponentName("com.zj.myfuncdemos",
//			// "com.zj.myfuncdemos.NetReq");
//			// mIntent.setComponent(comp);
//			// MyDemosApplication.mContext.startActivity(mIntent);
//			// } else {
//			//
//			// }
//
//			currInnerText = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//
//			NotificationManager manager = (NotificationManager) MyDemosApplication.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//			Notification.Builder builder = new Notification.Builder(MyDemosApplication.mContext);
//			builder.setTicker("您收到了一条即利网通知");
//			builder.setSmallIcon(R.drawable.ic_launcher);
//			builder.setContentTitle("即利网");
//
//			// builder.setContentText("[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//
//			if (!TextUtils.isEmpty(currInnerText)) {
//				builder.setContentText(currInnerText);
//			} else {
//				builder.setContentText(null);
//			}
//
//			builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
//			Intent intent2 = new Intent(MyDemosApplication.mContext, Index_Activity.class);
//
//			// 通知的点击事件
////			int appSatus = LogicUtil.getAppSatus(MyDemosApplication.mContext,"com.zj.myfuncdemos");
//			Intent mIntent = new Intent();
////			if (appSatus == 3) {
////				// mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////				// ComponentName comp = new
////				// ComponentName("com.zj.myfuncdemos","com.zj.myfuncdemos.NetReq");
////				// mIntent.setComponent(comp);
////				// MyDemosApplication.mContext.startActivity(mIntent);
////				mIntent.setClass(MyDemosApplication.mContext, NetReq.class);
////			} else {
////				mIntent.setClass(MyDemosApplication.mContext, NetReq.class);
////			}
//
//
//
//			PendingIntent pendingIntent = PendingIntent.getActivity(
//					MyDemosApplication.mContext, 1, mIntent,
//					PendingIntent.FLAG_ONE_SHOT);
//			builder.setContentIntent(pendingIntent);
//			manager.notify((int) System.currentTimeMillis(), builder.build());
//
//		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
//				.getAction())) {
//
//			Log.d(TAG,
//					"[MyReceiver] 接收到推送下来的通知   JPushInterface.EXTRA_MESSAGE :"
//							+ JPushInterface.EXTRA_MESSAGE);
//			int notifactionId = bundle
//					.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//			Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//
//		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
//				.getAction())) {
//			Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
//
//			// 打开自定义的Activity
//			Intent i = new Intent(context, TestActivity.class);
//			i.putExtras(bundle);
//			// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//					| Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			context.startActivity(i);
//
//		} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
//				.getAction())) {
//			Log.d(TAG,
//					"[MyReceiver] 用户收到到RICH PUSH CALLBACK: "
//							+ bundle.getString(JPushInterface.EXTRA_EXTRA));
//			// 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，
//			// 打开一个网页等..
//		} else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
//				.getAction())) {
//			boolean connected = intent.getBooleanExtra(
//					JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//			Log.w(TAG, "[MyReceiver]" + intent.getAction()
//					+ " connected state change to " + connected);
//		} else {
//			Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
//		}
//	}
//
//	// 打印所有的 intent extra 数据
//	private static String printBundle(Bundle bundle) {
//		StringBuilder sb = new StringBuilder();
//		for (String key : bundle.keySet()) {
//			// O啦 获取到推送的信息内容了
//			// currInnerText = null;
//			// if (key.equals("cn.jpush.android.ALERT")) {
//			// // System.out.println("笑而不语的青春期 ：" + "key:" + key + ", value:" +
//			// // bundle.getString(key));
//			// currInnerText = bundle.getString(key);
//			// }
//
//			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
//				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
//			} else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
//				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
//			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
//				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
//					Log.i(TAG, "This message has no Extra data");
//					continue;
//				}
//
//				try {
//					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
//					System.out.println("json result == "+ bundle.getString(JPushInterface.EXTRA_EXTRA));
//
//					Iterator<String> it = json.keys();
//					while (it.hasNext()) {
//						String myKey = it.next().toString();
//						if(myKey.equals("公告") || myKey.contains("公告")){
//							type = myKey;
//							params = json.optString(myKey);
//							System.out.println("	type = " + type + "params = " + params);
//						}
//						sb.append("\nkey:" + key + ", value: [" + myKey + " - "+ json.optString(myKey) + "]");
//					}
//
//				} catch (JSONException e) {
//					Log.e(TAG, "Get message extra JSON error!");
//				}
//
//			} else {
//				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
//			}
//		}
//
//		return sb.toString();
	}
}
