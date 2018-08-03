package com.example.testdemolib.Impl;

import android.content.Context;
import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.CardListInterface;
import com.example.testdemolib.Listener.CardListListener;
import com.example.testdemolib.entity.request.GetBankCardReqModel;
import com.example.testdemolib.entity.respons.QueryCardListResponseModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 14:32
 * E-Mail Address：1076790023@qq.com
 */

public class CardListImpl implements CardListInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, CardListListener cardListListener) {
        map.put("txnType", "11");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        QueryRequest.getCardList((GetBankCardReqModel) TransMapToBeanUtils.mapToBean(map, GetBankCardReqModel.class))
                .subscribe(new ProgressSubscriber<QueryCardListResponseModel>(context) {
                    @Override
                    protected void _onNext(QueryCardListResponseModel queryCardListResponseModel) {
                        Logger.e("查询银行卡result:" + queryCardListResponseModel.toString());
                        cardListListener._onNext(queryCardListResponseModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        cardListListener._onError(message);
                    }
                });
    }
}
