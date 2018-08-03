package com.sinopaylib.impl;

import android.content.Context;
import android.view.View;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.orhanobut.logger.Logger;
import com.sinopaylib.entity.request.LoginAppModel;
import com.sinopaylib.entity.request.TradeRecordReqModel;
import com.sinopaylib.entity.respons.LoginAppRepModel;
import com.sinopaylib.entity.respons.TradeRecordListRepModel;
import com.sinopaylib.inter.TradeRecordInter;
import com.sinopaylib.listener.TradeRecordListener;
import com.sinopaylib.mapbean.TransMapToBeanUtils;
import com.sinopaylib.utils.PayUtils;
import com.sinopaylib.utils.RSACoder;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/3 0003.
 */

public class TradeRecordImpl implements TradeRecordInter {
    @Override
    public void getTradeRecord(Context context, Map<String, String> map, TradeRecordListener tradeRecordListener) {
        map.put("txnType", "09");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes()).replaceAll("\n\r", ""));
        MerchantTradeRequestServiceImpl.requestTradeRecordByMonth((TradeRecordReqModel) TransMapToBeanUtils.mapToBean(map, TradeRecordReqModel.class))
                .subscribe(new ProgressSubscriber<TradeRecordListRepModel>(context) {
                    @Override
                    protected void _onNext(TradeRecordListRepModel tradeRecordListRepModel) {
                        tradeRecordListener._onNext(tradeRecordListRepModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        tradeRecordListener._onError(message);
                    }
                });
    }
}
