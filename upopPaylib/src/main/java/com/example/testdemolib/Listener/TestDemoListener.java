package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.GetTemsessionReqModel;

/**
 * Created by Administrator on 2018/7/6 0006.
 */

public interface TestDemoListener {
    void _onNext(GetTemsessionReqModel getTemsessionReqModel);
    void _onError(String error);
}
