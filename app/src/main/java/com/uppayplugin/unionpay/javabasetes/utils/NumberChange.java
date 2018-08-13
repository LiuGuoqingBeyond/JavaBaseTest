package com.uppayplugin.unionpay.javabasetes.utils;

/**
 * User: LiuGq
 * Date: 2018/4/27
 * Time: 15:12
 */

public class NumberChange {

    private static NumberChange numberChange;
    private int str = 100;

    public static synchronized NumberChange getInstance() {
        if (null == numberChange) {
            numberChange = new NumberChange();
        }
        return numberChange;
    }

    public int number(int msg){
        return str - msg;
    }
}
