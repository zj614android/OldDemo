package com.zj.myfuncdemos;

import java.util.ArrayList;
import java.util.List;

import com.zj.myfuncdemos.net.LogicUtil;
import com.zj.myfuncdemos.service.TalkingSocketService;
import com.zj.myfuncdemos.service.TalkingSocketService.recivedatacallback;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import zj.myfuncdemos.R;

public class TalkingSocketActivity extends BaseActivity {

	Handler refuiHandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			adapter.setData(datalist);
			adapter.notifyDataSetChanged();
		};
	};
	
	class lvAdapter extends BaseAdapter {
		List<dataBean> datalist;
		private ViewHolder holder;

		private void setData(List<dataBean> datalist) {
			this.datalist = datalist;
		}

		@Override
		public int getCount() {
			return datalist.size();
		}

		@Override
		public Object getItem(int position) {
			return datalist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		private class ViewHolder {
			View tb_send;
			View tb_rec;

			TextView senddate;
			TextView sendbody;

			TextView recdate;
			TextView recbody;
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				
				holder = new ViewHolder();
				convertView = View.inflate(TalkingSocketActivity.this,R.layout.bubble_listview_item, null);
				
				holder.tb_send = convertView.findViewById(R.id.send);
				holder.tb_rec = convertView.findViewById(R.id.rec);

				holder.recdate = (TextView) convertView
						.findViewById(R.id.recdate);
				holder.recbody = (TextView) convertView
						.findViewById(R.id.recbody);

				holder.senddate = (TextView) convertView
						.findViewById(R.id.senddate);
				holder.sendbody = (TextView) convertView
						.findViewById(R.id.sendbody);

				convertView.setTag(holder);
				
			} else {
				
				holder = (ViewHolder) convertView.getTag();
				
			}
			
			dataBean currDataBean = datalist.get(position);
			
			if(!TextUtils.isEmpty(currDataBean.getType()) && currDataBean.getType().equals("rec")){
				
				holder.tb_rec.setVisibility(View.VISIBLE);
				holder.tb_send.setVisibility(View.GONE);
				holder.recbody.setText(currDataBean.getData());
				holder.recdate.setText(currDataBean.getAddress());
				
			}else {
				
				holder.tb_send.setVisibility(View.VISIBLE);
				holder.tb_rec.setVisibility(View.GONE);
				holder.sendbody.setText(currDataBean.getData());
				holder.senddate.setText(currDataBean.getAddress());
				
			}
			
			return convertView;
		}

	}

	class dataBean {

		String type;
		String address;
		String data;

		public dataBean() {
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

	}

	private List<dataBean> datalist;
	private ListView lv;
	private lvAdapter adapter;
	private EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.talkingsocketlayout);
		
		edit = (EditText) findViewById(R.id.edittext_ionputkey);
		findViewById(R.id.button_talkuing_send).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bindService(new Intent(TalkingSocketActivity.this,TalkingSocketService.class), new ServiceConnection() {

					@Override
					public void onServiceDisconnected(ComponentName name) {
					}

					@Override
					public void onServiceConnected(ComponentName name, IBinder service) {
						TalkingSocketService.talkingcallback talkingcallback = (com.zj.myfuncdemos.service.TalkingSocketService.talkingcallback) service;
						 talkingcallback.send(edit.getText().toString(),new recivedatacallback() {
							
							@Override
							public void sendData(String result) {
								String[] split = result.split(":");
								String address = split[0];
								String data = split[1];
								dataBean databean = new dataBean();
								databean.setAddress(address);
								databean.setData(data);
								databean.setType("send");
								datalist.add(databean);
								refuiHandler.sendEmptyMessage(0);
							}
							
							@Override
							public void recData(String recdata) {
								
							}
						});
					
					}
				}, Context.BIND_AUTO_CREATE);				
			}
		});
		
		
		datalist = new ArrayList<dataBean>();
		
		lv = (ListView) findViewById(R.id.talkingcontent);
		
		adapter = new lvAdapter();
		adapter.setData(datalist);
		lv.setAdapter(adapter);
		
		bindService(new Intent(TalkingSocketActivity.this,
				TalkingSocketService.class), new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				TalkingSocketService.talkingcallback talkingcallback = (com.zj.myfuncdemos.service.TalkingSocketService.talkingcallback) service;
				talkingcallback.recive(new recivedatacallback() {

					@Override
					public void recData(String recdata) {
						String[] split = recdata.split(":");
						String address = split[0];
						
						if(address.equals(LogicUtil.getWifiIP()))
							return;
						
						String data = split[1];
						dataBean databean = new dataBean();
						databean.setAddress(address);
						databean.setData(data);
						databean.setType("rec");
						datalist.add(databean);
						refuiHandler.sendEmptyMessage(0);
					}

					@Override
					public void sendData(String result) {
						
					}
				});

			}
		}, Context.BIND_AUTO_CREATE);

	}

}
