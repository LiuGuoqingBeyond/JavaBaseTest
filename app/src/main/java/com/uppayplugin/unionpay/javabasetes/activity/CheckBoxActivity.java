package com.uppayplugin.unionpay.javabasetes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckBoxActivity extends AppToolBarActivity {
    @BindView(R.id.checkbox1)
    CheckBox checkbox1;
    @BindView(R.id.checkbox2)
    CheckBox checkbox2;
    @BindView(R.id.button2)
    Button button2;

    int num = 0;
    private List<CheckBox> checkBoxList;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_check_box;
    }

    @Override
    protected void initViewsAndEvents() {
        checkBoxList = new ArrayList<>();
        checkBoxList.add(checkbox1);
        checkBoxList.add(checkbox2);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick({R.id.checkbox1, R.id.checkbox2, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkbox1:
                if (checkbox1.isChecked()) {
                    checkbox1.setBackgroundResource(R.drawable.goods_attr_selected_shape);
                    checkbox1.setTextColor(getResources().getColor(R.color.forget_pwd));
                } else {
                    checkbox1.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
                    checkbox1.setTextColor(Color.GRAY);
                }
                break;
            case R.id.checkbox2:
                if (checkbox2.isChecked()) {
                    checkbox2.setBackgroundResource(R.drawable.goods_attr_selected_shape);
                    checkbox2.setTextColor(getResources().getColor(R.color.forget_pwd));
                } else {
                    checkbox2.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
                    checkbox2.setTextColor(Color.GRAY);
                }
                break;
            case R.id.button2:
                StringBuffer sb = new StringBuffer();
                //遍历集合中的checkBox,判断是否选择，获取选中的文本
                for (CheckBox checkbox : checkBoxList) {
                    if (checkbox.isChecked()) {
                        sb.append(checkbox.getText().toString());
                    }
                }
                if (sb != null && "".equals(sb.toString())) {
                    ToastUtils.showLong("请至少选择一个");
                } else {
                    ToastUtils.showLong(sb.toString());
                }
                break;
        }
    }
}
