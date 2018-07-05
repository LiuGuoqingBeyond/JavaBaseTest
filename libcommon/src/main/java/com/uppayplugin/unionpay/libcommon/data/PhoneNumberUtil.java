package com.uppayplugin.unionpay.libcommon.data;

import android.text.TextUtils;

/**
 * Created by chengxingjiang on 2017/11/8.
 */

public class PhoneNumberUtil {

    private static StringBuilder sb;

    public static String getPhoneNumber(String pNumber) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }


        }
        return sb.toString();
    }

    /**
     * 把电话号码变成 ***模式。
     *
     * @param pNumber
     *
     * @return
     */
    /*public static String getPhoneNumber(String pNumber) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() == 11) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 7) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return pNumber;
        }
    }*/
}
