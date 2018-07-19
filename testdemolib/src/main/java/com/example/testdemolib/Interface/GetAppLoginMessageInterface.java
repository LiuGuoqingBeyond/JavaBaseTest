package com.example.testdemolib.Interface;

import android.content.Context;

import com.example.testdemolib.Listener.GetAppLoginMessageListener;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/17 0017 16:38
 * E-Mail Address：1076790023@qq.com
 */

public interface GetAppLoginMessageInterface {
    void getMessage(Context context, Map<String, String> map, GetAppLoginMessageListener getAppLoginMessageListener);
}
