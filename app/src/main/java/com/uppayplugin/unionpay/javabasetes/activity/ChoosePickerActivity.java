package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.TimePickerView;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChoosePickerActivity extends ToolBarActivity {

    private Button btnTime;
    private TimePickerView pvTime;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_choose_picker;
    }

    @Override
    protected void initViewsAndEvents() {
        btnTime = findViewById(R.id.btn_time);
        btnTime.setOnClickListener(v -> openPickerView());
    }
    /**
     * 时间过滤器相关逻辑
     */
    private void openPickerView() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(2010, 0, 1);
        pvTime = new TimePickerView.Builder(this, (date, v) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf.format(date);
            String time = str.substring(0, 10);
            btnTime.setText(time);

        }).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .setRangDate(startDate, endDate)
                .setDate(endDate)
                .setCancelColor(R.color.colorAccent)//设置取消按钮字体颜色
                .setTitleText("请选择日期")//主题
                .setSubmitText("哈哈")//设置确定按钮颜色
                .setSubmitColor(R.color.red)
                .build();
        pvTime.show();

    }


    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
