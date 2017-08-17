package com.zj.myfuncdemos;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import zj.myfuncdemos.R;

public class popupmenuActivity extends BaseActivity {
	
	private Button bt_popupmenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popupmenu_layout);
		bt_popupmenu = (Button) findViewById(R.id.bt_popupmenu);
		bt_popupmenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					PopupMenu pop = new PopupMenu(popupmenuActivity.this, v);  
		            pop.getMenuInflater().inflate(R.menu.main, pop.getMenu()); 
		            pop.show();  
			}
		});
	}
	
}
