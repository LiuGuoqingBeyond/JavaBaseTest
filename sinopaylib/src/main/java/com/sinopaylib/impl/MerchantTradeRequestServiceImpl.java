package com.sinopaylib.impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.sinopaylib.api.MerchantTradeRequestService;
import com.sinopaylib.entity.request.QrCodeReqModel;
import com.sinopaylib.entity.respons.QrCodeRepModel;

import io.reactivex.Flowable;

/**
 * @project：sinopay
 * @author：- octopus on 2017/12/8 15:50
 * @email：zhanghuan@xinguodu.com
 */
public class MerchantTradeRequestServiceImpl {


    /**
     * 查询当月交易列表信息
     * @param params
     * @return
     */
//    public static Flowable<TradeRecordListRepModel> requestTradeRecordByMonth(TradeRecordReqModel params) {
//
//        return Flowable.just(params)
//                .concatMap(stringStringMap ->
//                        HttpEngine.getInstance()
//                                .createServices(MerchantTradeRequestService.class)
//                                .requestTradeRecordByMonth(params)
//                                .compose(RxSchedulersHelper.io_main()));
//    }
//
//    /**
//     * 查询交易详情信息
//     * @param params
//     * @return
//     */
//    public static Flowable<TradeRecordDetailRepModel> requestTradeDetailInfo(TradeRecordDetailReqModel params) {
//
//        return Flowable.just(params)
//                .concatMap(stringStringMap ->
//                        HttpEngine.getInstance()
//                                .createServices(MerchantTradeRequestService.class)
//                                .requestTradeDetailInfo(params)
//                                .compose(RxSchedulersHelper.io_main()));
//    }
//
    /**
     * 请求查询二维码接口
     * @param params
     * @return
     */
    public static Flowable<QrCodeRepModel> requestGenerateQRCode(QrCodeReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(MerchantTradeRequestService.class)
                                .requestGenerateQRCode(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
//
//    /**
//     * 请求撤销交易
//     * @param params
//     * @return
//     */
//    public static Flowable<RevokeTradeRepModel> requestRevokeTrade(RevokeTradeReqModel params) {
//
//        return Flowable.just(params)
//                .concatMap(stringStringMap ->
//                        HttpEngine.getInstance()
//                                .createServices(MerchantTradeRequestService.class)
//                                .requestRevokeTrade(params)
//                                .compose(RxSchedulersHelper.io_main()));
//    }
//
//    /**
//     * 请求极光推送通知
//     * @param params
//     * @return
//     */
//    public static Flowable<BaseRepModel> requestJPushNotice(JPushNoticeReqModel params) {
//
//        return Flowable.just(params)
//                .concatMap(stringStringMap ->
//                        HttpEngine.getInstance()
//                                .createServices(MerchantTradeRequestService.class)
//                                .requestJPushNotice(params)
//                                .compose(RxSchedulersHelper.io_main()));
//    }

}
