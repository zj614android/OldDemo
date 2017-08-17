package com.zj.myfuncdemos;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.zj.myfuncdemos.custmerui.activity.CustmerUi_RoundStatictics_activity;
import com.zj.myfuncdemos.custmerui.activity.CustmerUi_first_activity;
import com.zj.myfuncdemos.custmerui.activity.CustmerUi_handlock_activity;
import com.zj.myfuncdemos.custmerui.activity.CustmerUi_listviewrefresh_activity;
import com.zj.myfuncdemos.custmerui.activity.CustmerUi_slidingmenu_activity;
import com.zj.myfuncdemos.custmerui.activity.PicsCheckCodeActivity;
import com.zj.myfuncdemos.custmerui.view.ImageTextTabView;
import com.zj.myfuncdemos.net.LogicUtil;
import com.zj.myfuncdemos.net.SharedPrefrenceUtil;

import zj.myfuncdemos.R;

public class Index_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_index);
		
		String read_4_setting_sharedparefrence = null;
		//sharedparefrence test
		SharedPrefrenceUtil.write_2_setting_sharedparefrence(MyDemosApplication.mContext, "gesturetime", "2016-02-14 10:10:10");
		read_4_setting_sharedparefrence = SharedPrefrenceUtil.read_4_setting_sharedparefrence(MyDemosApplication.mContext, "gesturetime");
		System.out.println("read_4_setting_sharedparefrence  1 == " + read_4_setting_sharedparefrence);
		SharedPrefrenceUtil.write_2_setting_sharedparefrence(MyDemosApplication.mContext, "gesturetime", "fuck");
		read_4_setting_sharedparefrence = SharedPrefrenceUtil.read_4_setting_sharedparefrence(MyDemosApplication.mContext, "gesturetime");
		System.out.println("read_4_setting_sharedparefrence  2 == " + read_4_setting_sharedparefrence);
		SharedPrefrenceUtil.write_2_setting_sharedparefrence(MyDemosApplication.mContext, "autologin", "y");
		read_4_setting_sharedparefrence = SharedPrefrenceUtil.read_4_setting_sharedparefrence(MyDemosApplication.mContext, "autologin");
		System.out.println("read_4_setting_sharedparefrence  3 == " + read_4_setting_sharedparefrence);
		read_4_setting_sharedparefrence = SharedPrefrenceUtil.clear_4_setting_sharedparefrence(MyDemosApplication.mContext, "autologin");
		System.out.println("read_4_setting_sharedparefrence  4 == " + read_4_setting_sharedparefrence);
		
		//����test carlendar test
		LogicUtil.getCarlendar();
		
		//String test
		String strnew = "2016-01-20 08:18:30";
		String substring_before = strnew.substring(0, 4);
		String substring_after = strnew.substring(5, strnew.length());
		String[] split2 = substring_after.split("-");
		System.out.println("�ϻ��1" + substring_before);
		System.out.println("�ϻ��2" + split2[0]);
		System.out.println("�ϻ��3" + split2[1].split(" ")[0]);

		findViewById(R.id.zdyview_calendar_date).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								CarlendarActivity.class));
					}
				});
		findViewById(R.id.popupmenu).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								popupmenuActivity.class));
					}
				});

		findViewById(R.id.pullablelistview).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								PullAbleListActivity.class));
					}
				});

		findViewById(R.id.zdyview_hacker).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								HackerActivity.class));
					}
				});

		/**
		 * talking_socket �������
		 * */
		findViewById(R.id.talking_socket).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								TalkingSocketActivity.class));
					}
				});

		/**
		 * greenDao
		 * */
		findViewById(R.id.btn_greendao).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		/**
		 * ������_ͨ���㲥��activity����ͨ��
		 * */
		findViewById(R.id.bt_2_servicebrodcast_1).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								ServiceBroTestActivity.class));
					}
				});

		/**
		 * ������_ͨ��IBinder��activity����ͨ��
		 * */
		findViewById(R.id.bt_2_servicebrodcast_2).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		/**
		 * �㲥����_�㲥����ˢ��activity�ϵĿؼ�
		 * */
		findViewById(R.id.bt_2_servicebrodcast_3).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		findViewById(R.id.zdyview_clockstep).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								ClockStepActivity.class));
					}
				});

		findViewById(R.id.updragseedetail).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								GoOnDragSeeDetail.class));
					}
				});

		/**
		 * ��֤��
		 * */
		findViewById(R.id.zdyview_checkcode).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								PicsCheckCodeActivity.class));
					}
				});

		/**
		 * ��ͼƬ��edittext
		 * */
		findViewById(R.id.zdyview_imgedittext).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								ImgEditTextActivity.class));
					}
				});

		/**
		 * ���Բ���ʵ�ֵײ�tab��
		 * */
		findViewById(R.id.mypicstextbottombar).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								MyPicsBottomBarActivity.class));
					}
				});

		// �ַ�����ȡ����
		// String version = "giliwang_android_candianzhi_xxxx_1.1.5.apk";
		// version = version.replaceAll("[\\p{Punct}\\p{Space}]+", "");
		// version = version.replaceAll("giliwangandroid", "");
		// version = version.replaceAll("apk", "");
		// System.out.println("version  == " + version);
		// System.out.println("newversion==" +
		// version.substring(version.length() - 3, version.length()));
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// String url_1 = "http://www.giliwang.com/node/notice/notice43";
		// String url_2 = "www.giliwang.com/node/notice/notice43";
		// String url_3 = "http://www.giliwang.com/sendActivityThree";
		// String url_4 = "www.giliwang.com/sendActivityThree";
		//
		// boolean checkURL_1 = LogicUtil.checkURL(url_1);
		// boolean checkURL_2 = LogicUtil.checkURL(url_2);
		// boolean checkURL_3 = LogicUtil.checkURL(url_3);
		// boolean checkURL_4 = LogicUtil.checkURL(url_4);
		//
		// System.out.println("checkURL_1 == " + checkURL_1);
		// System.out.println("checkURL_2 == " + checkURL_2);
		// System.out.println("checkURL_3 == " + checkURL_3);
		// System.out.println("checkURL_4 == " + checkURL_4);
		// }
		// }).start();
		String str = "haha:hehe";
		String[] split = str.split(":");
		System.out.println("[0]" + split[0] + " ____[1]" + split[1]);

		/**
		 * Բ����ťִ�ж���
		 * */
		findViewById(R.id.roundAnimButton).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								RoundAnimButtonActivity.class));
					}
				});

		/**
		 * Cardview
		 * */
		findViewById(R.id.cardview_btn).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								Activity_CardView.class));
					}
				});

		findViewById(R.id.btn_shape).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								ShapeActivity.class));
					}
				});

		/**
		 * �̶����ٲ���
		 * */
		findViewById(R.id.recyclerview_standard).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								RecyclerViewStandardActivity.class));
					}
				});

		/**
		 * ���Զ���
		 * */
		findViewById(R.id.animator_property).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								PropertiesAnimActivity.class));
					}
				});

		/**
		 * spinner
		 * */
		findViewById(R.id.spinner_btn).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								SpinnerActivity.class));
					}
				});

		/**
		 * ��ʽ����
		 * */
		findViewById(R.id.flow_layout).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								FlowLayoutActivity.class));
					}
				});

		/**
		 * recycleview_listview
		 * */
		findViewById(R.id.recyclerview).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Index_Activity.this,
								RecyclerViewActivity.class));
					}
				});

		/**
		 * ����footer��header
		 * */
		findViewById(R.id.footerandheader).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(Index_Activity.this,
								FooterAndHeaderGoneActivity.class));
					}
				});

		findViewById(R.id.frag_viewpager_pager_adapter).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								Fragment_by_fragmentpageradapter.class);
						startActivity(intent);
					}
				});

		/**
		 * viewpager_1_base
		 * */
		findViewById(R.id.viewpagers).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								ViewPagersActivity.class);
						startActivity(intent);
					}
				});

		/**
		 * ����һ��֪ͨ
		 **/
		findViewById(R.id.notify).setOnClickListener(
				new View.OnClickListener() {

					private NotificationManager manager;
					private Notification.Builder builder;

					@Override
					public void onClick(View arg0) {
						manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
						builder = new Notification.Builder(Index_Activity.this);

						builder.setTicker("�ж�������");
						builder.setSmallIcon(R.drawable.ic_launcher);
						builder.setContentTitle("������ʱ����");
						builder.setContentText("�ϵط���!!");

						builder.setDefaults(Notification.DEFAULT_SOUND
								| Notification.DEFAULT_VIBRATE);

						Intent intent = new Intent(Index_Activity.this,
								Index_Activity.class);
						PendingIntent pendingIntent = PendingIntent
								.getActivity(Index_Activity.this, 1, intent,
										PendingIntent.FLAG_ONE_SHOT);
						builder.setContentIntent(pendingIntent);
						manager.notify((int) System.currentTimeMillis(),
								builder.build());

					}
				});

		/**
		 * Բ�ΰٷֱ�
		 * */
		findViewById(R.id.zdyview_circle).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// Բ�ΰٷֱ�
						Intent intent = new Intent(Index_Activity.this,
								CustmerUi_RoundStatictics_activity.class);
						startActivity(intent);

					}
				});

		/**
		 * �໬
		 * */
		findViewById(R.id.slidingmenu).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// Բ�ΰٷֱ�
						Intent intent = new Intent(Index_Activity.this,
								CustmerUi_slidingmenu_activity.class);
						startActivity(intent);

					}
				});

		/**
		 * newapi_swipeRefreshLayout
		 * */
		findViewById(R.id.newapi_swipeRefreshLayout).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								SwipeRefActivity.class);
						startActivity(intent);
					}
				});

		/**
		 * webview
		 * */
		findViewById(R.id.bt_2_webview).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								WebViewActivity.class);
						startActivity(intent);
					}
				});

		/**
		 * Htmlformat
		 * */
		findViewById(R.id.bt_2_htmlformat).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								HtmlFormatActivity.class);
						startActivity(intent);
					}
				});

		/**
		 * volley
		 * */
		findViewById(R.id.bt_2_volley).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								NetReq.class);
						startActivity(intent);
					}
				});

		/**
		 * �Զ���VIEW handlock
		 * */
		findViewById(R.id.zdyview_handlock).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								CustmerUi_handlock_activity.class);
						startActivity(intent);
					}

				});

		/**
		 * �Զ���View ����
		 * */
		findViewById(R.id.zdyview_indoor).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(Index_Activity.this,
								CustmerUi_first_activity.class);
						startActivity(intent);
					}
				});

		/**
		 * fragment_1_����fragment transation
		 * */
		findViewById(R.id.fragment_1).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(Index_Activity.this,
								"fragment by transation ~~~", 0).show();
						Intent intent = new Intent(Index_Activity.this,
								Fragment_by_fragMngReplace.class);
						startActivity(intent);
					}
				});

		/**
		 * fragment_2_���� viewpager
		 * */
		findViewById(R.id.fragment_2).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(Index_Activity.this,
								"fragment by viewpager ~~~", 0).show();
						Intent intent = new Intent(Index_Activity.this,
								Fragment_by_viewpager.class);
						startActivity(intent);
					}
				});

		/**
		 * listView����ˢ��
		 * */
		findViewById(R.id.zdyview_lvrefresh).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(Index_Activity.this,
								CustmerUi_listviewrefresh_activity.class);
						startActivity(intent);
					}
				});

	}

	/**
	 * ��2���˳�����
	 * */
	long firstTime;

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long secondTime = System.currentTimeMillis();

			if (secondTime - firstTime > 800) {// ������ΰ���ʱ��������800���룬���˳�
				Toast.makeText(Index_Activity.this, "�ٰ�һ���˳�����...",
						Toast.LENGTH_SHORT).show();
				firstTime = secondTime;// ����firstTime
				return true;
			} else {
				System.exit(0);// �����˳�����
			}
		}
		return super.onKeyUp(keyCode, event);
	}

}
