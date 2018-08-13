package com.uppayplugin.unionpay.javabasetes.singleton;

import android.content.Context;
import android.widget.Toast;

/**
 * User: LiuGq
 * Date: 2018/4/24
 * Time: 9:09
 */

public class SingletonThree {

    private static SingletonThree singletonThree;

    public static synchronized SingletonThree getInstance(){
        if (singletonThree == null){
            singletonThree = new SingletonThree();
        }
        return singletonThree;
    }

    public void testSingleton(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
