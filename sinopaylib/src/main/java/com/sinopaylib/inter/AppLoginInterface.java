package com.sinopaylib.inter;

import android.content.Context;

import com.sinopaylib.listener.AppLoginListener;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public interface AppLoginInterface {
    void appToLogin(Context context, Map<String, String> map, AppLoginListener appLoginListener);
}
