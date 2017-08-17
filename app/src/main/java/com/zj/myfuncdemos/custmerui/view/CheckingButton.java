package com.zj.myfuncdemos.custmerui.view;

import zj.myfuncdemos.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/1/20.
 */
public class CheckingButton extends Button{

    public CheckingButton(Context context) {
        this(context, null);
    }

    public CheckingButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.checkingbuttonlayout,null);
    }
}
