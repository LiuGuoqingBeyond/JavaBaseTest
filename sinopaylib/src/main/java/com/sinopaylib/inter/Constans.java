package com.sinopaylib.inter;

import com.sinopaylib.BuildConfig;

/**
 * User: LiuGq
 * Date: 2017/11/5
 * Time: 9:13
 */

public interface Constans {

    String BASEURL = BuildConfig.BASEURL + "UGateWay/merAppService";

    //这个是关于我们的h5页面地址   中文
    String ABOUTUSCHNURL = BuildConfig.BASEURL + "UGateWay/about.jsp?Language=CHN";
    //英文
    String ABOUTUSENGURL = BuildConfig.BASEURL + "UGateWay/about.jsp?Language=ENG";

}
