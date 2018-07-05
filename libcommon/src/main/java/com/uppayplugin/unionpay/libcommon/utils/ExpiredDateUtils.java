package com.uppayplugin.unionpay.libcommon.utils;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/12/26 17:27
 * @email：zhanghuan@xinguodu.com
 */
public class ExpiredDateUtils {

    public static String getEtDataText(String etDataString) {
        String edDataText = "";
        try {
            if(!TextUtils.isEmpty(etDataString) && etDataString.length() >=4) {
                edDataText = etDataString.substring(0,4);
                String preString = edDataText.substring(0,2);
                String lastString = edDataText.substring(2,4);
                edDataText = lastString + preString;
            } else {
                edDataText = etDataString;
            }
        } catch (Exception e) {
            edDataText = etDataString;
            Logger.e(e.getMessage());
        }
        return edDataText;
    }

}
