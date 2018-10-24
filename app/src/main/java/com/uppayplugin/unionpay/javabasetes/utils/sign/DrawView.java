package com.uppayplugin.unionpay.javabasetes.utils.sign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

public class DrawView extends View {

    /**
     * 签名画笔
     */
    private Paint paint;
    /**
     * 签名画布
     */
    private Canvas cacheCanvas;
    /**
     * 画笔路径
     */
    private Path path;
    /**
     * 缓存图片
     */
    private Bitmap cacheBitmap;
    /**
     * 图片宽度
     */
    private int width;
    /**
     * 图片高度
     */
    private int height;
    /**
     * 手指触摸屏幕时的X，Y坐标
     */
    private float xDown, yDown;
    /**
     * 是否正在绘制
     */
    private boolean isDrawing = false;
    /* 是否有绘制过 */
    public static boolean painted = false;
    /**
     * 默认画笔颜色
     */
    private int paintColor = Color.BLACK;

    /**
     * 点数
     */
    private long pointCount = 0;

    public long getPointCount() {
        return pointCount;
    }

    public void setPointCount(long pointCount) {
        this.pointCount = pointCount;
    }

    /**
     * 默认画板背景色
     */
    private int canvasColor = Color.parseColor("#ffffff");

    /**
     * 画布类
     *
     * @param context
     * @param width
     * @param height
     * @param random      :画布水印数字
     * @param screenHeigh :屏幕高度
     */
    public DrawView(Context context, int width, int height, String random,
                    int screenHeigh) {
        super(context);
        this.width = width;
        this.height = height;

        Logger.e("width:" + width + ",height:" + height);

        initWedgits();
        int w = cacheCanvas.getWidth();
        int h = cacheCanvas.getHeight();

        Logger.e("draw_width:" + w + ",draw_height:" + h);

        Paint p = new Paint();
        // paint.setTypeface(Typeface.create("Times New Roman",
        // Typeface.NORMAL));
        p.setTypeface(Typeface.SERIF);

        // 水印的字体大小
        if (screenHeigh > 720) {
            p.setTextSize(140);
        } else {
            p.setTextSize(70);
        }
        p.setAntiAlias(true);// 去锯齿
        p.setTextAlign(Paint.Align.CENTER);
        if (random == null) {
            cacheCanvas.drawText("", w / 2, h / 2, p);
        } else {
            if (screenHeigh > 720) {
                cacheCanvas.drawText(random, w / 2, h / 2 + 70, p);
            } else {
                cacheCanvas.drawText(random, w / 2, h / 2 + 35, p);
            }

        }

    }

    /**
     * 初始化组件
     */
    private void initWedgits() {
        try {
            paint = new Paint(Paint.DITHER_FLAG);
            // 设置抗锯齿
            paint.setAntiAlias(true);
            // 设置画笔宽度
            paint.setStrokeWidth(8);
            paint.setDither(true);
            // 设置样式
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            // 画笔颜色
            paint.setColor(paintColor);
            // 绘制路径
            path = new Path();
            // 创建空缓存图片
            if (cacheBitmap != null) {
                cacheBitmap.recycle();
            }
            cacheBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            // 把画布内容画到空缓存图片上
            cacheCanvas = new Canvas(cacheBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(canvasColor);
        canvas.drawBitmap(cacheBitmap, 0, 0, paint);
        canvas.drawPath(path, paint);
    }

    private static final float TOUCH_TOLERANCE = 4;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 记录手指摁下屏幕时的X坐标
        final float x = event.getX();
        // 记录手指摁下屏幕时的Y坐标
        final float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指摁下时清空之前的设置
                path.reset();
                // 设置路径起始点
                path.moveTo(x, y);
                xDown = x;
                yDown = y;
                isDrawing = true;
                break;
            case MotionEvent.ACTION_MOVE:
                pointCount++;
                float dx = Math.abs(x - xDown);
                float dy = Math.abs(y - xDown);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    path.quadTo(xDown, yDown, (x + xDown) / 2, (y + yDown) / 2);
                    xDown = x;
                    yDown = y;
                }
                isDrawing = true;
                break;
            case MotionEvent.ACTION_UP:
                painted = true;
                path.lineTo(xDown, yDown);
                // 手指抬起时绘制路径
                cacheCanvas.drawPath(path, paint);
                // 路径重置
                path.reset();
                isDrawing = false;
                break;
            default:
                break;
        }
        // 刷新界面
        invalidate();
        return true;
    }

    /**
     * 设置画笔颜色
     *
     * @param color 画笔颜色
     */
    public void setPaintColor(int color) {
        paintColor = color;
    }

    /**
     * 设置画板颜色
     *
     * @param color 画板颜色
     */
    public void setCanvasColor(int color) {
        canvasColor = color;
    }

    /**
     * 返回绘画状态
     *
     * @return【true:正在绘制】【false:绘制完成】
     */
    public boolean getDrawState() {
        return isDrawing;
    }

    /**
     * 返回Bitmap
     *
     * @return 返回绘制的图片
     */
    public Bitmap getBitmap() {
        return cacheBitmap;
    }

    /**
     * 是否合格的绘制
     *
     * @return
     */
    public boolean isValidPaint() {
        if (pointCount > 60) {
            return true;
        } else {
            return false;
        }
    }
}
