package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.AnalysisInterface;
import com.example.testdemolib.Listener.AnalysisListener;
import com.example.testdemolib.entity.request.PreTradeInfoReqModel;
import com.example.testdemolib.entity.respons.TradeInfoRespModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 16:17
 * E-Mail Address：1076790023@qq.com
 */

public class AnalysisImpl implements AnalysisInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, AnalysisListener analysisListener) {
        map.put("txnType", "74");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        TradeRequestImpl
                .getUnionPayPreTradeInfo((PreTradeInfoReqModel) TransMapToBeanUtils.mapToBean(map, PreTradeInfoReqModel.class))
                .subscribe(new ProgressSubscriber<TradeInfoRespModel>(context) {
                    @Override
                    protected void _onNext(TradeInfoRespModel responseModel) {
                        Logger.e("后台查询到的预交易信息:" + responseModel.toString());
                        analysisListener._onNext(responseModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        Logger.e("message:" + message);
                        analysisListener._onError(message);
                    }
                });
    }
}
