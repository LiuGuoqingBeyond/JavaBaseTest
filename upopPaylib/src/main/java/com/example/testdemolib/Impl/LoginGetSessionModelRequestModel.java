package com.example.testdemolib.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.example.testdemolib.Interface.RequestService;
import com.example.testdemolib.entity.request.LoginGetSessionModel;
import com.example.testdemolib.entity.respons.GetTemsessionReqModel;

import io.reactivex.Flowable;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 22:45
 */

public class LoginGetSessionModelRequestModel {
    public static Flowable<GetTemsessionReqModel> getSessionMessage(LoginGetSessionModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getSessionMessage(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
