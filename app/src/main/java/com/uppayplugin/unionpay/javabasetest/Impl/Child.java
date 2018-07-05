package com.uppayplugin.unionpay.javabasetest.Impl;

import android.content.Context;
import android.widget.Toast;

/**
 * User: LiuGq
 * Date: 2018/4/13
 * Time: 9:59
 */

public class Child {
    private String name;
    private int age;
    private Context context;
    //构造方法
    public Child(Context context,String myname, int myage){
        this.name = myname;
        this.age = myage;
        this.context = context;
    }

    public void getMessage(){
        Toast.makeText(context,name+age+"",Toast.LENGTH_LONG).show();
    }
}
