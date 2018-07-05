package com.uppayplugin.unionpay.libcommon.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUitl {

    /**
     * 格式化日期时间
     *
     * @param date
     * @param time
     * @return
     */
    public static String formatDateTime(String date, String time) {
       // Date now = new Date();
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String hh = time.substring(0, 2);
        String mm = time.substring(2, 4);
        String ss = time.substring(4, 6);

        //SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");
        //formatDate.format(now)
        //
        return year+ "/" + month + "/" + day + " " + hh
                + ":" + mm + ":" + ss;
    }

    public static String formatDate(String date) {
        Date now = new Date();

        String month = date.substring(0, 2);
        String day = date.substring(2, 4);

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");

        return formatDate.format(now) + "/" + month + "/" + day;
    }

    public static String formatDateYYYYMMDD(String date) {
        Date now = new Date();

        String month = date.substring(0, 2);
        String day = date.substring(2, 4);

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy");

        return formatDate.format(now) + month + day;
    }

    public static String formatTime(String time) {
        Date now = new Date();

        String hh = time.substring(0, 2);
        String mm = time.substring(2, 4);
        String ss = time.substring(4, 6);

        return hh + ":" + mm + ":" + ss;
    }

}
