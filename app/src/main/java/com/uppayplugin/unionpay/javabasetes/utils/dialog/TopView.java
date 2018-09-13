package com.uppayplugin.unionpay.javabasetes.utils.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.uppayplugin.unionpay.javabasetes.R;

/**
 * Created by Administrator on 2018/9/12 0012.
 */

public class TopView {
    Context context;
    private PopupWindow popupWindow;
    View popupWindowView;


    public TopView(Context context){
        this.context=context;

        initPopupWindow();
    }

    /**
     * 初始化
     */
    public void initPopupWindow() {

        popupWindowView = LayoutInflater.from(context).inflate(R.layout.select_layout, null);
        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


        popupWindow.setAnimationStyle(R.style.TopSelectAnimationShow);
        // 菜单背景色。加了一点透明度
        ColorDrawable dw = new ColorDrawable(0xddffffff);
        popupWindow.setBackgroundDrawable(dw);

        //TODO 注意：这里的 R.layout.activity_main，不是固定的。你想让这个popupwindow盖在哪个界面上面。就写哪个界面的布局。这里以主界面为例
        popupWindow.showAtLocation(LayoutInflater.from(context).inflate(R.layout.activity_top_pop_up_window, null),
                Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);

        // 设置背景半透明
        backgroundAlpha(0.7f);

        popupWindow.setOnDismissListener(new popupDismissListener());

        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = popupWindowView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dimss();
                    }
                }
                return true;
            }
        });
//        dealWithSelect();
    }


    /**
     * 处理点击事件
     */
    private void dealWithSelect(){
        //点击了关闭图标（右上角图标）
        popupWindowView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimss();
            }
        });

        popupWindowView.findViewById(R.id.ll_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong( "点击了操作1");
            }
        });

        popupWindowView.findViewById(R.id.ll_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("点击了操作2");
            }
        });

        popupWindowView.findViewById(R.id.ll_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("点击了操作3");
            }
        });
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }

    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    public void dimss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}