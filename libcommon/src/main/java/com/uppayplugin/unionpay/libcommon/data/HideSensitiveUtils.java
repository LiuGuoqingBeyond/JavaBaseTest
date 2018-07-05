package com.uppayplugin.unionpay.libcommon.data;

import android.text.TextUtils;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/15 21:05
 * @email：zhanghuan@xinguodu.com
 */
public class HideSensitiveUtils {

    /**
     * 隐藏证件号码(屏蔽规则,隐藏中间四位)
     *
     * @param cardInfo
     * @return   6-8
     */
    public static String getHidenSensitiveCard(String cardInfo) {
        if(cardInfo.length() > 14){
            cardInfo = cardInfo.substring(0,6) + "********" + cardInfo.substring(14,cardInfo.length());
        } else if(cardInfo.length() > 6 && cardInfo.length() <= 14) {
            cardInfo = cardInfo.substring(0,6) + returnSpiltString(cardInfo.length() - 6);
        }
        return cardInfo;
    }

    public static String returnSpiltString(int length){
        String siltString = "";
        if(length == 1) {
            siltString = "*";
        } else if(length == 2) {
            siltString = "**";
        }else if(length == 3) {
            siltString = "***";
        }else if(length == 4) {
            siltString = "****";
        }else if(length == 5) {
            siltString = "*****";
        }else if(length == 6) {
            siltString = "******";
        }else if(length == 7) {
            siltString = "*******";
        }else if(length == 8) {
            siltString = "********";
        }
        return siltString;
    }


    /**
     * 隐藏手机号码(屏蔽规则,隐藏中间四位)
     *
     * @param mobile
     * @return 3-4
     */
    public static String getHidenSensitiveMobile(String mobile) {
        // return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        if(TextUtils.isEmpty(mobile)) return "";
        if(mobile.contains("-")) {
            mobile = mobile.substring(mobile.indexOf("-") + 1,mobile.length());
        }
        if(mobile.length() > 7){
            mobile = mobile.substring(0,3) + "****" + mobile.substring(7,mobile.length());
        } else if(mobile.length() > 3 && mobile.length() <= 7) {
            mobile = mobile.substring(0,3) + returnSpiltString(mobile.length() - 3);
        }
        return mobile;
    }

    /**
     * 隐藏银行卡号后四位之前
     */

    public static String hindCardFourNumber(String cardNumber){
        String hindNumber = "****  ****  ****  "+cardNumber;
        return hindNumber;
    }
}
