package com.uppayplugin.unionpay.javabasetes.Impl;

import android.content.Context;

import com.uppayplugin.unionpay.javabasetes.Interface.TestSevenInter;
import com.uppayplugin.unionpay.javabasetes.Interface.TextSevenListener;

/**
 * User: LiuGq
 * Date: 2018/6/5
 * Time: 16:37
 */

public class TestSevenImpl implements TestSevenInter {
    @Override
    public void sendMessage(Context context, TextSevenListener textSevenListener) {
        textSevenListener.getSevenMessage(context,"哈哈");
    }
}
