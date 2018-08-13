package com.uppayplugin.unionpay.javabasetes.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.uppayplugin.unionpay.javabasetes.R;


/**
 * @author King_wangyao
 * @version 1.0.0
 * @description EditText带删除图标的样式及功能（自定义带有删除功能）
 * @date 2014-7-24
 */
@SuppressLint("AppCompatCustomView")
public class EditTextWithDEL extends EditText {

    private final static String TAG = EditTextWithDEL.class.getSimpleName();

    private Drawable imgAble;
    private Context mContext;

    public EditTextWithDEL(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDEL(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public EditTextWithDEL(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        imgAble = mContext.getResources().getDrawable(R.drawable.icon_delete);
        addTextChangedListener(new TextWatcher() {

            // 文字变化后
            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }

            // 文字变化前
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            // 文字变化时
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        setDrawable();
    }

    /**
     * 设置删除图片
     */
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            // FIXME: 2018/1/11 LGQ 添加了边距,拉开些间距
            setCompoundDrawablePadding(16);
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
        }
    }

    /**
     * 处理删除事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 130;
            if (rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
