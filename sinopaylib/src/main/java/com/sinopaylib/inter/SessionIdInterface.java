package com.sinopaylib.inter;

import android.content.Context;

import com.sinopaylib.listener.SessionIdListener;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public interface SessionIdInterface {
    void getSessionId(Context context, Map<String, String> map, SessionIdListener sessionIdListener);
}
