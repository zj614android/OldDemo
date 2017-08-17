//package com.giliwang.android.view;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.giliwang.android.R;
//
//public class SimpleTextViewLineItem extends RelativeLayout {
//
//    private TextView bottomlinetext_left;
//    private TextView bottomlinetext_right;
//    private View bottomline;
//
//    public SimpleTextViewLineItem(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        init(context);
//    }
//
//    public SimpleTextViewLineItem(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public SimpleTextViewLineItem(Context context) {
//        this(context, null);
//    }
//
//    private void init(Context context) {
//        View inflate = View.inflate(context, R.layout.bottomlinetextlayout, this);
//        bottomlinetext_left = (TextView) inflate.findViewById(R.id.bottomlinetext_left);
//        bottomlinetext_right = (TextView) inflate.findViewById(R.id.bottomlinetext_right);
//        bottomline = inflate.findViewById(R.id.bottom_line);
//    }
//
//    public TextView getBottomlinetext_left() {
//        return bottomlinetext_left;
//    }
//
//    public void setBottomlinetext_left(TextView bottomlinetext_left) {
//        this.bottomlinetext_left = bottomlinetext_left;
//    }
//
//    public TextView getBottomlinetext_right() {
//        return bottomlinetext_right;
//    }
//
//    public void setBottomlinetext_right(TextView bottomlinetext_right) {
//        this.bottomlinetext_right = bottomlinetext_right;
//    }
//
//    public TextView getLeftTextView() {
//        return bottomlinetext_left;
//    }
//
//    public TextView getRightTextView() {
//        return bottomlinetext_right;
//    }
//
//    public View getBottomLine() {
//        return bottomline;
//    }
//
//
//    public View getLine() {
//        return null;
//    }
//}
