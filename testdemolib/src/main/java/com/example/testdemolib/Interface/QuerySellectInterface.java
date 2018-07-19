package com.example.testdemolib.Interface;

import android.content.Context;

import com.example.testdemolib.Listener.BoundCardListener;
import com.example.testdemolib.Listener.QuerySellectListener;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 10:05
 * E-Mail Address：1076790023@qq.com
 */

public interface QuerySellectInterface {
    void getMessage(Context context, Map<String, String> map, QuerySellectListener querySellectListener);
}
