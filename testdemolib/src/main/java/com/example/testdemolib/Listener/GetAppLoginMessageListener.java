package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.LoginAppReqModel;

/**
 * Created by LiuGuoqing on 2018/7/17 0017 16:33
 * E-Mail Address：1076790023@qq.com
 */

public interface GetAppLoginMessageListener {
    void _onNext(LoginAppReqModel loginAppReqModel);
    void _onError(String error);
}
