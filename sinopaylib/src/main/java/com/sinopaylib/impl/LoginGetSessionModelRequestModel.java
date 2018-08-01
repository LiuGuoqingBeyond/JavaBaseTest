package com.sinopaylib.impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.sinopaylib.entity.request.LoginAppModel;
import com.sinopaylib.entity.request.LoginGetSessionModel;
import com.sinopaylib.entity.respons.GetTemsessionRepModel;
import com.sinopaylib.entity.respons.LoginAppRepModel;
import com.sinopaylib.inter.RequestService;

import io.reactivex.Flowable;

/**
 * User: LiuGq
 * Date: 2017/11/15
 * Time: 22:45
 */

public class LoginGetSessionModelRequestModel {
    public static Flowable<GetTemsessionRepModel> getSessionMessage(LoginGetSessionModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getSessionMessage(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
    public static Flowable<LoginAppRepModel> getLoginAppMessage(LoginAppModel params) {
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getLoginAppMessage(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
