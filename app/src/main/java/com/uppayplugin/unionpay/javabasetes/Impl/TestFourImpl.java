package com.uppayplugin.unionpay.javabasetes.Impl;

import android.content.Context;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetes.Interface.TestFourListener;

/**
 * User: LiuGq
 * Date: 2018/5/14
 * Time: 15:10
 */

public class TestFourImpl implements TestFourListener {
    @Override
    public void getMessage(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
