package com.example.testdemolib.Interface;

import android.content.Context;

import com.example.testdemolib.Listener.AnalysisListener;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 16:11
 * E-Mail Address：1076790023@qq.com
 */

public interface AnalysisInterface {
    void getMessage(Context context, Map<String, String> map, AnalysisListener analysisListener);
}
