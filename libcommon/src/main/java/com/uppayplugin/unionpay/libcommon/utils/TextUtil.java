package com.uppayplugin.unionpay.libcommon.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;


/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/6/14
 * Time: 下午7:21
 */

public class TextUtil {
    /**
     * 设置文字
     *
     * @param text 要设置的文字
     * @param temp 要设置的占位文字
     */
    public static String setText(String text, String temp, boolean isName) {
        if (isName)
            return TextUtils.isEmpty(text) ? TextUtils.isEmpty(temp) ? "" : temp : text.length() > 8 ? text.substring(0, 8) : text;
        else return TextUtils.isEmpty(text) ? TextUtils.isEmpty(temp) ? "" : temp : text;
    }

    /**
     * 设置文字
     *
     * @param text 要设置的文字
     */
    public static String setText(String text, String temp) {
        return setText(text, temp, false);
    }

    /**
     * 设置文字
     *
     * @param text 要设置的文字
     */
    public static String setText(String text) {
        return setText(text, "", false);
    }

    public static double setTextByNum(String text) {
        if (!TextUtils.isEmpty(text) && TextUtils.isDigitsOnly(text) && Integer.valueOf(text) > 0)
            return Double.valueOf(text);
        else return 0.0;
    }

    public static String setTextByNum(String text, String add, String temp) {
        if (!TextUtils.isEmpty(text) && TextUtils.isDigitsOnly(text) && Integer.valueOf(text) > 0)
            return String.valueOf(text + add);
        else return temp;
    }

    public static String setTextFormatNum(String text) {
        return setTextFormatNum(text, false);
    }

    public static String setTextFormatNum(String text, boolean needExcept100) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Double.valueOf(setText(text, "0")) / (needExcept100 ? 100 : 1));
    }


}
