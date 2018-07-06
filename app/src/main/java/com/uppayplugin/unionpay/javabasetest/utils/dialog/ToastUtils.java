package com.uppayplugin.unionpay.javabasetest.utils.dialog;

import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetest.MApplication;


/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/5/27
 * Time: 下午5:02
 */

public class ToastUtils {
    public static void showShort(String msg) {
        Toast.makeText(MApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(MApplication.getAppContext(), msg, Toast.LENGTH_LONG).show();
    }
}
