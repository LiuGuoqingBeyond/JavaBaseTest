package com.example.testdemolib.Interface;

import com.example.testdemolib.BuildConfig;

/**
 * User: LiuGq
 * Date: 2017/11/5
 * Time: 9:13
 */

public interface Constans {
    String BASEURL = BuildConfig.BASEURL + "UGateWay/appService";

    //这个是关于我们的h5页面地址   中文
    String ABOUTUSCHNURL = BuildConfig.BASEURL + "UGateWay/about.jsp?Language=CHN";
    //英文
    String ABOUTUSENGURL = BuildConfig.BASEURL + "UGateWay/about.jsp?Language=ENG";
    // App服务请求地址
    String URL_VIRTUAL_APP_SERVICE_REQUEST = BuildConfig.BASEURL + "UGateWay/virtualCard";

}
