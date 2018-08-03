package com.example.testdemolib.Interface;

import android.content.Context;
import com.example.testdemolib.Listener.CardListListener;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 14:31
 * E-Mail Address：1076790023@qq.com
 */

public interface CardListInterface {
    void getMessage(Context context, Map<String, String> map, CardListListener cardListListener);
}
