package com.uppayplugin.unionpay.javabasetes.utils.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.uppayplugin.unionpay.javabasetes.R;


/**
 * @description 涂鸦签名
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class WritePadDialog extends Dialog implements OnClickListener {

	private Context mContext;
	private LayoutParams p;
	private PaintView mView;

	public WritePadDialog(Context context) {
		super(context);
		this.mContext = context;
	}

	/** The index of the current color to use. */
	int mColorIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.write_pad);

		setTitle("请手写签名：");

		Display display = getWindow().getWindowManager().getDefaultDisplay();
		p = getWindow().getAttributes(); // 获取对话框当前的参数值
		p.width = (int) (display.getWidth() * 1);// 设置屏幕的宽度
		p.height = (int) (display.getHeight() * 0.5);// 设置屏幕的高度
		// p.height = (int) (p.width/3);// 设置屏幕的高度
		getWindow().setAttributes(p); // 设置生效

		mView = new PaintView(mContext);
		LinearLayout frameLayout = (LinearLayout) findViewById(R.id.tablet_view);
		frameLayout.addView(mView);
		mView.requestFocus();

		Button btnCommit = (Button) findViewById(R.id.btn_commit);
		Button btnClear = (Button) findViewById(R.id.btn_clear);

		btnCommit.setOnClickListener(this);
		btnClear.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_commit:
			doGetBitmap();
			break;
		case R.id.btn_clear:
			mView.clear();
			break;
		}
	}

	public Bitmap doGetBitmap() {

		return mView.getCachebBitmap();
	}

	/**
	 * This view implements the drawing canvas.
	 *
	 * It handles all of the input events and drawing functions.
	 */
	class PaintView extends View {
		private Canvas cacheCanvas;
		private Bitmap cachebBitmap;
		private Paint paint;// 画笔
		private Path path;// 路径

		public Bitmap getCachebBitmap() {
			return cachebBitmap;
		}

		public PaintView(Context context) {
			super(context);
			init();
		}

		private void init() {
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setStrokeWidth(3);// 画笔宽度
			paint.setStyle(Paint.Style.STROKE);// 画笔样式
			paint.setColor(Color.BLACK);// 画笔颜色
			paint.setDither(true);// 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰

			path = new Path();
			cachebBitmap = Bitmap.createBitmap(p.width, p.height, Config.ARGB_8888);
			// cachebBitmap = Bitmap.createBitmap(p.width, p.width/3,
			// 保存一次一次绘制出来的图形
			cacheCanvas = new Canvas(cachebBitmap);
			cacheCanvas.drawColor(Color.WHITE);
		}

		public void clear() {
			if (cacheCanvas != null) {
				paint.setColor(Color.WHITE);
				cacheCanvas.drawPaint(paint);
				paint.setColor(Color.BLACK);
				cacheCanvas.drawColor(Color.WHITE);
				invalidate();
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// canvas.drawColor(BRUSH_COLOR);
			canvas.drawBitmap(cachebBitmap, 0, 0, null);
			canvas.drawPath(path, paint);
		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			int curW = cachebBitmap != null ? cachebBitmap.getWidth() : 0;
			int curH = cachebBitmap != null ? cachebBitmap.getHeight() : 0;
			if (curW >= w && curH >= h) {
				return;
			}

			if (curW < w) {
				curW = w;
			}
			if (curH < h) {
				curH = h;
			}

			Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Config.ARGB_8888);
			Canvas newCanvas = new Canvas();
			newCanvas.setBitmap(newBitmap);
			if (cachebBitmap != null) {
				newCanvas.drawBitmap(cachebBitmap, 0, 0, null);
			}
			cachebBitmap = newBitmap;
			cacheCanvas = newCanvas;
		}

		private float cur_x, cur_y;

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX();
			float y = event.getY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				cur_x = x;
				cur_y = y;
				path.moveTo(cur_x, cur_y);
				break;
			case MotionEvent.ACTION_MOVE:
				path.quadTo(cur_x, cur_y, x, y);
				cur_x = x;
				cur_y = y;
				break;
			case MotionEvent.ACTION_UP:
				cacheCanvas.drawPath(path, paint);
				path.reset();
				break;
			}

			invalidate();

			return true;
		}
	}

}
