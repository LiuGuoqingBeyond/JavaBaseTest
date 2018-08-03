package com.sinopaylib.inter;

import android.content.Context;

import com.sinopaylib.entity.respons.TradeRecordListRepModel;
import com.sinopaylib.listener.TradeRecordListener;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/3 0003.
 */

public interface TradeRecordInter {
    void getTradeRecord(Context context, Map<String,String> map,TradeRecordListener tradeRecordListener);
}
