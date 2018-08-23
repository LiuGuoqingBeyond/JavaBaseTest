package com.uppayplugin.unionpay.javabasetes.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.net.utils.RxSchedulersHelper;
import com.uppayplugin.unionpay.javabasetes.Interface.RequestService;
import com.uppayplugin.unionpay.javabasetes.entity.request.TodoLoginRequest;
import com.uppayplugin.unionpay.javabasetes.entity.response.TodoLoginRep;

import io.reactivex.Flowable;

/**
 * User: LiuGuoqing
 * Data: 2018/8/22 0022.
 */

public class TodoLoginRequestImpl {
    public static Flowable<TodoLoginRep> loginRequest(TodoLoginRequest params){
        return Flowable
                .just(params)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(RequestService.class)
                                .getPromoteQrCode(params)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
