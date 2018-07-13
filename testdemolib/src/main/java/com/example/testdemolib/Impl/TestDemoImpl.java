package com.example.testdemolib.Impl;

import android.content.Context;

import com.example.testdemolib.Interface.TestDemoInterface;
import com.example.testdemolib.Listener.TestDemoListener;

/**
 * Created by Administrator on 2018/7/6 0006.
 */

public class TestDemoImpl implements TestDemoInterface{
    private Context mContext;
    private String message = "";
    private TestDemoListener testDemoListener;

    @Override
    public void getMessage(Context context, String message, TestDemoListener testDemoListener) {
        this.mContext= context;
        this.message = message;
        this.testDemoListener = testDemoListener;
        testDemoListener.getMessage("这个是回调信息");
    }
}
