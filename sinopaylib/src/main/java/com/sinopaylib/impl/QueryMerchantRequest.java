package com.sinopaylib.impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.sinopaylib.entity.request.QueryMerchantReqModel;
import com.sinopaylib.entity.respons.QueryMerchantRep;
import com.sinopaylib.inter.RequestService;

import io.reactivex.Flowable;

/**
 * User: LiuGq
 * Date: 2017/12/7
 * Time: 11:49
 */

public class QueryMerchantRequest {
    public static Flowable<QueryMerchantRep> getMerchantInfomation(QueryMerchantReqModel params) {
        return Flowable.just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getMerchantInfomation(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
