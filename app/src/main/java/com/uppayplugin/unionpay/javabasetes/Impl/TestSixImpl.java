package com.uppayplugin.unionpay.javabasetes.Impl;

import android.content.Context;

import com.uppayplugin.unionpay.javabasetes.Interface.TestSixInter;
import com.uppayplugin.unionpay.javabasetes.Interface.TestSixListener;

/**
 * User: LiuGq
 * Date: 2018/5/14
 * Time: 16:55
 */

public class TestSixImpl implements TestSixInter {
    @Override
    public void getMsge(Context context, TestSixListener testSixListener) {
        testSixListener.senMessage(context,"接收到消息了吗？");
    }
}
