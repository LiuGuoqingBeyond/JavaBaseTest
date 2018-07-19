package com.example.testdemolib.api;
import com.example.testdemolib.Interface.Constans;
import com.example.testdemolib.entity.request.PreTradeInfoReqModel;
import com.example.testdemolib.entity.request.UnionPayByUpopReqModel;
import com.example.testdemolib.entity.respons.QrCodePayInfoResponseModel;
import com.example.testdemolib.entity.respons.TradeInfoRespModel;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 交易类相关接口
 *
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/8 15:42
 * @email：zhanghuan@xinguodu.com
 */
public interface TradeRequestService {

    // 国际二维码获取商户信息接口
    @POST(Constans.BASEURL)
    Flowable<TradeInfoRespModel> getUnionPayPreTradeInfo(@Body PreTradeInfoReqModel preTradeInfoReqModel);

//    // 国际二维码支付接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<QrCodePayInfoResponseModel> unionPayCodeTrade(@Body UnionPayTradeReqModel unionPayTradeReqModel);

    // 银联二维码做upop交易
    @POST(Constans.URL_VIRTUAL_APP_SERVICE_REQUEST)
    Flowable<QrCodePayInfoResponseModel> unionPayByUpopTrade(@Body UnionPayByUpopReqModel unionPayTradeReqModel);

//    // 请求解析中付固定二维码接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<TradeInfoRespModel> reqAnalyticFixedQrCode(@Body ReqFixedZFQrCodeModel reqFixedZFQrCodeModel);
//
//    // 请求解析中付非固定二维码接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<TradeInfoRespModel> reqAnalyticUnFixedQrCode(@Body ReqUnFixedZFQrCodeModel reqUnFixedZFQrCodeModel);
//
//    // 中付非固定二维码请求费率接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<BillingRateRepMode> reqBillingRate(@Body BillingRateReqModel billingRateReqModel);
//
//    // 中付非固定二维码请求下单接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<UnFixedCodePayRepModel> reqUnFixedCodePay(@Body UnFixedCodePayReqModel unFixedCodePayReqModel);
//
//    // 中付固定二维码消费接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<QrCodePayInfoResponseModel> zfFixedQrCodePay(@Body ZFFixedQrCodePayReqModel zfFixedQrCodePayReqModel);
//
//    // 交易结果查询接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<QrCodePayInfoResponseModel> queryPayResult(@Body QueryPayResultReqModel queryPayResultReqModel);
//
//    // 国际二维码交易结果查询接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<QrCodePayInfoResponseModel> querySonyPayResult(@Body QuerySonyPayResultReqModel querySonyPayResultReqModel);
//
//    // HttpUrl二维码解析接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<TradeInfoRespModel> requestAnalyHttpUrlCode(@Body AnalyHttpUrlReqModel analyHttpUrlReqModel);
//
//    // HttpUrl二维码解析接口
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<QrCodePayInfoResponseModel> sonyCardUrlCodePay(@Body SinoCardUrlPayReqModel sinoCardUrlPayReqModel);


}
