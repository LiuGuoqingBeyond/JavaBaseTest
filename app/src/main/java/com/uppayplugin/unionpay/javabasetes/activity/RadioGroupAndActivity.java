package com.uppayplugin.unionpay.javabasetes.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.whty.xzfpos.base.AppToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RadioGroupAndActivity extends AppToolBarActivity {
    @BindView(R.id.radio_button1)
    RadioButton radio_button1;
    @BindView(R.id.radio_button2)
    RadioButton radio_button2;
    @BindView(R.id.switch1)
    Switch switch1;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_radio_group_and;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick({R.id.radio_button1, R.id.radio_button2, R.id.switch1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radio_button1:
                radio_button1.setBackgroundResource(R.drawable.rado_unselected_shape_left_black);
                radio_button1.setTextColor(Color.WHITE);

                radio_button2.setBackgroundResource(R.drawable.rado_unselected_shape_right);
                radio_button2.setTextColor(Color.BLACK);
                break;
            case R.id.radio_button2:
                radio_button1.setBackgroundResource(R.drawable.rado_unselected_shape_left);
                radio_button1.setTextColor(Color.BLACK);

                radio_button2.setBackgroundResource(R.drawable.rado_unselected_shape_right_black);
                radio_button2.setTextColor(Color.WHITE);
                break;
            case R.id.switch1:
                if (switch1.isChecked()) {
                    ToastUtils.showLong("打开");
                } else {
                    ToastUtils.showLong("关闭");
                }
                break;
        }
    }
}
