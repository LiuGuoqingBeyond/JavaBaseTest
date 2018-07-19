package com.example.testdemolib.Interface;

import android.content.Context;
import com.example.testdemolib.Listener.GetCodeListener;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 09:22
 * E-Mail Address：1076790023@qq.com
 */

public interface GetCodeInterface {
    void getMessage(Context context, Map<String, String> map, GetCodeListener getCodeListener);
}
