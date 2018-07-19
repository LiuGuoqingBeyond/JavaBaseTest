package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.api.TradeRequestService;
import com.example.testdemolib.entity.request.PreTradeInfoReqModel;
import com.example.testdemolib.entity.request.UnionPayByUpopReqModel;
import com.example.testdemolib.entity.respons.QrCodePayInfoResponseModel;
import com.example.testdemolib.entity.respons.TradeInfoRespModel;

import io.reactivex.Flowable;

/**
 * 交易接口实现类
 *
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/8 18:19
 * @email：zhanghuan@xinguodu.com
 */
public class TradeRequestImpl {

    /**
     * 获取银联国际码商户信息
     *
     * @param params
     * @return
     */
    public static Flowable<TradeInfoRespModel> getUnionPayPreTradeInfo(PreTradeInfoReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .getUnionPayPreTradeInfo(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    /**
     * 发起银联国际码付款交易
     *
     * @param params
     * @return
     *//*
    public static Flowable<QrCodePayInfoResponseModel> unionPayCodeTrade(UnionPayTradeReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .unionPayCodeTrade(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * 通过发起银联国际码付款进行UPOP交易
     *
     * @param params
     * @return
     */
    public static Flowable<QrCodePayInfoResponseModel> unionPayByUpopTrade(UnionPayByUpopReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .unionPayByUpopTrade(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    /**
     * 请求解析固定二维码
     *
     * @param params
     * @return
     *//*
    public static Flowable<TradeInfoRespModel> reqAnalyticFixedQrCode(ReqFixedZFQrCodeModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .reqAnalyticFixedQrCode(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * 请求解析非固定二维码
     *
     * @param params
     * @return
     *//*
    public static Flowable<TradeInfoRespModel> reqUnAnalyticFixedQrCode(ReqUnFixedZFQrCodeModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .reqAnalyticUnFixedQrCode(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * 非固定二维码发起获取费率接口
     *
     * @param params
     *
     * @return
     *//*
    public static Flowable<BillingRateRepMode> reqBillingRate(BillingRateReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .reqBillingRate(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * 非固定二维码发起获取下单接口
     *
     * @param params
     *
     * @return
     *//*
    public static Flowable<UnFixedCodePayRepModel> reqUnFixedCodePay(UnFixedCodePayReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .reqUnFixedCodePay(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * 中付固定二维码消费接口
     *
     * @param params
     * @return
     *//*
    public static Flowable<QrCodePayInfoResponseModel> zfFixedQrCodePay(ZFFixedQrCodePayReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .zfFixedQrCodePay(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * 交易结果查询接口
     *
     * @param params
     * @return
     *//*
    public static Flowable<QrCodePayInfoResponseModel> queryPayResult(QueryPayResultReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .queryPayResult(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * 交易结果查询接口
     *
     * @param params
     * @return
     *//*
    public static Flowable<QrCodePayInfoResponseModel> querySonyPayResult(QuerySonyPayResultReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .querySonyPayResult(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * http_url请求解析接口
     *
     * @param params
     * @return
     *//*
    public static Flowable<TradeInfoRespModel> requestAnalyHttpUrlCode(AnalyHttpUrlReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .requestAnalyHttpUrlCode(params)
                                .compose(RxSchedulersHelper.io_main()));
    }

    *//**
     * http_url请求支付接口
     *
     * @param params
     * @return
     *//*
    public static Flowable<QrCodePayInfoResponseModel> sonyCardUrlCodePay(SinoCardUrlPayReqModel params) {

        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(TradeRequestService.class)
                                .sonyCardUrlCodePay(params)
                                .compose(RxSchedulersHelper.io_main()));
    }*/
}
