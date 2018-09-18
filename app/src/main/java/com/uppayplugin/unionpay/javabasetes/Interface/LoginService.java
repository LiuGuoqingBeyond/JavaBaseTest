package com.uppayplugin.unionpay.javabasetes.Interface;
import com.uppayplugin.unionpay.javabasetes.entity.request.LoginReqModel;
import com.uppayplugin.unionpay.javabasetes.entity.response.LoginRepModel;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * User: LiuGuoqing
 * Data: 2018/9/17 0017.
 */

public interface LoginService {
    //查询银行卡列表
    @POST("http://192.168.2.34:8112/login")
    Flowable<LoginRepModel> loginRequest(@Body LoginReqModel params);
}
