package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.GetBoundCardMobileRespons;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 09:22
 * E-Mail Address：1076790023@qq.com
 */

public interface GetCodeListener {
    void _onNext(GetBoundCardMobileRespons getBoundCardMobileRespons);
    void _onError(String error);
}
