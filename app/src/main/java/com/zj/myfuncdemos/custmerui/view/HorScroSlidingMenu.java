package com.zj.myfuncdemos.custmerui.view;

import com.nineoldandroids.view.ViewHelper;
import zj.myfuncdemos.R;
import com.zj.myfuncdemos.net.LogicUtil;
import com.zj.myfuncdemos.util.ScreenUtils;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;

/**
 * ʹ�÷�����
 * ��ֱ��copy�ൽָ��Ŀ¼
 * �����Զ��������ж���������ԣ�attr����attrs�ﶨ�嶼�У������е�����������ʼ����ʱ���ȥ��������Զ������� == R.styleable.HorScroSlidingMenu_rightpadding:
 * �����ڴ������õ������Զ����������ǵ�sdk�汾������������Ҫ����һ��jar�� == nineoldandroids-2.4.0.jar
 * �����ڴ������õ���leftmenu��rightcontent���� child.findchildat(0),child.findchildat(1),�����ڲ��ֵ�ʹ����Ҫע��ṹ���⡣��Ȼ�ᱨ��ָ�롣
 * ���滻�������õ��ı���ͼƬ���������õ�activity�����ý���ʲô��ʼ��
 * 
 * scrollTo �� smoothcrollto  == scrollview�еľ�̬�붯̬����
 * */
public class HorScroSlidingMenu extends HorizontalScrollView implements
		OnTouchListener {

	private int mMenuPadding = 0;
	private int screenWidth = 0;
	private ViewGroup mGroup_left_menu;
	private ViewGroup mGroup_right_content;
	private ViewGroup mGroup_wrapper;
	private Context mContext = null;
	private boolean once = false;
	private int mMenuWidth = 0;

	public HorScroSlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		init(attrs, defStyle);
	}

	public HorScroSlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HorScroSlidingMenu(Context context) {
		this(context, null);
	}

	/**
	 * ��ʼ��
	 * */
	private void init(AttributeSet attrs, int defStyle) {
		screenWidth = ScreenUtils.getScreenWidth(mContext);

		// ��������ֵ
		TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.HorScroSlidingMenu, defStyle, 0);
		int typedArrayindexCount = typedArray.getIndexCount();
		for (int i = 0; i < typedArrayindexCount; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.HorScroSlidingMenu_rightpadding:
				mMenuPadding = typedArray.getDimensionPixelSize(attr,
						LogicUtil.TypeValue_Dp_To_Px(50, mContext));// attr��ûֵ�������
																	// ��
																	// �и�Ĭ��ֵ
				break;
			default:
				break;
			}
		}

		typedArray.recycle();// �����ͷŽ�������ֵ

		setOnTouchListener(this);
	}

	/**
	 * ������view�Ŀ�͸�(view) �����Լ��Ŀ�͸�(viewgroup)
	 * */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (!once) {
			mGroup_wrapper = (ViewGroup) getChildAt(0);// ���������Բ���
			mGroup_left_menu = (ViewGroup) mGroup_wrapper.getChildAt(0);
			mGroup_right_content = (ViewGroup) mGroup_wrapper.getChildAt(1);

			// ������߻����˵��Ŀ�
			mMenuWidth = mGroup_left_menu.getLayoutParams().width = screenWidth - mMenuPadding;

			// this.scrollTo(mMenuWidth, 0);// ��ʼ��ʱ����������������λ��

			// �����ұ߻����˵��Ŀ�
			mGroup_right_content.getLayoutParams().width = screenWidth;
			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			int scrollX = this.getScrollX();
			if (scrollX >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
			} else {
				this.smoothScrollTo(0, 0);
			}
			return true;
		}
		return super.onTouchEvent(event);// ��һ�в���ʡ �� ��Ϊ��ֻ��up��ʱ����return true // ��move��down �͵õ�Ĭ�ϵ�super
	
	}

	/**
	 * �򿪲˵�
	 * */
	public void openMenu() {
		int scrollX = this.getScrollX();
		if (scrollX < mMenuWidth / 2) {
			this.smoothScrollTo(mMenuWidth, 0);
		} else {
			this.smoothScrollTo(0, 0);
		}
	}

	/**
	 * ����ʽ�໬�Ĺؼ�ʵ��
	 * */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);

		/*
		 * �� �˵� ���ѵ�
		 */
		//����ǳ���ʽ�໬�ĵ�һ��    ����㷨�� scale ��ʱû��� ����
		float scale = l * 1.0f/mMenuWidth;//L ����scrollx ������ʱ����0 ����������������leftΪmMenuWidth ��������ʼֵΪ600�� scale�ĳ�ʼֵҲ��ȻΪ1
//		ViewHelper.setTranslationX(mGroup_left_menu, mMenuWidth * scale);//λ�����Զ���
		
		//��˵�
		float leftScale = 1 - 0.3f * scale;   //0.7 - 1
		ViewHelper.setScaleX(mGroup_left_menu, leftScale);//���Ŷ���
		ViewHelper.setScaleY(mGroup_left_menu, leftScale);//���Ŷ���
		ViewHelper.setAlpha(mGroup_left_menu, 0.4f + 0.6f * (1 - scale));//͸�������Զ���  0.4 - 1
		ViewHelper.setTranslationX(mGroup_left_menu, mMenuWidth * scale* 0.7f);//����λ�����Զ���
		
		//�� ����
		float rightscale = 0.7f + 0.3f * scale; //0.7 -1
		ViewHelper.setPivotX(mGroup_right_content, 0);
		ViewHelper.setPivotY(mGroup_right_content, mGroup_right_content.getHeight()/2);
		ViewHelper.setScaleX(mGroup_right_content, rightscale);
		ViewHelper.setScaleY(mGroup_right_content, rightscale);
		
		//ƫ���������ܽ᣺
		//ViewHelper.setTranslationX(mGroup_left_menu,scale * mMenuWidth);
		//�������д������⣺
		//����ֻ����һ��setTranslationX(mGroup_left_menu, mMenuWidth),�����Ǹ���ֹ��ֵ������ȥ�����Ǹ������������ƶ�����û�䣬ֻ��scrollview�����ǳ�ʼ����ʱ�����ó�һ�����ֵ
		//��ô���ǽ�scrollview�ϳ�����ʱ�����϶�Ҫ����0ȥ�仯�����Ǿ��������ֵscale * mMenuWidth������0�ǹؼ�������һ��ʸ��
		
	}
	
	
}
