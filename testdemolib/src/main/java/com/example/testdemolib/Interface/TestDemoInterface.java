package com.example.testdemolib.Interface;

import android.content.Context;

import com.example.testdemolib.Listener.TestDemoListener;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/6 0006.
 */

public interface TestDemoInterface {
    void getMessage(Context context, Map<String, String> map, TestDemoListener testDemoListener);
}
