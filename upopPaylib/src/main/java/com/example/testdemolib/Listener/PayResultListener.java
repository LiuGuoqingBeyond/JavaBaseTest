package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.QrCodePayInfoResponseModel;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 17:09
 * E-Mail Address：1076790023@qq.com
 */

public interface PayResultListener {
    void _onNext(QrCodePayInfoResponseModel qrCodePayInfoResponseModel);
    void _onError(String error);
}
