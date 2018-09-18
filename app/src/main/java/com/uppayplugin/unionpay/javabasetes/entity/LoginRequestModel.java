package com.uppayplugin.unionpay.javabasetes.entity;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.uppayplugin.unionpay.javabasetes.Interface.LoginService;
import com.uppayplugin.unionpay.javabasetes.entity.request.LoginReqModel;
import com.uppayplugin.unionpay.javabasetes.entity.response.LoginRepModel;

import io.reactivex.Flowable;

/**
 * User: LiuGuoqing
 * Data: 2018/9/17 0017.
 */

public class LoginRequestModel {
    public static Flowable<LoginRepModel> getloginRequest(LoginReqModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(LoginService.class)
                                .loginRequest(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
