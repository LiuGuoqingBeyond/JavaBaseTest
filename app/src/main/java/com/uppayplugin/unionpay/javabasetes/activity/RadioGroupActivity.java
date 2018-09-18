package com.uppayplugin.unionpay.javabasetes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.RadioGroup;
import com.whty.xzfpos.base.AppToolBarActivity;

import butterknife.BindView;

public class RadioGroupActivity extends AppToolBarActivity {
    @BindView(R.id.main_radio1)
    RadioGroup main_radio1;
    @BindView(R.id.radio_button3)
    RadioButton radio_button3;
    @BindView(R.id.radio_button4)
    RadioButton radio_button4;
    @BindView(R.id.radio_button5)
    RadioButton radio_button5;
    @BindView(R.id.radio_button6)
    RadioButton radio_button6;

    @BindView(R.id.tv_click)
    TextView tvClick;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_radio_group;
    }

    @Override
    protected void initViewsAndEvents() {
        main_radio1.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio_button3:
                    tvClick.setText("点击了1");
                    setResources(radio_button3, radio_button4, radio_button5, radio_button6);
                    break;
                case R.id.radio_button4:
                    tvClick.setText("点击了2");
                    setResources(radio_button4, radio_button3, radio_button5, radio_button6);
                    break;
                case R.id.radio_button5:
                    tvClick.setText("点击了3");
                    setResources(radio_button5, radio_button3, radio_button4, radio_button6);
                    break;
                case R.id.radio_button6:
                    tvClick.setText("点击了4");
                    setResources(radio_button6, radio_button3, radio_button4, radio_button5);
                    break;
            }
            RadioButton rb = RadioGroupActivity.this.findViewById(main_radio1.getCheckedRadioButtonId());
            CharSequence text = rb.getText();
        });
        selectRadioButton();
    }

    private void selectRadioButton() {

    }

    private void setResources(RadioButton radio_button3, RadioButton radio_button4, RadioButton radio_button5, RadioButton radio_button6) {
        radio_button3.setBackgroundResource(R.drawable.goods_attr_selected_shape);
        radio_button3.setTextColor(getResources().getColor(R.color.forget_pwd));
        radio_button4.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button4.setTextColor(Color.GRAY);
        radio_button5.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button5.setTextColor(Color.GRAY);
        radio_button6.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
        radio_button6.setTextColor(Color.GRAY);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }


}
