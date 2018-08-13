package com.uppayplugin.unionpay.javabasetes.Impl;

import android.content.Context;

import com.uppayplugin.unionpay.javabasetes.Interface.TestFiveInter;
import com.uppayplugin.unionpay.javabasetes.Interface.TestFiveListener;

/**
 * User: LiuGq
 * Date: 2018/5/14
 * Time: 15:29
 */

public class TestFiveImpl implements TestFiveInter {
    @Override
    public void getMsg(Context context, TestFiveListener testFiveListener) {
        testFiveListener.sendMsg(context,"发送消息过去了");
    }
}
