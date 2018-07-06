package com.example.testdemolib.Impl;

import android.content.Context;
import android.widget.Toast;

import com.example.testdemolib.Interface.TestDemoInterface;

/**
 * Created by Administrator on 2018/7/6 0006.
 */

public class TestDemoImpl implements TestDemoInterface{
    private Context mContext;
    private String message = "";

    @Override
    public void getMessage(Context context, String message) {
        this.mContext= context;
        this.message = message;
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
