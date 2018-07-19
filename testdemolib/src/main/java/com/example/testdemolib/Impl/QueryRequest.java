package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.Interface.RequestService;
import com.example.testdemolib.entity.request.GetBankCardReqModel;
import com.example.testdemolib.entity.respons.QueryCardListResponseModel;

import io.reactivex.Flowable;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 14:41
 * E-Mail Address：1076790023@qq.com
 */

public class QueryRequest {
    public static Flowable<QueryCardListResponseModel> getCardList(GetBankCardReqModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getCashireList(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
