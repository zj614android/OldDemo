package com.zj.myfuncdemos.custmerui.view.hacker;

import com.zj.myfuncdemos.MyDemosApplication;
import com.zj.myfuncdemos.net.LogicUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class HkTextGroup extends View{
	
	private char[] counts = new char[]{'A','B','C','D','E','F','G','H','J','K','L','M','N','O'};
	
	private Paint paint;
	
	private Context ctx;
	
	public HkTextGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = context;
		init();
	}

	
	private Cell[][] cells;
	
	/**
	 * ����ֵ
	 */
	private int textSize = 20;
	
	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setTextSize(textSize);//PXֵ
		paint.setTextAlign(Align.LEFT);
		paint.setStyle(Style.FILL);
		
		cells = new Cell[list][row];
		for (int j = 0; j < list; j++) {
			for (int i = 0; i < row; i++) {
				cells[j][i]=new Cell(i,j);
				cells[j][i].alpha=0;
				cells[j][i].msg = ""+counts[(int)(Math.random()*counts.length)];
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		textSize = LogicUtil.TypeValue_Sp_To_Px(12, MyDemosApplication.mContext);
		left_bottom = getHeight();
	}
	
	public float left;
	public float left_bottom ;
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int flag = msg.what;// 0 -- 10 
			
			for (int j = 0; j < list; j++) {
				
				for (int i = row-1; i >=0; i--) {
				//1�������һ��͸����Ϊ0������һ�����ʱ�Ϊ255
				//2������м���͸����Ϊ0����������
				//3���м��в�Ϊ0�����μ���һ���ݶ�
				//4���������һ����255����ô��Ҳ��255,�������ȼ�1	
					Cell cell = cells[j][i];
					
					/*
					 * if(cell.alpha == 255 && i<row-1){ cell.alpha=255-25;
					 * cells[j][i+1].alpha=255;
					 * 
					 * }else
					 */
					if (i == 0) {
						if (cell.alpha == 0) {
							if (Math.random() * 10 > 9) {
								cell.alpha = 255;
							}
						}else{
							cell.alpha = cell.alpha-25>0?cell.alpha-25:0;
						}
					}else if(i>0 && i<=row-1){
						if(cells[j][i-1].alpha==255 ){
//							cells[j][i-1].alpha=255-25;
							cell.alpha=255;
						}else{
							cell.alpha = cell.alpha-25>0?cell.alpha-25:0;
						}
					}
				}
			}
			invalidate();
			
		};
	};
	
	private int seed = 0;
	
	private int stepCount = 11;
	
	/**��*/
	private int row = 50;
	
	/**��*/
	private int list = 50;
	
	@Override
	protected void onDraw(Canvas canvas) {

		for (int j = 0; j < list; j++) {
			for (int i = 0; i < row; i++) {
				Cell cell = cells[j][i];
				//С�����¼����ı�����
				if(Math.random()*100>85){
					cell.msg = ""+counts[(int)(Math.random()*counts.length)];
				}
				//����͸����ȷ����ɫ
				if(cell.alpha==255){
					paint.setColor(Color.WHITE);
				}else{
					paint.setColor(Color.GREEN);
				}
				//����͸����
				paint.setAlpha(cell.alpha);
				
				//����
				if(cell.alpha!=0){
					canvas.drawText(cell.msg, cell.j*15, (float) (cell.i*textSize*0.6+textSize),paint);
				}
			}
		}
		
//		seed = (seed + 1) % stepCount;
		handler.sendEmptyMessageDelayed(seed, 10);
	}

	private class Cell{
		public Cell(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		/**��*/
		public int i;
		/**��*/
		public int j;
		/**����*/
		public int seed;
		/**͸����*/
		public int alpha;
		public String msg;
		
	}
	
}
