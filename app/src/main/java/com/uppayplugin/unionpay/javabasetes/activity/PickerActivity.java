package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.bean.TypeBean;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.PickerUtil;

import java.util.ArrayList;

public class PickerActivity extends ToolBarActivity {

    private Button btnPicker;
    private ArrayList<TypeBean> mList = new ArrayList<TypeBean>();
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_picker;
    }

    @Override
    protected void initViewsAndEvents() {
        btnPicker = findViewById(R.id.btn_picker);

        // 单项选择
        for (int i = 0; i <= 20; i++) {
            mList.add(new TypeBean(i, "item" + i));
        }
        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseString();
            }
        });
    }

    private void chooseString() {
        PickerUtil.alertBottomWheelOption(PickerActivity.this, mList, new PickerUtil.OnWheelViewClick() {
            @Override
            public void onClick(View view, int postion) {
                Toast.makeText(PickerActivity.this, mList.get(postion).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
