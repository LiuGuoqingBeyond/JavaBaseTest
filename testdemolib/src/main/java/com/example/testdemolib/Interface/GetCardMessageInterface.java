package com.example.testdemolib.Interface;

import android.content.Context;
import com.example.testdemolib.Listener.GetCardMessageListener;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/17 0017 18:06
 * E-Mail Address：1076790023@qq.com
 */

public interface GetCardMessageInterface {
    void getMessage(Context context, Map<String, String> map, GetCardMessageListener getCardMessageListener);
}
