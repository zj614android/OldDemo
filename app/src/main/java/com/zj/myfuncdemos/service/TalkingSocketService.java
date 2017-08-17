package com.zj.myfuncdemos.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zj.myfuncdemos.net.LogicUtil;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

public class TalkingSocketService extends Service {

	public static final String MULTICAST_ADDRESS = "224.0.0.1";
	public static final int MULTICAST_PORT = 14089;
	boolean isHasInitFinished = false;
	private MulticastSocket multicastSocket_send;
	private MulticastSocket multicastSocket_recive;
	private sendThread sendRunnable;

	class SocketBinder extends Binder implements talkingcallback {

		@Override
		public String send(final String info, final recivedatacallback callback) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						MulticastSocket multicastSocket = new MulticastSocket();
						InetAddress address = InetAddress
								.getByName("224.0.0.1"); // 必须使用D类地址
						multicastSocket.joinGroup(address);
						String datas = LogicUtil.getWifiIP() + ":" + info;
						byte[] buf = datas.getBytes();
						DatagramPacket datagramPacket = new DatagramPacket(buf,
								buf.length);
						datagramPacket.setAddress(address); // 接收地址和group的标识相同
						datagramPacket.setPort(MULTICAST_PORT); // 发送至的端口号
						multicastSocket.send(datagramPacket);
						callback.sendData(datas);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			return null;
		}

		@Override
		public void recive(final recivedatacallback recinterface) {
			// reciveInfos(recinterface);

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						MulticastSocket multicastSocket = new MulticastSocket(
								MULTICAST_PORT);
						InetAddress address = InetAddress
								.getByName("224.0.0.1");
						multicastSocket.joinGroup(address);
						byte[] buf = new byte[1024];

						while (true) {
							DatagramPacket datagramPacket = new DatagramPacket(
									buf, buf.length);
							multicastSocket.receive(datagramPacket); // 接收数据，同样会进入阻塞状态

							byte[] message = new byte[datagramPacket
									.getLength()]; // 从buffer中截取收到的数据
							message = datagramPacket.getData();
							
							String addresses = datagramPacket.getAddress().getHostAddress();
							String data = new String(message, 0, message.length);
//							data = addresses + ":" + data;
							recinterface.recData(data);
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		try {
			initSocketJoinTheGroup();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new SocketBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();

		try {
			initSocketJoinTheGroup();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// 初始化socket
		try {
			initSocketJoinTheGroup();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.onStartCommand(intent, flags, startId);
	}

	private void initSocketJoinTheGroup() throws IOException {

		InetAddress address = InetAddress.getByName("224.0.0.1"); // 必须使用D类地址

		if (multicastSocket_send == null) {
			multicastSocket_send = new MulticastSocket();
			multicastSocket_send.joinGroup(address);
		}

		if (multicastSocket_recive == null) {
			multicastSocket_recive = new MulticastSocket();
			multicastSocket_recive.joinGroup(address);
		}
	}

	@Override
	public void onDestroy() {
		multicastSocket_recive.close();
		multicastSocket_recive = null;

		multicastSocket_send.close();
		multicastSocket_send = null;

		super.onDestroy();
	}

	class sendThread implements Runnable {
		String info;

		public sendThread(String info) {
			this.info = info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		@Override
		public void run() {

		}

	}

	/**
	 * 发送一个信息
	 * */
	public String sendInfos(final String info) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				DatagramPacket datagramPacket = null;
				try {
					byte[] senddatabuff = info.getBytes();
					datagramPacket = new DatagramPacket(senddatabuff, 0,
							senddatabuff.length,
							InetAddress.getByName(MULTICAST_ADDRESS),
							MULTICAST_PORT);
					multicastSocket_send.send(datagramPacket);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		return LogicUtil.getWifiIP() + ":" + info;
	}

	/**
	 * 收取一个信息
	 * */
	public void reciveInfos(final recivedatacallback recinterface) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						byte[] data = new byte[1024];
						DatagramPacket recivedatagramPacket = new DatagramPacket(
								data, 0, data.length);

						multicastSocket_recive.receive(recivedatagramPacket);
						recinterface.recData(new String(recivedatagramPacket.getData(), 0,
										recivedatagramPacket.getData().length));


					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public interface talkingcallback {
		String send(String info, recivedatacallback callback);

		void recive(recivedatacallback recinterface);
	}

	public interface recivedatacallback {
		void recData(String recdata);

		void sendData(String result);
	}

}
