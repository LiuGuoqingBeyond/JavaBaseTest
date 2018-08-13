package com.uppayplugin.unionpay.javabasetes.Impl;

import android.content.Context;
import android.widget.Toast;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 16:39
 */

public class Animal {
    private static Animal animal;
    private Animal(){}

    public static synchronized Animal getInstance(){
        if (animal == null){
            animal = new Animal();
        }

        return animal;
    }

    public void eat(Context context,String name, int age) {
        Toast.makeText(context,name+age+"",Toast.LENGTH_LONG).show();
    }

    public void sleep() {
        System.out.println("正在睡");
    }
}
