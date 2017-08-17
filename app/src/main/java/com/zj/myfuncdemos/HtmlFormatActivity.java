package com.zj.myfuncdemos;

import org.xml.sax.XMLReader;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zj.myfuncdemos.net.LogicUtil.postRequestCallBack;
import com.zj.myfuncdemos.net.ParseJson;
import com.zj.myfuncdemos.net.PostByVolley;
import android.app.Activity;
import android.app.DownloadManager.Request;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import zj.myfuncdemos.R;

public class HtmlFormatActivity extends Activity {

	private TextView content;
	private RequestQueue mQueue;

	public class ImageClick extends ClickableSpan {

		String name = null;

		public ImageClick(String name) {
			this.name = name;
		}

		@Override
		public void onClick(View arg0) {
			System.out.println(" name == " + name);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_htmlformat);
		content = (TextView) findViewById(R.id.content);
		content.setMovementMethod(LinkMovementMethod.getInstance());
		mQueue = Volley.newRequestQueue(HtmlFormatActivity.this);

		final TagHandler tagHandler = new TagHandler() {

			/** opening Ӧ���Ǳ�ǩ����ʼ tag�Ǳ�ǩ�� */
			@Override
			public void handleTag(boolean opening, String tag,
					Editable output_edittable, XMLReader xmlReader) {

				if (tag.toLowerCase().equals("img")) {
					int length = output_edittable.length();
					ImageSpan[] spans = output_edittable.getSpans(length - 1,
							length, ImageSpan.class);
					String imgURL = spans[0].getSource();
					
					output_edittable.setSpan(new ImageClick(imgURL), length - 1, length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

				}
			}
		};

		/**
		 * imageGetter
		 * */
		final ImageGetter imageGetter = new ImageGetter() {

			@Override
			public Drawable getDrawable(String source) {

				final URLDrawable urlDrawable = new URLDrawable();

				PostByVolley.postImage(mQueue, "http://182.92.219.229:80/"
						+ source, new postRequestCallBack() {

					@Override
					public void onSuccessBitmap(int responceCode,
							Bitmap result, String responceMessage) {

						urlDrawable.bitmap = result;
						urlDrawable.setBounds(0, 0, result.getWidth(),
								result.getHeight());
						content.invalidate();
						content.setText(content.getText()); // ���ͼ���ص�

					}

					@Override
					public void onSuccess(int responceCode, String result,
							String responceMessage) {

					}

					@Override
					public void onFail(int responceCode, String responceMessage) {

					}

					@Override
					public void onDisconnect() {

					}
				}, 500, 500, Config.ARGB_8888);

				return urlDrawable;
			}
		};

		/**
		 * htmlformatCallBack
		 * */
		postRequestCallBack htmlformatCallBack = new postRequestCallBack() {

			private String parstHtmlContent = null;

			@Override
			public void onSuccessBitmap(int responceCode, Bitmap result,
					String responceMessage) {
			}

			@Override
			public void onSuccess(int responceCode, String result,
					String responceMessage) {
				System.out.println("html result == " + result);
				parstHtmlContent = ParseJson.parstHtmlContent(result);
				System.out.println("parstHtmlContent == " + parstHtmlContent);

				/**
				 * ����html��ǩ����ʾ��textview���
				 * */
				content.setText(Html.fromHtml(parstHtmlContent, imageGetter,tagHandler));
			}

			@Override
			public void onFail(int responceCode, String responceMessage) {

			}

			@Override
			public void onDisconnect() {

			}

		};

		String url = "http://182.92.219.229:80/app/cmslist?nodeid=notice43";

		PostByVolley.postString(mQueue, url, htmlformatCallBack);
	}

	/**
	 * URLDrawable
	 * */
	public class URLDrawable extends BitmapDrawable {
		protected Bitmap bitmap;

		@Override
		public void draw(Canvas canvas) {
			if (bitmap != null) {
				canvas.drawBitmap(bitmap, 0, 0, getPaint());
			}
		}
	}

}
