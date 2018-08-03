package com.sinopaylib.api;
import com.sinopaylib.entity.request.QrCodeReqModel;
import com.sinopaylib.entity.request.TradeRecordReqModel;
import com.sinopaylib.entity.respons.QrCodeRepModel;
import com.sinopaylib.entity.respons.TradeRecordListRepModel;
import com.sinopaylib.inter.URLConstants;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @project：sinopay
 * @author：- octopus on 2017/12/8 15:16
 * @email：zhanghuan@xinguodu.com
 */
public interface MerchantTradeRequestService {

    // 查询当月交易列表信息
    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
    Flowable<TradeRecordListRepModel> requestTradeRecordByMonth(@Body TradeRecordReqModel tradeRecordReqModel);

//    // 查询交易详情信息
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<TradeRecordDetailRepModel> requestTradeDetailInfo(@Body TradeRecordDetailReqModel tradeRecordDetailReqModel);
//
    // 请求查询二维码
    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
    Flowable<QrCodeRepModel> requestGenerateQRCode(@Body QrCodeReqModel qrCodeReqModel);
//
//    // 请求撤销交易
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<RevokeTradeRepModel> requestRevokeTrade(@Body RevokeTradeReqModel revokeTradeReqModel);
//
//    // 请求极光推送通知
//    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
//    Flowable<BaseRepModel> requestJPushNotice(@Body JPushNoticeReqModel jPushNoticeReqModel);
}
