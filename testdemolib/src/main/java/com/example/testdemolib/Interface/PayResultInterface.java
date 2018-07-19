package com.example.testdemolib.Interface;

import android.content.Context;
import com.example.testdemolib.Listener.PayResultListener;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 17:10
 * E-Mail Address：1076790023@qq.com
 */

public interface PayResultInterface {
    void getMessage(Context context, Map<String, String> map, PayResultListener payResultListener);
}
