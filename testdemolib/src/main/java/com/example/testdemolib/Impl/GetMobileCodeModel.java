package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.Interface.RequestService;
import com.example.testdemolib.entity.request.GetBoundCardMobileCodeModel;
import com.example.testdemolib.entity.respons.GetBoundCardMobileRespons;

import io.reactivex.Flowable;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 12:53
 */

public class GetMobileCodeModel {

    public static Flowable<GetBoundCardMobileRespons> getMessage(GetBoundCardMobileCodeModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getMessage(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
