package com.sinopaylib.listener;
import com.sinopaylib.entity.respons.TradeRecordListRepModel;

/**
 * Created by Administrator on 2018/8/3 0003.
 */

public interface TradeRecordListener {
    void _onNext(TradeRecordListRepModel tradeRecordListRepModel);
    void _onError(String error);
}
