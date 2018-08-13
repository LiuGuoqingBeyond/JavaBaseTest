package com.uppayplugin.unionpay.javabasetes.singleton;

import android.content.Context;
import android.widget.Toast;

/**
 * User: LiuGq
 * Date: 2018/4/10
 * Time: 9:38
 * 简单的单例模式:饿汉式（常用）
 */

public class Singleton {
    private static Singleton singleton;
    public Singleton(){}
    public static synchronized Singleton getInstance(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public void toast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
}
