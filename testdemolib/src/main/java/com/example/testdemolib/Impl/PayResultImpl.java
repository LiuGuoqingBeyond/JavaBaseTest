package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.PayResultInterface;
import com.example.testdemolib.Listener.PayResultListener;
import com.example.testdemolib.entity.request.UnionPayByUpopReqModel;
import com.example.testdemolib.entity.respons.QrCodePayInfoResponseModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 17:11
 * E-Mail Address：1076790023@qq.com
 */

public class PayResultImpl implements PayResultInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, PayResultListener payResultListener) {
        map.put("txnType", "04");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));

        TradeRequestImpl
                .unionPayByUpopTrade((UnionPayByUpopReqModel) TransMapToBeanUtils.mapToBean(map, UnionPayByUpopReqModel.class))
                .subscribe(new ProgressSubscriber<QrCodePayInfoResponseModel>(context) {
                    @Override
                    protected void _onNext(QrCodePayInfoResponseModel qrCodePayInfoResponseModel) {
                        payResultListener._onNext(qrCodePayInfoResponseModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        payResultListener._onError(message);
                    }
                });
    }
}
