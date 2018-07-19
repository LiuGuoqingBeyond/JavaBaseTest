package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.PayResultInterface;
import com.example.testdemolib.Listener.PayResultListener;
import com.example.testdemolib.entity.request.UnionPayByUpopReqModel;
import com.example.testdemolib.entity.respons.QrCodePayInfoResponseModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/19 0019 17:11
 * E-Mail Address：1076790023@qq.com
 */

public class PayResultImpl implements PayResultInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, PayResultListener payResultListener) {
        map.put("txnType", "04");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", com.uppayplugin.unionpay.libcommon.rsa.RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        TradeRequestImpl
                .unionPayByUpopTrade((UnionPayByUpopReqModel) TransMapToBeanUtils.mapToBean(map, UnionPayByUpopReqModel.class))
                .subscribe(new ProgressSubscriber<QrCodePayInfoResponseModel>(context) {
                    @Override
                    protected void _onNext(QrCodePayInfoResponseModel responseModel) {
                        Logger.e("后台查询到的交易详情信息:" + responseModel.toString());
                        payResultListener.getMessage(responseModel.toString());
                        /*if(responseModel.getStatus().equals("3")){
                            // 交易状态不明确
                            haveTryRequest = 1;

                            tradeInfoRespModel.setOrderId(responseModel.getOrderId());
                            tradeInfoRespModel.setBillingAmt(responseModel.getBillingAmt());
                            tradeInfoRespModel.setBillingCurr(responseModel.getBillingCurr());
                            tradeInfoRespModel.setMerId(responseModel.getMerId());
                            tradeInfoRespModel.setBillingRate(responseModel.getBillingRate());

                            queryPayResult(true);
                        } else if(responseModel.isOk()) {
                            // 消费成功，跳转到订单详情页面
                            // zhanghuan 国际码赋值

                                *//*tradeInfoRespModel.setOrderId(responseModel.getOrderId());
                                tradeInfoRespModel.setBillingAmt(responseModel.getBillingAmt());
                                tradeInfoRespModel.setBillingCurr(responseModel.getBillingCurr());
                                tradeInfoRespModel.setMerId(responseModel.getMerId());
                                tradeInfoRespModel.setBillingRate(responseModel.getBillingRate());*//*

                            paySuccess(responseModel);
                        } else if(responseModel.needLogin()) {
                            DialogShowUtils.showReloginDailog(mContext,responseModel.msg);
                        } else if(responseModel.getStatus().equals("55")) {
                            editSecurityCode.setText("");
                            DialogShowUtils.showNoticeDialog(mContext,"",responseModel.msg,getString(R.string.text_know),false);
                        } else if(responseModel.getStatus().equals("64")) {
                            cardStatus = "1";
                            DialogShowUtils.showNoticeDialog(mContext,"",responseModel.msg,getString(R.string.text_know),false);
                        } else {
                            // 支付失败
                            payFailed(responseModel.msg);
                        }*/
                    }

                    @Override
                    protected void _onError(String message) {
                        // 支付失败
//                        DialogShowUtils.showNoticeDialog(mContext,"",message,getString(R.string.text_know),false);
                    }
                });
    }
}
