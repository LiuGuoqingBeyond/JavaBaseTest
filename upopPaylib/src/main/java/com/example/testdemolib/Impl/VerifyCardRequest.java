package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.Interface.RequestService;
import com.example.testdemolib.entity.request.GetBankTypeReqModel;
import com.example.testdemolib.entity.respons.BankTypeModel;

import io.reactivex.Flowable;

/**
 * User: LiuGq
 * Date: 2017/11/6
 * Time: 19:47
 */

public class VerifyCardRequest {
    public static Flowable<BankTypeModel> getBankType(GetBankTypeReqModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getBankType(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
