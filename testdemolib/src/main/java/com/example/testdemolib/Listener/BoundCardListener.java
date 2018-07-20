package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.BankCardMobel;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 10:05
 * E-Mail Address：1076790023@qq.com
 */

public interface BoundCardListener {
    void _onNext(BankCardMobel bankCardMobel);

    void _onError(String error);
}
