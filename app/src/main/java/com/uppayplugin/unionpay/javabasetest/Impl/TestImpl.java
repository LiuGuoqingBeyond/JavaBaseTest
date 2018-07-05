package com.uppayplugin.unionpay.javabasetest.Impl;

import android.content.Context;

import com.uppayplugin.unionpay.javabasetest.Interface.GetInterface;
import com.uppayplugin.unionpay.javabasetest.Interface.TestInter;

/**
 * User: LiuGq
 * Date: 2018/4/24
 * Time: 8:53
 */

public class TestImpl implements GetInterface {
    private Context context;
    private String message;
    private TestInter testInter;
    public TestImpl(){}
    @Override
    public void getMessage(Context context, String message, TestInter testInter) {
        this.context = context;
        this.message = message;
        this.testInter = testInter;

        testInter.onResult(message);
    }
}
