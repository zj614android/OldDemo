package com.zj.myfuncdemos.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import zj.myfuncdemos.R;

public class Fragment_title extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_title, container);
		Button button = (Button) view.findViewById(R.id.bt_frag);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "title Button �������~~", 0).show();
			}
		});

		return view;
	}

}
