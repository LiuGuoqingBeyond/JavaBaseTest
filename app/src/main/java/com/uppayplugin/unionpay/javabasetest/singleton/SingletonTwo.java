package com.uppayplugin.unionpay.javabasetest.singleton;

import android.content.Context;
import android.widget.Toast;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 14:43
 */

public class SingletonTwo {

    private static SingletonTwo singletonTwo;

    public static synchronized SingletonTwo getInstance(){
        if (singletonTwo == null){
            singletonTwo = new SingletonTwo();
        }
        return singletonTwo;
    }

    public void getNessage(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
