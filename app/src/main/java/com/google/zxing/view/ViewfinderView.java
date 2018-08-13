/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.google.zxing.camera.CameraManager;
import com.uppayplugin.unionpay.javabasetes.R;

import java.util.Collection;
import java.util.HashSet;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 */
public final class ViewfinderView extends View {

	/**
	 * 刷新界面的时间
	 */
	private static final long ANIMATION_DELAY = 100L;
	private static final int OPAQUE = 0xFF;

	/**
	 * 扫描框中的中间线的宽度
	 */
	private static final int MIDDLE_LINE_WIDTH = 5;

	/**
	 * 扫描框中的中间线的与扫描框左右的间隙
	 */
	private static final int MIDDLE_LINE_PADDING = 30;

	/**
	 * 扫描框中的中间线每次刷新移动的距离
	 */
	private static final int SPEEN_DISTANCE = 10;

	/**
	 * 画笔对象的引用
	 */
	private Paint paint;

	/**
	 * 中间滑动线的最顶端位置
	 */
	private int slideTop;

	/**
	 * 将扫描的二维码拍下来
	 */
	private Bitmap resultBitmap;
	private final int maskColor;
	private final int resultColor;
	private final int frameColor;
	private final int lineColor;
	private final int resultPointColor;
	private final int barColor;
	private Collection<ResultPoint> possibleResultPoints;
	private Collection<ResultPoint> lastPossibleResultPoints;

	private boolean isFirst;

	// This constructor is used when the class is built from an XML resource.
	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// Initialize these once for performance rather than calling them every
		// time in onDraw().
//		Paint   画笔
//		Bitmap  画布
//		Canvas  画家
		paint = new Paint();
		Resources resources = getResources();
		maskColor = resources.getColor(R.color.viewfinder_mask);
		resultColor = resources.getColor(R.color.result_view);
		frameColor = resources.getColor(R.color.viewfinder_frame);
		lineColor = resources.getColor(R.color.status_bar_color);//扫线色
		barColor = resources.getColor(R.color.status_bar_color);
		resultPointColor = resources.getColor(R.color.possible_result_points);//扫亮点
		possibleResultPoints = new HashSet<ResultPoint>(5);
	}

	@Override
	public void onDraw(Canvas canvas) {
		// 中间的扫描框（修改扫描框的大小，CameraManager里修改）
		Rect frame = CameraManager.get().getFramingRect();
		if (frame == null) {
			return;
		}

		// 初始化中间线滑动的最上边和最下边
		if (!isFirst) {
			isFirst = true;
			slideTop = frame.top;
		}

		// 获取屏幕的宽和高
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		// Draw the exterior (i.e. outside the framing rect) darkened
//		paint.setColor(resultBitmap != null ? resultColor : maskColor);//绘制框外四周颜色
//
//		// 绘制扫描框外面的阴影部分，共四个部分，扫描框的上面到屏幕上面，扫描框的下面到屏幕下面，扫描框的左边面到屏幕左边，扫描框的右边到屏幕右边
//		canvas.drawRect(0, 0, width, frame.top, paint);
//		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
//		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
//		canvas.drawRect(0, frame.bottom + 1, width, height, paint);

		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else {
			paint.setColor(barColor);

			// 绘制扫描框边上的角，总共8个部分
			canvas.drawRect(15 + frame.left, 15 + frame.top, 15 + (10 + frame.left), 15 + (50 + frame.top), paint);
			canvas.drawRect(15 + frame.left, 15 + frame.top, 15 + (50 + frame.left), 15 + (10 + frame.top), paint);
			canvas.drawRect(-15 + ((0 - 10) + frame.right), 15 + frame.top, -15 + (1 + frame.right), 15 + (50 + frame.top), paint);
			canvas.drawRect(-15 + (-50 + frame.right), 15 + frame.top, -15 + frame.right, 15 + (10 + frame.top), paint);
			canvas.drawRect(15 + frame.left, -15 + (-49 + frame.bottom), 15 + (10 + frame.left), -15 + (1 + frame.bottom), paint);
			canvas.drawRect(15 + frame.left, -15 + ((0 - 10) + frame.bottom), 15 + (50 + frame.left), -15 + (1 + frame.bottom), paint);
			canvas.drawRect(-15 + ((0 - 10) + frame.right), -15 + (-49 + frame.bottom), -15 + (1 + frame.right), -15 + (1 + frame.bottom), paint);
			canvas.drawRect(-15 + (-50 + frame.right), -15 + ((0 - 10) + frame.bottom), -15 + frame.right, -15 + (10 - (10 - 1) + frame.bottom), paint);

			// 绘制中间线，每次刷新界面，中间的线往下移动SPEEN_DISTANCE
			slideTop += SPEEN_DISTANCE;
			if (slideTop >= frame.bottom) {
				slideTop = frame.top;
			}
			//canvas.drawRect（left，top，right，bottom）参数
			paint.setColor(lineColor);
			canvas.drawRect(//画圆
					frame.left + MIDDLE_LINE_PADDING,
					slideTop - MIDDLE_LINE_WIDTH / 2,
					frame.right - MIDDLE_LINE_PADDING,
					slideTop + MIDDLE_LINE_WIDTH / 2,
					paint);

			// 画扫描框下面的字
//			paint.setColor(barColor);
//			paint.setTextSize(24 * Constant.SCREEN_DENSITY);
//			paint.setAlpha(0x40);
//			paint.setTypeface(Typeface.create("System", Typeface.BOLD));
//			String textStr = getResources().getString(R.string.capture_scan_text);
//			canvas.drawText(//画文本
//					textStr,
//					frame.left,
//					(float) (frame.bottom + (float) 30 * Constant.SCREEN_DENSITY),
//					paint);

//			canvas.drawText(
//					textStr,
//					width /2 - getTextBound(textStr).width()/2,
//					(float) (frame.bottom + (float) 30 * Constant.SCREEN_DENSITY),
//					paint
//			);

			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty()) {
				lastPossibleResultPoints = null;
			} else {
				possibleResultPoints = new HashSet<ResultPoint>(5);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible) {
					canvas.drawCircle(//画圆
							frame.left + point.getX(),
							frame.top + point.getY(),
							6.0f,
							paint);
				}
			}
			if (currentLast != null) {
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast) {
					canvas.drawCircle(//画圆
							frame.left + point.getX(),
							frame.top + point.getY(),
							3.0f,
							paint);
				}
			}

			// 只刷新扫描框的内容，其他地方不刷新
			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
		}
	}

	/**
	 * 获取文字的边界
	 * @param text
	 * @return
	 */
	private Rect getTextBound(String text){
		Rect textRect = new Rect();
		paint.getTextBounds(text,0,text.length(),textRect);
		return textRect;
	}

	public void drawViewfinder() {
		resultBitmap = null;
		invalidate();
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live
	 * scanning display.
	 *
	 * @param barcode
	 *            An image of the decoded barcode.
	 */
	public void drawResultBitmap(Bitmap barcode) {
		resultBitmap = barcode;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point) {
		possibleResultPoints.add(point);
	}

}
