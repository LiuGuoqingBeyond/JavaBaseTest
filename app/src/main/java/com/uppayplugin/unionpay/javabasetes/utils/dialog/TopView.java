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

import com.bigkoo.pickerview.TimePickerView;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.activity.TopPopUpWindowActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/12 0012.
 */

public class TopView {
    Context context;
    private PopupWindow popupWindow;
    View popupWindowView;
    private TimePickerView pvTime;

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
        popupWindow.showAtLocation(LayoutInflater.from(context).inflate(R.layout.empty_layout, null),
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
        dealWithSelect();
    }


    /**
     * 处理点击事件
     */
    private void dealWithSelect(){
        popupWindowView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimss();
            }
        });
        popupWindowView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new BottomView(context);
                backgroundAlpha(0.7f);
                //弹出时间
                Calendar startDate = Calendar.getInstance();
                Calendar endDate = Calendar.getInstance();
                startDate.set(2010, 0, 1);
                pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(date);
                        String time = str.substring(0, 10);
                        ToastUtils.showLong(time);
                    }
                }).setType(new boolean[]{true, true, true, false, false, false})
                        .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                        .setRangDate(startDate, endDate)
                        .setDate(endDate)
                        .setCancelColor(R.color.colorAccent)//设置取消按钮字体颜色
                        .setTitleText("请选择日期")//主题
                        .setSubmitText("哈哈")//设置确定按钮颜色
                        .setSubmitColor(R.color.red)
//                        .setBackgroundId(R.color.app_background)
                        .build();
                pvTime.show();
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
