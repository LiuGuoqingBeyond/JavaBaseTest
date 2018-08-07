package com.sinopaylib.inter;

import android.content.Context;

import com.sinopaylib.listener.MerChantInfoListener;
import com.sinopaylib.listener.SessionIdListener;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public interface MerChantInter {
    void getMerChantInfo(Context context, Map<String, String> map, MerChantInfoListener merChantInfoListener);
}
