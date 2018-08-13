package com.uppayplugin.unionpay.javabasetes.thread;

import android.util.Log;

import java.util.Random;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 11:30
 */

public class B implements Runnable {
    int i = 2;
    Random r = new Random();

    @Override
    public void run() {
        while (i <= 100) {
            Log.d("偶数：", i + "");
            i += 2;
            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
