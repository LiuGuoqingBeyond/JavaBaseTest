package com.uppayplugin.unionpay.javabasetest.Impl;

import android.util.Log;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 17:05
 */

public class Mouse extends Person {
    private String name;
    private int age;
    public Mouse(String myname, int myage) {
        super(myname, myage);
        this.name = myname;
        this.age = myage;
    }

    public void eat(){
        Log.d("eat",name+age+"");
    }
}
