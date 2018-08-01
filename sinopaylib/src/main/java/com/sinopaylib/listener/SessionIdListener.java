package com.sinopaylib.listener;

import com.sinopaylib.entity.respons.GetTemsessionRepModel;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public interface SessionIdListener {
    void _onNext(GetTemsessionRepModel getTemsessionRepModel);
    void _onError(String error);
}
