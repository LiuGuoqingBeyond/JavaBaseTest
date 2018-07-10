package com.uppayplugin.unionpay.javabasetest.test;

import com.uppayplugin.unionpay.javabasetest.BuildConfig;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/11/3
 * Time: 15:28
 */

//请求地址
public interface URLConstants {

    //使用BASEURL+请求接口拼接
    String URL_TEST = BuildConfig.BASEURL + "/test/";

    // App服务请求地址
    String URL_APP_SERVICE_REQUEST = BuildConfig.BASEURL + "UGateWay/merAppService";
}
