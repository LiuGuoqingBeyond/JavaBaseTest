package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.QuerySellectInterface;
import com.example.testdemolib.Listener.QuerySellectListener;
import com.example.testdemolib.entity.respons.TradeRecordAllRespone;
import com.example.testdemolib.entity.respons.TradeRecordMonthRequest;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 14:48
 * E-Mail Address：1076790023@qq.com
 */

public class QuerySellectImpl implements QuerySellectInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, QuerySellectListener querySellectListener) {
        map.put("txnType", "12");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));

        TradeRecordRequest.getTradeRecordAll((TradeRecordMonthRequest) TransMapToBeanUtils.mapToBean(map, TradeRecordMonthRequest.class))
                .subscribe(new ProgressSubscriber<TradeRecordAllRespone>(context) {
                    @Override
                    protected void _onNext(TradeRecordAllRespone uPlanModelRespones) {
                        querySellectListener._onNext(uPlanModelRespones);
                    }

                    @Override
                    protected void _onError(String message) {
                        querySellectListener._onError(message);
                    }
                });

    }
}
