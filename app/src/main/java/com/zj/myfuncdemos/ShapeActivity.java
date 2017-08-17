package com.zj.myfuncdemos;

import android.os.Bundle;

import zj.myfuncdemos.R;

/**
 * shape ����Ϊ����״
 * 
 * shape�����趨��״��������selector��layout������ʹ�ã���6���ӱ�ǩ�����£�Բ -����-���-��С-���-���>
 * 
 * ʵ�ֲ���: ��1����res/drawable�¶���һ��xml�ļ����ڶ���shape��
 * ��2���ڴ����л�����xml�ļ�����������ļ��Ϳ��Կ���Ч���ˡ����濪ʼ���ܾ����ÿһ�������ʵ�ֵģ�
 * 
 * <corners 
 * android:topRightRadius="20dp" ���Ͻ� 
 * android:bottomLeftRadius="20dp" ���½�  **
 * android:topLeftRadius="1dp" ���Ͻ� 
 * android:bottomRightRadius="0dp" ���½� 
 * />
 * 
 *shape����״��Ĭ��Ϊ���Σ���������Ϊ���Σ�rectangle������Բ��(oval)��������״(line)������(ring)  
 * 
 * */

public class ShapeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shape_activity);
	}

}
