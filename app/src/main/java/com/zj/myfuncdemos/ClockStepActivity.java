package com.zj.myfuncdemos;

import java.util.Timer;
import java.util.TimerTask;

import com.zj.myfuncdemos.custmerui.view.CheckingButton;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import zj.myfuncdemos.R;

public class ClockStepActivity extends BaseActivity {

	private CheckingButton getcheckcode = null;
	private Timer timer;
	int i = 60;
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			int w = (int)msg.what;
			getcheckcode.setText(w+"");
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clockstepactivity);
		getcheckcode = (CheckingButton) findViewById(R.id.getcheckcodebutton);
		
		
		timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				handler.sendEmptyMessage(i--);
				if(i == 0){
					timer.cancel();
				}
					
			}
		};
		timer.schedule(timerTask, 1000,1000);

	}

}
