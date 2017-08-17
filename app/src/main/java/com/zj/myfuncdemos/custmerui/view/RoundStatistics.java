package com.zj.myfuncdemos.custmerui.view;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.zj.myfuncdemos.net.LogicUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ZhuJiao 2015.11.5 13:51:52 环形统计类
 * 
 *         使用方式： ①直接copy该类
 *         ②布局里长款别写warp，该控件onmesure方法还未得到优化，有时间再优化,目前使用方法是将高度设置为200
 *         ③调用该控件的父activity需要对该控件进行setitems进行初始化方法方可显示环形，待优化
 * 
 */
public class RoundStatistics extends View {

	/**
	 * 基本参数
	 * */
	private RectF rectf_oval;
	private ArrayList<Paint> paints = null;
	private RoundStatisticsBean[] items = null;
	private int screenWidth;
	private int screenHeight;
	private Context context;
	private float totalValue = 0;
	private boolean once = false;

	/**
	 * 作图参数
	 * */
	private int strokeWidth = 0;// 默认初始化弧形的粗细为20
	private int linestrokeWidth = 0;// 默认初始化线的粗细为4
	// 确定圆心/半径
	private float arc_heart_x;
	private float arc_heart_y;
	private float arc_raidus;
	// 不断刷新的绘制百分比弧形的坐标点
	private float start_x;
	private float start_y;
	// 画线的笔
	private Paint linePaint = null;
	private int lineColor = Color.parseColor("#ff119AE5");// 线的颜色
	// 画线的坐标
	private float lineStartX;
	private float lineStartY;
	private float lineStopX;
	private float lineStopY;
	private float verticallinelength = 1.5f;
	private float horizontallinelength = 30f;// 画字横线偏移量

	// 弧线集合处理
	ArrayList<Float> arcs = null;
	ArrayList<Float> poors = null;
	private Float poor = 0f;
	private Float minSweep = 45f;
	private Float maxSweep = 0f;
	private int parentwidth;
	private int parentheight;
	private boolean hasMax = false;
	private boolean allzero = false;

	// 画字儿参数相关
	private int textoffset = 0;
	private Paint blackWordPaint = null;
	private Paint blackCenterkWordPaint = null;
	private Paint orangeCenterkWordPaint = null;
	private int textSize = 0;

	private String centerValue = "0.00";
	private float pointer_bound = 0f;
	private DecimalFormat df;
	private DecimalFormat dfs;

	public void setCenterValue(double centerValue) {
		df = new DecimalFormat("#.00");
		this.centerValue = df.format(centerValue);
	}

	public RoundStatistics(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		defaultinit();
	}

	public RoundStatistics(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundStatistics(Context context) {
		this(context, null);
	}

	public void refreshui() {
		invalidate();
	}

	/**
	 * 初始化
	 * */
	private void defaultinit() {

		// 为了不报错而做的一些初始化
		if (paints == null || paints.size() > 0)
			paints = new ArrayList<Paint>();

		if (items == null) {
			items = new RoundStatisticsBean[] { new RoundStatisticsBean(
					Color.WHITE, 0f, "default") };
		}

		strokeWidth = LogicUtil.TypeValue_Dp_To_Px(15, context);
		linestrokeWidth = LogicUtil.TypeValue_Dp_To_Px(1, context);
		textoffset = LogicUtil.TypeValue_Dp_To_Px(8, context);// dp
		pointer_bound = LogicUtil.TypeValue_Dp_To_Px(3, context);// dp
		textSize = LogicUtil.TypeValue_Sp_To_Px(12, context);// sp
		// verticallinelength = LogicUtil.TypeValue_Dp_To_Px(1, context);// dp
		// horizontallinelength = LogicUtil.TypeValue_Dp_To_Px(1, context);//
		// dp// 画字横线偏移量
		// verticallinelength = 10f;
		// horizontallinelength =10f;

		initPaint(items);
	}

	/**
	 * 初始化画笔
	 * */
	private void initPaint(RoundStatisticsBean[] items) {

		if (paints.size() > 0 && paints != null)
			paints.clear();

		if (linePaint == null) {
			linePaint = new Paint();
			linePaint.setStrokeWidth(linestrokeWidth);
			linePaint.setColor(lineColor);
			linePaint.setTextSize(textSize);
		}

		if (blackWordPaint == null) {
			blackWordPaint = new Paint();
			blackWordPaint.setStrokeWidth(linestrokeWidth);
			blackWordPaint.setColor(Color.BLACK);
			blackWordPaint.setTextSize((int) (textSize / 3 * 2));
		}

		if (blackCenterkWordPaint == null) {
			blackCenterkWordPaint = new Paint();
			blackCenterkWordPaint.setStrokeWidth(linestrokeWidth);
			blackCenterkWordPaint.setColor(Color.BLACK);
			blackCenterkWordPaint.setTextSize((int) (textSize / 3 * 4));
		}

		if (orangeCenterkWordPaint == null) {
			orangeCenterkWordPaint = new Paint();
			orangeCenterkWordPaint.setStrokeWidth(linestrokeWidth);
			orangeCenterkWordPaint.setColor(Color.parseColor("#ffff4400"));// 橙色
			orangeCenterkWordPaint.setTextSize((int) (textSize / 3 * 2));
		}

		/**
		 * 将画笔加进集合
		 * */
		for (int i = 0; i < items.length; i++) {
			Paint paint = new Paint();
			paint.setColor(items[i].getColor());
			paint.setStrokeWidth(strokeWidth);
			paint.setStyle(Paint.Style.STROKE);
			totalValue += items[i].getValue();
			paints.add(paint);
		}

		arcs = new ArrayList<Float>();
		poors = new ArrayList<Float>();
	}

	/**
	 * 重要的方法
	 * */
	public void setItems(RoundStatisticsBean[] items) {
		this.items = items;
		maxSweep = 360 - (minSweep * (items.length - 1));
		System.out.println(maxSweep + "<---maxSweep");

		for (int i = 0; i < items.length; i++) {
			if (items[i].getValue() != 0) {
				allzero = false;
				break;
			} else {
				allzero = true;
			}
		}

		initPaint(items);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawArcs(canvas);
	}

	/**
	 * 画点,线,字 这是难点
	 * */
	private void drawLineCircleText(Canvas canvas, float start, float sweep,
			int index) {

		// core: 点线字算法 核心算法 重要
		float radians = (float) ((start + sweep / 2) / 180 * Math.PI);

		// 计算从一开始画线到画出字的路径
		lineStartX = arc_heart_x + arc_raidus * (float) (Math.cos(radians));
		lineStartY = arc_heart_y + arc_raidus * (float) (Math.sin(radians));
		lineStopX = arc_heart_x + arc_raidus * verticallinelength
				* (float) (Math.cos(radians));
		lineStopY = arc_heart_y + arc_raidus * verticallinelength
				* (float) (Math.sin(radians));

		// 画竖线
		canvas.drawLine(lineStartX, lineStartY, lineStopX, lineStopY, linePaint);// 垂直顶点延长线

		// 开始画字的指引线(即横线) 根据左右屏幕进行判断指引线该往哪儿拐
		if (lineStopX > parentwidth / 2) {// 右边半屏
			canvas.drawLine(lineStopX, lineStopY, lineStopX
					+ horizontallinelength, lineStopY, linePaint);// 垂直顶点水平偏移线

			float circle_X_right = lineStopX + horizontallinelength;
			float circle_Y_right = lineStopY;

			// 圆dp
			canvas.drawCircle(circle_X_right, circle_Y_right, pointer_bound,linePaint);
			drawTheText(canvas, circle_X_right, circle_Y_right, items[index]);

		} else {// 左边半屏
			canvas.drawLine(lineStopX, lineStopY, lineStopX- horizontallinelength, lineStopY, linePaint);// 垂直顶点水平偏移线

			float circle_X_left = lineStopX - horizontallinelength;
			float circle_Y_left = lineStopY;

			// 圆dp
			canvas.drawCircle(circle_X_left, circle_Y_left, pointer_bound,
					linePaint);
			drawTheText(canvas, circle_X_left, circle_Y_left, items[index]);
		}

	}

	/**
	 * 画字
	 * */
	private void drawTheText(Canvas canvas, float X, float Y,
			RoundStatisticsBean obj) {

		// 大字
		int textWidth = getTextWidth(linePaint, obj.getTypename()) + textoffset;
		FontMetrics fm = linePaint.getFontMetrics();
		float textHeight = (float) Math.ceil(fm.descent - fm.ascent);
		// 小字
		int textSmallWidth = getTextWidth(blackWordPaint, obj.getValue() + "");
		if (textSmallWidth > textWidth)
			textWidth = textSmallWidth;

		if (dfs == null)
			dfs = new DecimalFormat("#.00");

		// 上
		if (Y < arc_heart_y) {
			if (X < arc_heart_x) { // 左
				canvas.drawText(obj.getTypename(), X - textWidth, Y
						- (textHeight / 2), linePaint);
				canvas.drawText("￥" + dfs.format(obj.getValue()) + "", X
						- textWidth, Y + (textHeight / 2), blackWordPaint);
			} else { // 右
				canvas.drawText(obj.getTypename(), X + textoffset, Y
						- (textHeight / 2), linePaint);
				canvas.drawText("￥" + dfs.format(obj.getValue()) + "", X
						+ textoffset, Y + (textHeight / 2), blackWordPaint);
			}
		} else { // 下
			// 左
			if (X < arc_heart_x) {
				canvas.drawText(obj.getTypename(), X - textWidth, Y
						- (textHeight / 2), linePaint);
				canvas.drawText("￥" + dfs.format(obj.getValue()) + "", X
						- textWidth, Y + (textHeight / 2), blackWordPaint);
			} else { // 右
				canvas.drawText(obj.getTypename(), X + textoffset, Y
						- (textHeight / 2), linePaint);
				canvas.drawText("￥" + dfs.format(obj.getValue()) + "", X
						+ textoffset, Y + (textHeight / 2), blackWordPaint);
			}
		}

		drawTextInCenter(canvas);
	}

	/**
	 * 画中间的字
	 * */
	private void drawTextInCenter(Canvas canvas) {
		// 画中间的大字
		String centerStr_big = "资金总额";
		FontMetrics fm_big = blackCenterkWordPaint.getFontMetrics();
		int bigCenterTextWidth = getTextWidth(blackCenterkWordPaint,
				centerStr_big) + textoffset;
		float bigtextHeight = (float) Math.ceil(fm_big.descent - fm_big.ascent);
		canvas.drawText(centerStr_big, arc_heart_x - (bigCenterTextWidth / 2),
				arc_heart_y - (bigtextHeight / 2), blackCenterkWordPaint);

		// 画中间的小字
		String centerStr_small = "￥" + this.centerValue + "元";
		FontMetrics fm_small = orangeCenterkWordPaint.getFontMetrics();
		int smallCenterTextWidth = getTextWidth(orangeCenterkWordPaint,
				centerStr_small) + textoffset;
		float smalltextHeight = (float) Math.ceil(fm_small.descent
				- fm_small.ascent);
		canvas.drawText(centerStr_small, arc_heart_x
				- (smallCenterTextWidth / 2), arc_heart_y + smalltextHeight,
				orangeCenterkWordPaint);
	}

	/**
	 * 获取字的宽高
	 * */
	public static int getTextWidth(Paint paint, String str) {
		int iRet = 0;
		if (str != null && str.length() > 0) {
			int len = str.length();
			float[] widths = new float[len];
			paint.getTextWidths(str, widths);
			for (int j = 0; j < len; j++) {
				iRet += (int) Math.ceil(widths[j]);
			}
		}
		return iRet;
	}

	/**
	 * 画圆形统计
	 * */
	private void drawArcs(Canvas canvas) {

		if (!allzero) {
			for (int i = 0; i < paints.size(); i++) {
				if (!hasMax) {
					float sweep = calcPercent(items[i].getValue());
					exchange(sweep);
				} else {
					break;
				}
			}

			if (hasMax) {
				addMaxSweep();
				hasMax = false;
			}
		} else {
			addAllZero();
			allzero = false;
		}

		for (int i = 0; i < arcs.size(); i++) {
			if (i >= items.length)
				break;// 华为手机bug
			drawLineCircleText(canvas, start_x, arcs.get(i), i);// 这里有个巧妙的用法，它们绘制的顺序避免了线遮盖弧，后绘制弧的话可以覆盖掉线，这样看起来友好很多
			canvas.drawArc(rectf_oval, start_x, arcs.get(i), false,
					paints.get(i));
			start_x += arcs.get(i);
		}

	}

	/**
	 * 当全部为0 的情况下直接均分圆环
	 * */
	private void addAllZero() {
		arcs.clear();
		for (int i = 0; i < items.length; i++) {
			arcs.add((float) (360 / items.length));
		}
	}

	/**
	 * 当所有的比例为满360度时候的处理 暂无处理
	 * */
	private void totalangelcheck360() {
		// for (int i = 0; i < arcs.size(); i++) {
		// }
	}

	/**
	 * 重新洗牌 调整百分比 将角度太小会造成绘制的重叠
	 * */
	private void exchange(float sweep) {

		poor = 0f;// 定义一个差值

		if (sweep < minSweep) {// 当返回的百分比小于某个会导致绘制重叠的度数时
			poor = minSweep - sweep;// 记录它距离最小差值有多少差距
			arcs.add(minSweep);// 然后把这个不够最小差值的度数以最小度数的方式保存到集合里（之后根据这个集合进行度数画弧）
			poors.add(poor);// 然后把那个差值添加到一个集合里保存记录
		} else if (sweep >= maxSweep) {
			hasMax = true;
			// addMaxSweep();
			// arcs.add(sweep);// 添加进绘弧的集合
		} else {// 当返回的百分比并不于某个会导致绘制重叠的度数时，说明正常
			arcs.add(sweep);// 添加进绘弧的集合
		}

		if (!hasMax) {
			// 在添加完绘狐集合 和 差值集合 之后 进行再次调整 将之前多增加的度数 从绘弧集合里进行一个减掉 这样才能整体保持圆环的平衡
			if (poors.size() > 0) {// 如果差值集合里有差值（也就是进行过无理由的增加时）
				for (int i = 0; i < poors.size(); i++) {// 遍历这个集合
					Float tmpPoor = poors.get(i);// 取出其中的差值
					for (int j = 0; j < arcs.size(); j++) {// 再将绘弧集合里的东西取出
						Float tmpCalc = arcs.get(j) - tmpPoor;// 用这个绘狐集合里的数去减这个差值
						if (tmpCalc > minSweep) {// 如果减去的结果不会导致绘制重合 //
													// 那么替换并记录到绘弧集合里边去
							arcs.remove(j);
							arcs.add(j, tmpCalc);
						}
					}
				}
			}
		}

	}

	/**
	 * 遇见有最大比例失衡的情况处理
	 * */
	private void addMaxSweep() {
		int index = arcs.size();// 遇见大的时候我之前没加筋集合 所以也不必减1了
		arcs.clear();
		for (int i = 0; i < items.length; i++) {
			if (i < index) {
				arcs.add(minSweep);
			} else if (i == index) {
				arcs.add(maxSweep);
			} else {
				arcs.add(minSweep);
			}
		}
	}

	/**
	 * 设置自己的宽和高。
	 * */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (!once) {

			// 暂时未用到 为wrap时候需要考虑
			// int widthMode = MeasureSpec.getMode(widthMeasureSpec);
			// int heightMode = MeasureSpec.getMode(heightMeasureSpec);

			parentwidth = MeasureSpec.getSize(widthMeasureSpec);
			parentheight = MeasureSpec.getSize(heightMeasureSpec);

			initContentParams(parentwidth, parentheight);

			setMeasuredDimension(parentwidth, parentheight);

			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 开始具体绘制之前准备一些参数
	 * */
	private void initContentParams(int width, int height) {
		// 圆心定义
		arc_heart_x = width / 2;
		arc_heart_y = height / 2;

		// 半径定义 最大变减去最小边除以2
		if (width > height) {
			arc_raidus = ((width - height) / 3);
		} else {
			arc_raidus = ((height - width) / 3);
		}

		// 用圆心和半径来划定一个范围 作为 弧的框架
		rectf_oval = new RectF();
		rectf_oval.left = arc_heart_x - arc_raidus;
		rectf_oval.top = arc_heart_y - arc_raidus;
		rectf_oval.right = arc_heart_x + arc_raidus;
		rectf_oval.bottom = arc_heart_y + arc_raidus;

	}

	/**
	 * 计算业务百分比的公式 返回的是一个（相对于）圆周的角度
	 * */
	public float calcPercent(double value) {
		System.out.println(totalValue + "<---totalValue");
		return (float) value / totalValue * 360;
	}

	/**
	 * item的bean类
	 * */
	public static class RoundStatisticsBean {

		int color;
		double value;
		String typename;

		public RoundStatisticsBean(int color, double value, String typename) {
			super();
			this.color = color;
			this.value = value;
			this.typename = typename;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public double getValue() {
			return value;
		}

		public void setValue(float value) {
			this.value = value;
		}

		public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
		}
	}

	public RoundStatisticsBean[] getItems() {
		return items;
	}

}

// private Data mReceivedInterestData = null;// 可用利息
// private Data mFrozenMoneyData = null;// 冻结资金
// private Data mReceivableCorpusData = null;// 待收本金
// private Data mReceivableInterestData = null;// 待收利息
// private Data mUsemoneyData = null;// 可用资金

// 绘制圆形百分比的关键
// ---------------------------------------------
// 1:关于弧度
// 单位圆(即半径为1的圆)中度数为1的圆心角对应的弧长就是1个弧度.
// 那么圆周就是2pai弧度,其中pai是圆周率,于是
// 1弧度=(180/pai)度,
// ---------------------------------------------
// 2:关于Math.cos 和 Math.sin
// Math.sin(x) x 的正玄值。返回值在 -1.0 到 1.0 之间；
// Math.cos(x) x 的余弦值。返回的是 -1.0 到 1.0 之间的数；
// 这两个函数中的X 都是指的“弧度”而非“角度”，弧度的计算公式为： 2*PI/360*角度；
// 30° 角度 的弧度 = 2*PI/360*30

// ---------------------------------------------
// 3:如何获得一段圆弧的中间点位置？
// float radians = (float) ((start + sweep / 2) / 180 * Math.PI);
//
// 参考源码： 圆心 + 半径 * 正余弦
// float lineStartX = pieCenterX + pieRadius /** 0.7f*/* (float)
// (Math.cos(radians));
// float lineStartY = pieCenterY + pieRadius /** 0.7f*/* (float)
// (Math.sin(radians));
