package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.BankTypeModel;

/**
 * Created by LiuGuoqing on 2018/7/17 0017 18:05
 * E-Mail Address：1076790023@qq.com
 */

public interface GetCardMessageListener {
    void _onNext(BankTypeModel bankTypeModel);
    void _onError(String error);
}
