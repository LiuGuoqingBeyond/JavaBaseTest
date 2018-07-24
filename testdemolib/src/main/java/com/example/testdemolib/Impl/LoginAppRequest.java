package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.Interface.RequestService;
import com.example.testdemolib.entity.request.LoginAppModel;
import com.example.testdemolib.entity.respons.LoginAppReqModel;

import io.reactivex.Flowable;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 23:03
 */

public class LoginAppRequest {
    public static Flowable<LoginAppReqModel> getLoginAppMessage(LoginAppModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getLoginAppMessage(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
