package com.uppayplugin.unionpay.javabasetest.Impl;

import android.content.Context;

import com.uppayplugin.unionpay.javabasetest.Interface.GetInter;
import com.uppayplugin.unionpay.javabasetest.Interface.TestInterface;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 18:10
 */

public class InterImpl implements GetInter {

    private Context context;
    private TestInterface testInterface;
    private String message;

    public InterImpl() {
    }

    @Override
    public void getMsg(Context context, String Message, TestInterface testInterface) {
        this.context = context;
        this.testInterface = testInterface;
        this.message = Message;

        testInterface.onResult(message);
    }
}
