package com.example.testdemolib.Interface;

import android.content.Context;

import com.example.testdemolib.Listener.TestDemoListener;

/**
 * Created by Administrator on 2018/7/6 0006.
 */

public interface TestDemoInterface {
    void getMessage(Context context, String message, TestDemoListener testDemoListener);
}
