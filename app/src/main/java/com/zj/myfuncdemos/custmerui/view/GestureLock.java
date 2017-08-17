package com.zj.myfuncdemos.custmerui.view;

import java.util.ArrayList;

import com.zj.myfuncdemos.MyDemosApplication;
import zj.myfuncdemos.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * ʹ��ע����� 1������activity�����ﶨ��ÿؼ�ʱ����ҪsetclickableΪtrue
 * 2������Ҫ��У�������ʱ����Ҫ�ڸ���activity�����ж���һ��textview
 * ��Ȼ��ʵ��gesturelockcallback�ӿڣ���Ȼ�󽫸���activity��Ϊһ���ص��ӿ����ø��ÿؼ�,setcallback����
 * 3:����У��ɹ����Զ��رոý��棬��Ҫ�øÿؼ����õ���setActivity���������õĲ������ǽ�����activity����ӿؼ�
 * 4:�����Ҫ�����ڸ���activity�еĻص���������������
 * */
public class GestureLock extends View implements OnTouchListener {

	// ������������
	public static final String TAG = "GestureLock";
	private ArrayList<GestureLockPointerBean> checkedlist;// У�����ʱ�õ��ļ��� ������3����Ч
															// ������Ϊ��
	private ArrayList<GestureLockPointerBean> focus_list;// ����ֵ����
	private StringBuilder gesturePass = null;
	private String str_gestrue_pass_1 = null;
	private String str_gestrue_pass_2 = null;
	private boolean delayReset = false;// �Ƿ���Ҫ�ӳ��ػ�
	private Activity fatheractivity;
	private GestureCallback callback;

	// �ÿؼ�״̬��������
	public static final int STATE_IDLE = 0;// ��ȷ״̬����ʱ��һ����ɫʵ��Բ
	public static final int STATE_RIGHT = 1;// ����״̬����ʱ��һ����ɫʵ��Բ
	public static final int STATE_WRONG = 2;// ��ͨ����״̬����ʱ������һ����ɫ����Բ

	// ��Ļ�����ʾ����
	private final int DIRECTION_VERTICAL = 1;// ��ͨ����״̬����ʱ������һ����ɫ����Բ
	private final int DIRECTION_HORIZONTAL = 2;// ��ͨ����״̬����ʱ������һ����ɫ����Բ

	// ���������ز���
	private int[][] points_sets_x;// ��¼�Ź����9����λ����
	private int[][] points_sets_y;// ��¼�Ź����9����λ����
	private int screenHeight = 0;// ��Ļ��
	private int screenWidth = 0;// ��Ļ��
	private int maxLine = 0;// ���� ����ƫ������ʱ���õ�
	private int minLine = 0;// ��С�� ����ƫ������ʱ���õ�
	private int long_centerOffset = 0;// ����ƫ���� �к������Ĳ��
	private int short_centerOffset = 0;// ����ƫ���� �к������Ĳ��
	private float touch_x = 0;// ������Ļʱ���x����
	private float touch_y = 0;// ������Ļʱ���y����
	float line_start_x = 0;
	float line_start_y = 0;
	float line_stop_x = 0;
	float line_stop_y = 0;

	// ����ͼ��ʱ�����ı���
	private Paint inner_paint;
	private Paint outer_paint;
	private Paint mid_paint;
	private int INNER_CIRCLE_R = 20;// ��ʵ��Բ�İ뾶
	private int OUTTER_CIRCLE_R = 30;// �����Բ�İ뾶
	private int reCheckCount = 5;// ����У���ظ�����

	// ����� �Ź���ĵ�
	private GestureLockPointerBean pointer_1 = null;
	private GestureLockPointerBean pointer_2 = null;
	private GestureLockPointerBean pointer_3 = null;
	private GestureLockPointerBean pointer_4 = null;
	private GestureLockPointerBean pointer_5 = null;
	private GestureLockPointerBean pointer_6 = null;
	private GestureLockPointerBean pointer_7 = null;
	private GestureLockPointerBean pointer_8 = null;
	private GestureLockPointerBean pointer_9 = null;
	private ArrayList<GestureLockPointerBean> pointers = null;
	private GestureLockPointerBean the_pointer_in_area = null;
	private GestureLockPointerBean currGestureLockPointerBean = null;

	// ����ʶ������ж���
	public static final int GESTURE_TITLE_CONTENT_TYPE_CHECK_OK = 0;
	public static final int GESTURE_TITLE_CONTENT_TYPE_CHECK_FALSE = 1;
	public static final int GESTURE_TITLE_CONTENT_TYPE_ENTER = 2;
	public static final int GESTURE_TITLE_CONTENT_TYPE_ENTER_AGAIN = 3;
	public static final int GESTURE_TITLE_CONTENT_TYPE_ENTER_TWICEERROR = 4;
	public static final int GESTURE_TITLE_CONTENT_TYPE_CHECK = 5;
	public static final int GESTURE_TITLE_CONTENT_TYPE_ENTER_SHORTERROR = 6;

	/**
	 * �ӳ��ػ�
	 * */
	private Handler delayHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			clearCavans();
			invalidate();
		};
	};
	private SharedPreferences localSharedPreferences;

	public GestureLock(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public GestureLock(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GestureLock(Context context) {
		super(context);
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		pointsSetsInit();
		drawPoints(canvas);
		drawLines(canvas);
	}

	public void setActivity(Activity activity) {
		fatheractivity = activity;
	}

	public void setCallback(GestureCallback callback) {
		this.callback = callback;
	}

	/**
	 * ����
	 * */
	private void drawLines(Canvas canvas) {

		Log.i(TAG, "focus_list.size == " + focus_list.size());

		if (focus_list != null && focus_list.size() > 0) {
			currGestureLockPointerBean = focus_list.get(focus_list.size() - 1);
			canvas.drawLine(currGestureLockPointerBean.getX(),
					currGestureLockPointerBean.getY(), touch_x, touch_y,
					inner_paint);
			Log.i(TAG, "focus_list != null && focus_list.size() > 0");
		} else {
			Log.i(TAG, "!!!!!!!focus_list != null && focus_list.size() > 0");

			if (focus_list != null) {
				Log.i(TAG,
						"focus_list != null  focus_list == "
								+ focus_list.size());
			} else {
				Log.i(TAG, "focus_list == null");
			}
		}

		if (focus_list != null && focus_list.size() > 0) {

			for (int i = 0; i < focus_list.size(); ++i) {
				if (i == 0) {
					line_start_x = focus_list.get(i).getX();
					line_start_y = focus_list.get(i).getY();
					line_stop_x = line_start_x;
					line_stop_y = line_start_y;
				} else {
					line_start_x = focus_list.get(i - 1).getX();
					line_start_y = focus_list.get(i - 1).getY();
					line_stop_x = focus_list.get(i).getX();
					line_stop_y = focus_list.get(i).getY();
				}

				canvas.drawLine(line_start_x, line_start_y, line_stop_x,
						line_stop_y, inner_paint);
			}

		}

	}

	/**
	 * ����
	 * */
	private void drawPoints(Canvas canvas) {
		for (GestureLockPointerBean point : pointers) {
			drawOnePoint(canvas, point);
		}
	}

	/**
	 * ����һ����ʱ�õ��ķ���
	 * */
	private void drawOnePoint(Canvas canvas, GestureLockPointerBean point) {

		switch (point.getState()) {
		case STATE_IDLE:
			outer_paint.setColor(Color.BLACK);
			canvas.drawCircle(point.getX(), point.getY(),TypeValue_Dp_To_Px(OUTTER_CIRCLE_R), outer_paint);
			break;
		case STATE_RIGHT:
			inner_paint.setColor(getResources().getColor(R.color.color_bule_2));
			outer_paint.setColor(getResources().getColor(R.color.color_bule_2));
			mid_paint.setColor(getResources().getColor(R.color.color_bule_2));
			mid_paint.setAlpha(10);

			canvas.drawCircle(point.getX(), point.getY(),
					TypeValue_Dp_To_Px(INNER_CIRCLE_R), inner_paint);

			canvas.drawCircle(point.getX(), point.getY(),
					TypeValue_Dp_To_Px(OUTTER_CIRCLE_R), outer_paint);

			canvas.drawCircle(point.getX(), point.getY(),
					TypeValue_Dp_To_Px(OUTTER_CIRCLE_R), mid_paint);

			break;
		case STATE_WRONG:
			inner_paint.setColor(getResources().getColor(R.color.color_red));
			outer_paint.setColor(getResources().getColor(R.color.color_red));
			mid_paint.setColor(getResources().getColor(R.color.color_red));
			mid_paint.setAlpha(10);

			canvas.drawCircle(point.getX(), point.getY(),
					TypeValue_Dp_To_Px(INNER_CIRCLE_R), inner_paint);

			canvas.drawCircle(point.getX(), point.getY(),
					TypeValue_Dp_To_Px(OUTTER_CIRCLE_R), outer_paint);

			canvas.drawCircle(point.getX(), point.getY(),
					TypeValue_Dp_To_Px(OUTTER_CIRCLE_R), mid_paint);
			break;
		default:
			outer_paint.setColor(Color.BLACK);
			canvas.drawCircle(point.getX(), point.getY(),
					TypeValue_Dp_To_Px(OUTTER_CIRCLE_R), outer_paint);
			break;
		}

	}

	/**
	 * ��Ŀ��������
	 * */
	private void pointsSetsInit() {

		// ��Ļ��߻�ȡ
		screenHeight = getHeight();
		screenWidth = getWidth();

		// ������
		if (screenHeight > screenWidth) {
			// ����
			pointsSetsClac(DIRECTION_VERTICAL);
		} else {
			// ����
			pointsSetsClac(DIRECTION_HORIZONTAL);
		}
	}

	/**
	 * ��ʼλ�õ�ƫ�������㣬�漰����������ƫ��������
	 * */
	private void pointsSetsClac(int DIRECTION) {
		switch (DIRECTION) {
		case DIRECTION_VERTICAL:
			maxLine = screenHeight;
			minLine = screenWidth;
			break;
		case DIRECTION_HORIZONTAL:
			maxLine = screenWidth;
			minLine = screenHeight;
			break;
		}
		offsetClac(maxLine, minLine, DIRECTION);
	}

	/**
	 * ƫ��������
	 * */
	private void offsetClac(int maxLine, int minLine, int DIRECTION) {
		long_centerOffset = (maxLine - minLine) / 2;
		short_centerOffset = minLine / 4;

		switch (DIRECTION) {
		case DIRECTION_VERTICAL:// ����

			// ��һ�еĵ�
			points_sets_y[0][0] = long_centerOffset + short_centerOffset;
			points_sets_x[0][0] = short_centerOffset;
			points_sets_y[0][1] = long_centerOffset + short_centerOffset;
			points_sets_x[0][1] = short_centerOffset * 2;
			points_sets_y[0][2] = long_centerOffset + short_centerOffset;
			points_sets_x[0][2] = short_centerOffset * 3;

			// �ڶ��еĵ�
			points_sets_y[1][0] = long_centerOffset + short_centerOffset * 2;
			points_sets_x[1][0] = short_centerOffset;
			points_sets_y[1][1] = long_centerOffset + short_centerOffset * 2;
			points_sets_x[1][1] = short_centerOffset * 2;
			points_sets_y[1][2] = long_centerOffset + short_centerOffset * 2;
			points_sets_x[1][2] = short_centerOffset * 3;

			// �����еĵ�
			points_sets_y[2][0] = long_centerOffset + short_centerOffset * 3;
			points_sets_x[2][0] = short_centerOffset;
			points_sets_y[2][1] = long_centerOffset + short_centerOffset * 3;
			points_sets_x[2][1] = short_centerOffset * 2;
			points_sets_y[2][2] = long_centerOffset + short_centerOffset * 3;
			points_sets_x[2][2] = short_centerOffset * 3;

			break;
		case DIRECTION_HORIZONTAL:// ����

			// ��һ�еĵ�
			points_sets_x[0][0] = long_centerOffset + short_centerOffset;
			points_sets_y[0][0] = short_centerOffset;
			points_sets_x[0][1] = long_centerOffset + short_centerOffset;
			points_sets_y[0][1] = short_centerOffset * 2;
			points_sets_x[0][2] = long_centerOffset + short_centerOffset;
			points_sets_y[0][2] = short_centerOffset * 3;

			// �ڶ��еĵ�
			points_sets_x[1][0] = long_centerOffset + short_centerOffset * 2;
			points_sets_y[1][0] = short_centerOffset;
			points_sets_x[1][1] = long_centerOffset + short_centerOffset * 2;
			points_sets_y[1][1] = short_centerOffset * 2;
			points_sets_x[1][2] = long_centerOffset + short_centerOffset * 2;
			points_sets_y[1][2] = short_centerOffset * 3;

			// �����еĵ�
			points_sets_x[2][0] = long_centerOffset + short_centerOffset * 3;
			points_sets_y[2][0] = short_centerOffset;
			points_sets_x[2][1] = long_centerOffset + short_centerOffset * 3;
			points_sets_y[2][1] = short_centerOffset * 2;
			points_sets_x[2][2] = long_centerOffset + short_centerOffset * 3;
			points_sets_y[2][2] = short_centerOffset * 3;

			break;
		}

		// 9�������ĳ�ʼ��
		if (pointers == null)
			initPointers();
	}

	/**
	 * ��ʼ���Ź����bean
	 * */
	private void initPointers() {
		pointer_1 = new GestureLockPointerBean(points_sets_x[0][0],
				points_sets_y[0][0], 1);

		pointer_2 = new GestureLockPointerBean(points_sets_x[0][1],
				points_sets_y[0][1], 2);

		pointer_3 = new GestureLockPointerBean(points_sets_x[0][2],
				points_sets_y[0][2], 3);

		pointer_4 = new GestureLockPointerBean(points_sets_x[1][0],
				points_sets_y[1][0], 4);

		pointer_5 = new GestureLockPointerBean(points_sets_x[1][1],
				points_sets_y[1][1], 5);

		pointer_6 = new GestureLockPointerBean(points_sets_x[1][2],
				points_sets_y[1][2], 6);

		pointer_7 = new GestureLockPointerBean(points_sets_x[2][0],
				points_sets_y[2][0], 7);

		pointer_8 = new GestureLockPointerBean(points_sets_x[2][1],
				points_sets_y[2][1], 8);

		pointer_9 = new GestureLockPointerBean(points_sets_x[2][2],
				points_sets_y[2][2], 9);

		pointers = new ArrayList<GestureLockPointerBean>();
		pointers.add(pointer_1);
		pointers.add(pointer_2);
		pointers.add(pointer_3);
		pointers.add(pointer_4);
		pointers.add(pointer_5);
		pointers.add(pointer_6);
		pointers.add(pointer_7);
		pointers.add(pointer_8);
		pointers.add(pointer_9);

	}

	/**
	 * ��ʼ��
	 * */
	private void init() {
		inner_paint = new Paint();
		inner_paint.setStrokeWidth(5);

		outer_paint = new Paint();
		outer_paint.setColor(Color.BLACK);
		outer_paint.setStrokeWidth(5);
		outer_paint.setStyle(Style.STROKE);

		mid_paint = new Paint();
		mid_paint.setColor(Color.BLUE);
		mid_paint.setAlpha(50);
		mid_paint.setStyle(Style.FILL);

		points_sets_x = new int[3][3];
		points_sets_y = new int[3][3];

		setOnTouchListener(this);
		checkedlist = new ArrayList<GestureLockPointerBean>();

		gesturePass = new StringBuilder();
		focus_list = new ArrayList<GestureLockPointerBean>();

		// ���������ڼ�¼��������ı���sharedprefrence
		initSharedPrefrence();

	}

	/**
	 * �����¼�
	 * */
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ���µ�ʱ�����Ƿ���9����׼�������غ�
			updatePointsDraw(event);
			break;
		case MotionEvent.ACTION_UP:
			// ̧����ָ��ʱ����С�У�顿����ˢ�½���
			checkout();
			clearData();
			clearCavans();
			break;
		case MotionEvent.ACTION_MOVE:
			// �ƶ���ʱ�򡾼�⵱ǰ����xy�����Ƿ���ԲȦ��Χ�ڡ�
			// ����֮ǰ��������·��event.getxд����getx�����Ĵ��⣬�˷��˺ܾ�ʱ��ŷ���
			updatePointsDraw(event);
			break;
		}
		invalidate();
		return false;
	}

	/**
	 * ����̧���ʱ�����У��
	 * */
	private void checkout() {
		// ������ݿ����Ƿ������������ Ĭ������������sharedprefrenceΪ-1
		int gesturepassword = /* Settings.instance(). */localSharedPreferences
				.getInt("gesturepassword", -1);

		Log.i(TAG, gesturepassword + "<--gesturepassword");
		if (gesturepassword == -1) {
			// ������������ʱ(ֵΪ-1ʱ��ʾ��δ�����������)������2�Σ���У���Ƿ�һ�£���¼�����ݿ⣬���ñ�־λΪ���������������롱

			passwordhandle_for_set_sec();

		} else {
			// ���ǽ�������ʱ�������ݿ�ȡ�����룬��У�鵱ǰ���������
			Log.i(TAG, "���ǽ�������ʱ�������ݿ�ȡ�����룬��У�鵱ǰ���������   gesturepassword =="
					+ gesturepassword);

			passwordhandle_for_check_sec(gesturepassword);
		}
	}

	/**
	 * У������ʱ�Ĵ���
	 * */
	private void passwordhandle_for_check_sec(int gesturepassword) {
		// ��ȡ����������������� Ȼ����������ȶԲ���������

		for (GestureLockPointerBean iterable_element : focus_list) {
			Log.i(TAG, "iterable_element num == " + iterable_element.getNum());
			gesturePass.append(iterable_element.getNum() + "");
		}

		if (focus_list.size() >= 3) {

			if ((gesturepassword + "").equals(gesturePass.toString())) {
				Log.i(TAG, "�����ɹ�");
				if (callback != null)
					callback.refreshTitleText(
							GESTURE_TITLE_CONTENT_TYPE_CHECK_OK, -1);
				closeFather();
			} else {
				Log.i(TAG,
						"����ʧ��  gesturepassword == " + gesturepassword
								+ "����ʧ��  gesturePass.toString() == "
								+ gesturePass.toString());
				setPointerErrorState();

				if (callback != null)
					callback.refreshTitleText(
							GESTURE_TITLE_CONTENT_TYPE_CHECK_FALSE,
							reCheckCount);

				if (reCheckCount != 0) {
					reCheckCount--;
				} else {
					// 5���������
					if (callback != null)
						callback.refreshTitleText(-1, -1);
					reCheckCount = 5;
					closeFather();
				}
			}

		} else {

			setPointerErrorState();
			if (callback != null)
				callback.refreshTitleText(
						GESTURE_TITLE_CONTENT_TYPE_ENTER_SHORTERROR, -1);

		}

	}

	/**
	 * ��������ʱ�Ĵ���
	 * */
	private void passwordhandle_for_set_sec() {

		if (focus_list != null && focus_list.size() >= 3) {// �����صļ��ϳ��ȴ���3��3�����룩ʱ����¼���ǵĺ��벢�Ҵ���

			for (GestureLockPointerBean focus_point : focus_list) {

				gesturePass.append(focus_point.getNum() + "");

			}

			Log.i(TAG, gesturePass.toString() + "<--gesturePass");

			if (TextUtils.isEmpty(str_gestrue_pass_1)) {// ��һ�����������ʱ��

				str_gestrue_pass_1 = gesturePass.toString();
				Log.i(TAG, "str_gestrue_pass_1 = " + str_gestrue_pass_1);

				if (callback != null)
					callback.refreshTitleText(
							GESTURE_TITLE_CONTENT_TYPE_ENTER_AGAIN, -1);

			} else if (TextUtils.isEmpty(str_gestrue_pass_2)) {// �ڶ������������ʱ��

				str_gestrue_pass_2 = gesturePass.toString();
				Log.i(TAG, "str_gestrue_pass_2 = " + str_gestrue_pass_2);
				password_set_check();

			}

		} else if (focus_list != null && focus_list.size() < 3) {// �����еĵ�״̬����Ϊwrong����ʾ���볤�ȱ������3λ��

			if (callback != null) {
				callback.refreshTitleText(
						GESTURE_TITLE_CONTENT_TYPE_ENTER_SHORTERROR, -1);
			}
			setPointerErrorState();
		}
	}

	/**
	 * ��2�������������ʱ����������У�飬��������ʱ���õ��߼�
	 * */
	private void password_set_check() {
		if (str_gestrue_pass_1.equals(str_gestrue_pass_2)) {
			Log.i(TAG, "��������У��һ��");
			// 2������������ͬ��������ݿⱣ��,�������óɹ���Ȼ�����ĳ������
			Editor edit = localSharedPreferences.edit();
			edit.putInt("gesturepassword", Integer.parseInt(str_gestrue_pass_1));
			edit.commit();

			if (callback != null)
				callback.refreshTitleText(GESTURE_TITLE_CONTENT_TYPE_CHECK_OK,
						-1);

			closeFather();

		} else {
			Log.i(TAG, "��������У�鲻һ��");
			// 2���������벻��ͬ����ʾ2���������벻һ�²����������룬��ͣ���ڵ�ǰ������ʾ��������
			if (callback != null)
				callback.refreshTitleText(
						GESTURE_TITLE_CONTENT_TYPE_ENTER_TWICEERROR, -1);
			setPointerErrorState();
		}

	}

	/**
	 * �رո�activity
	 * */
	private void closeFather() {
		if (fatheractivity != null) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if (fatheractivity != null)
						fatheractivity.finish();
				}
			}, 500);
		}
	}

	/**
	 * ���õ�Ϊ����״̬�������ػ�
	 * */
	private void setPointerErrorState() {
		for (GestureLockPointerBean iterable_element : focus_list) {
			pointers.get(
					findWhichPoint(iterable_element.getX(),
							iterable_element.getY()).getNum() - 1).setState(
					STATE_WRONG);// ����ָ���ĵ�Ϊwrong
		}

		delayReset = true;

	}

	/**
	 * ������,
	 * */
	private ArrayList<GestureLockPointerBean> checkError() {

		for (GestureLockPointerBean iterable_element : pointers) {
			if (iterable_element.getState() == STATE_RIGHT) {
				Log.i(TAG,
						"checkedlist iterable_element"
								+ iterable_element.getNum());
				checkedlist.add(iterable_element);
			}
		}

		return checkedlist;
	}

	/**
	 * �������
	 * */
	private void clearData() {
		gesturePass.setLength(0);

		if (!TextUtils.isEmpty(str_gestrue_pass_1)
				&& !TextUtils.isEmpty(str_gestrue_pass_2)) {
			str_gestrue_pass_1 = "";
			str_gestrue_pass_2 = "";
		}

		focus_list.clear();

	}

	/**
	 * ��յ�ǰ�����ϵĻ��ƺۼ����ص���ʼ״̬
	 * */
	private void clearCavans() {

		if (delayReset) {
			delayHandler.sendEmptyMessageDelayed(0, 500);
			delayReset = false;
		} else {
			for (GestureLockPointerBean iterable_element : pointers) {
				iterable_element.setState(STATE_IDLE);
			}
			focus_list.clear();
		}

	}

	/**
	 * ���µ�Ļ���,����Ҳ�ǻ��ߵ�������µط�
	 * */
	private void updatePointsDraw(MotionEvent event) {
		touch_x = event.getX();
		touch_y = event.getY();
		the_pointer_in_area = checkThePointArea();// ���ص��ǵ�ǰtouch����ĵ�
		if (the_pointer_in_area != null) {
			pointers.get(the_pointer_in_area.getNum() - 1)
					.setState(STATE_RIGHT);

			if (!focus_list.contains(the_pointer_in_area))// ��������б��ﲻ�����õ�ʱ�򽫸õ���뵽�����б���
				focus_list.add(the_pointer_in_area);
		}
	}

	/**
	 * ��鵱ǰ��touch���Ƿ��ڵ�������ڣ����ڷ��ظõ�
	 * */
	private GestureLockPointerBean checkThePointArea() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				GestureLockPointerBean pointCheckCalc = pointCheckCalc(
						points_sets_x[i][j], points_sets_y[i][j],
						TypeValue_Dp_To_Px(OUTTER_CIRCLE_R), touch_x, touch_y);
				if (pointCheckCalc != null) {
					GestureLockPointerBean findWhichPoint = findWhichPoint(
							pointCheckCalc.getX(), pointCheckCalc.getY());
					return findWhichPoint;
				} else {
					// System.out.println("checkThePointArea false �ڷ�Χ��");
				}
			}
		}

		return null;
	}

	/**
	 * ��"��鵱ǰ��touch���Ƿ��ڵ�������ڣ����ڷ��ظõ�"ʱ�����ݽ���2�����꣬�ҳ���Ӧ��numλ��
	 * */
	public GestureLockPointerBean findWhichPoint(float x, float y) {
		for (GestureLockPointerBean iterable_element : pointers) {
			if (iterable_element.getX() == x && iterable_element.getY() == y) {
				return iterable_element;
			}
		}
		return null;
	}

	/**
	 * @param pointX
	 *            �ο���X
	 * @param pointY
	 *            �ο���Y
	 * @param r
	 *            �뾶
	 * @param movingX
	 *            �ƶ���X
	 * @param movingY
	 *            �ƶ���Y
	 * 
	 *            �����Ƿ���9����������ڣ������㷨
	 * */
	private GestureLockPointerBean pointCheckCalc(float pointX, float pointY,
			float r, float movingX, float movingY) {
		// ����
		if (Math.sqrt((pointX - movingX) * (pointX - movingX)
				+ (pointY - movingY) * (pointY - movingY)) < r) {
			return new GestureLockPointerBean(pointX, pointY, -1);
		}
		return null;
	}

	// ----------------------------------------
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	// ------------------- ���� ���ϡ�����������������������������������������---------------------------
	/**
	 * ��ʼ��SharedPrefrence,������
	 * */
	private void initSharedPrefrence() {
		if (localSharedPreferences == null) {
			localSharedPreferences = MyDemosApplication.mContext
					.getSharedPreferences("localhost", Context.MODE_PRIVATE);
			Editor edit = localSharedPreferences.edit();
			edit.putInt("gesturepassword", -1);
			edit.putBoolean("haslogin", false);
			edit.putBoolean("remenberLoginpassword", false);
			edit.putBoolean("gesturelockisopen", false);
			edit.commit();
		}
	}

	/**
	 * ���ƵĻص��ӿ�
	 * */
	public interface GestureCallback {
		void refreshTitleText(int type, int times);
	}

	/**
	 * dp ת px
	 * */
	public int TypeValue_Dp_To_Px(int mDip) {
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				mDip, MyDemosApplication.mContext.getResources()
						.getDisplayMetrics());
		return px;
	}
}

/**
 * �������������ģ��
 * */
class GestureLockPointerBean {

	private float x;
	private float y;
	private int num;
	private int state;

	public GestureLockPointerBean(float x, float y, int num) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return "GestureLockPointerBean [x=" + x + ", y=" + y + ", num=" + num
				+ ", state=" + state + "]";
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {

		if (!(o instanceof GestureLockPointerBean))
			return false;

		GestureLockPointerBean other = (GestureLockPointerBean) o;

		return this.x == other.x && this.y == other.y;
	}

}
