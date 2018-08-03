package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.TradeInfoRespModel;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 16:12
 * E-Mail Address：1076790023@qq.com
 */

public interface AnalysisListener {
    void _onNext(TradeInfoRespModel tradeInfoRespModel);
    void _onError(String error);
}
