package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.BoundCardInterface;
import com.example.testdemolib.Listener.BoundCardListener;
import com.example.testdemolib.entity.request.BoundCardModel;
import com.example.testdemolib.entity.respons.BankCardMobel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 10:07
 * E-Mail Address：1076790023@qq.com
 */

public class BoundCardImpl implements BoundCardInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, BoundCardListener boundCardListener) {
        map.put("txnType", "10");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        BoundCardRequest.getBoundCard((BoundCardModel) TransMapToBeanUtils.mapToBean(map, BoundCardModel.class))
                .subscribe(new ProgressSubscriber<BankCardMobel>(context) {
                    @Override
                    protected void _onNext(BankCardMobel bankCardMobel) {
                        Logger.e("绑定银行卡result:" + bankCardMobel.toString());
                        boundCardListener._onNext(bankCardMobel);
                    }

                    @Override
                    protected void _onError(String message) {
                        boundCardListener._onError(message);
                    }
                });
    }
}
