package com.example.testdemolib.Impl;

import android.content.Context;
import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.CardListInterface;
import com.example.testdemolib.Listener.CardListListener;
import com.example.testdemolib.entity.request.GetBankCardReqModel;
import com.example.testdemolib.entity.respons.QueryCardListResponseModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 14:32
 * E-Mail Address：1076790023@qq.com
 */

public class CardListImpl implements CardListInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, CardListListener cardListListener) {
        map.put("txnType", "11");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", com.uppayplugin.unionpay.libcommon.rsa.RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        QueryRequest.getCardList((GetBankCardReqModel) TransMapToBeanUtils.mapToBean(map, GetBankCardReqModel.class))
                .subscribe(new ProgressSubscriber<QueryCardListResponseModel>(context) {
                    @Override
                    protected void _onNext(QueryCardListResponseModel queryCardListResponseModel) {
                        Logger.e("查询银行卡result:" + queryCardListResponseModel.toString());
                        cardListListener.getMessage(queryCardListResponseModel);
                        /*if (queryCardListResponseModel.isOk()) {
                            if (!TextUtils.isEmpty(queryCardListResponseModel.getTotalCredit()) && queryCardListResponseModel.getTotalCredit().equals("1")){
                                btnConfirm.setVisibility(View.GONE);
                            }else {
                                btnConfirm.setVisibility(View.VISIBLE);
                            }

                            if (queryCardListResponseModel.getList().size() > 0) {
                                accountNoBound.setVisibility(View.GONE);
                                bankcardlist = new Gson().toJson(queryCardListResponseModel);
                                prefs.writePrefs(Constant.PREFES_CARDCODELIST, bankcardlist);
                                prefs.writePrefs(Constant.PREFES_CARDCODELISTSIZE, queryCardListResponseModel.getList().size() + "");
                                bankInteradapter.clear();
//                                bankInteradapter.appendToList(queryCardListResponseModel.getList());//国际adapter
//                                queryCardListResponse = queryCardListResponseModel;
                                // FIXME: 2018/6/14 LGQ 区别信用卡和借记卡
                                betweenCard(queryCardListResponseModel.getList());
                            }

                        } else if (queryCardListResponseModel.getStatus().equals("2")) {
                            prefs.writePrefs(Constant.PREFES_CARDCODELIST, new Gson().toJson(queryCardListResponseModel));

                            prefs.writePrefs(Constant.PREFES_CARDCODELISTSIZE, "0");
                            bankInteradapter.clear();
                            accountNoBound.setVisibility(View.VISIBLE);
                            btnConfirm.setVisibility(View.VISIBLE);
                        } else if (queryCardListResponseModel.needLogin()) {
                            *//*DialogShowUtils.showReloginDailog(AccountBankCardActivity.this, queryCardListResponseModel.msg);*//*
                        } else {
                            *//*DialogShowUtils.showNoticeDialog(mContext,"",queryCardListResponseModel.getMsg(),getString(R.string.text_know),true);*//*
                        }*/
                    }

                    @Override
                    protected void _onError(String message) {
                        Logger.e("message" + message);
                       /* DialogShowUtils.showNoticeDialog(mContext,"",message,getString(R.string.text_know),false);*/
                    }
                });
    }
}
