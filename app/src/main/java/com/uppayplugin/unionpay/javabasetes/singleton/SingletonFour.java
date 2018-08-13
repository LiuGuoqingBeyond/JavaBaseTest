package com.uppayplugin.unionpay.javabasetes.singleton;

import android.content.Context;
import android.widget.Toast;

/**
 * User: LiuGq
 * Date: 2018/5/7
 * Time: 15:10
 */

public class SingletonFour {

    private static SingletonFour singletonFour;

    public static synchronized SingletonFour getInstance(){
        if (singletonFour == null){
            singletonFour = new SingletonFour();
        }
        return singletonFour;
    }

    public void getMessage(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
