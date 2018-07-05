package com.uppayplugin.unionpay.javabasetest.thread;

import android.util.Log;

import java.util.Random;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 11:28
 */

public class A extends Thread {
    int i = 1;
    Random r = new Random();

    public void run() {
        while (i < 100) {
            Log.d("奇数：", i + "");
            i += 2;
            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
