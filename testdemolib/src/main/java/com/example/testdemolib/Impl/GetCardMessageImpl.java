package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.GetCardMessageInterface;
import com.example.testdemolib.Listener.GetCardMessageListener;
import com.example.testdemolib.entity.request.GetBankTypeReqModel;
import com.example.testdemolib.entity.respons.BankTypeModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/17 0017 18:08
 * E-Mail Address：1076790023@qq.com
 */

public class GetCardMessageImpl implements GetCardMessageInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, GetCardMessageListener getCardMessageListener) {
        map.put("txnType", "51");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        VerifyCardRequest.getBankType((GetBankTypeReqModel) TransMapToBeanUtils.mapToBean(map, GetBankTypeReqModel.class))
                .subscribe(new ProgressSubscriber<BankTypeModel>(context) {
                    @Override
                    protected void _onNext(BankTypeModel bankTypeModel) {
                        Logger.e("银行卡类型返回参数:" + bankTypeModel.toString());
                        getCardMessageListener._onNext(bankTypeModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        getCardMessageListener._onError(message);
                    }
                });
    }
}
