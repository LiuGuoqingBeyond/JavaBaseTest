package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.Interface.RequestService;
import com.example.testdemolib.entity.request.BoundCardModel;
import com.example.testdemolib.entity.respons.BankCardMobel;

import io.reactivex.Flowable;

/**
 * User: LiuGq
 * Date: 2017/11/7
 * Time: 11:28
 */

public class BoundCardRequest {
    public static Flowable<BankCardMobel> getBoundCard(BoundCardModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getBoundCard(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
