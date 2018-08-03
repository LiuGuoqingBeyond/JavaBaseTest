package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.Interface.RequestService;
import com.example.testdemolib.entity.respons.TradeRecordAllRespone;
import com.example.testdemolib.entity.respons.TradeRecordMonthRequest;

import io.reactivex.Flowable;

/**
 * User: chengxingjiang
 * Date: 2017/11/10
 * Time: 下午8:18
 */

public class TradeRecordRequest {

    public static Flowable<TradeRecordAllRespone> getTradeRecordAll(TradeRecordMonthRequest params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getTradeRecord(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    /*public static Flowable<TradeRecordMonthRespone> getTradeRecordMonth(TradeRecordMonthRequest params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getMonthTradeRecord(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    public static Flowable<QrCodePayInfoResponseModel> getTradeRecordNew(TradeRecordNewRequest params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getNewTradeRecord(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    public static Flowable<TradeRecordAllRespone> getTradeCardRecordAll(CardTreadRecordReqModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getCardTradeRecord(params)
                                .compose(RxSchedulersHelper.io_main()));
    }*/
}
