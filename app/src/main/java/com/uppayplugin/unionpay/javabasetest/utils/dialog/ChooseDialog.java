package com.uppayplugin.unionpay.javabasetest.utils.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.uppayplugin.unionpay.javabasetest.R;


/**
 * @author King_wangyao
 * @version 1.0.0
 * @description 选择相册或拍照选项Dialog
 * @date 2014-5-20
 */
public class ChooseDialog extends Dialog implements OnClickListener {

    public ChooseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_choose_dialog);

        Button mPhoto = (Button) this.findViewById(R.id.btn_photo);
        Button mPhone = (Button) this.findViewById(R.id.btn_phone);
        Button mCancel = (Button) this.findViewById(R.id.btn_cancel);

        mPhoto.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_photo:
                doGetValue(1);
                break;
            case R.id.btn_phone:
                doGetValue(2);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    public int doGetValue(int value) {
        return value;
    }
}
