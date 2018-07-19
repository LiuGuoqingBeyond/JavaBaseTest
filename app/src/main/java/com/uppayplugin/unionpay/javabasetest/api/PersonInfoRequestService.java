package com.uppayplugin.unionpay.javabasetest.api;
import com.uppayplugin.unionpay.javabasetest.entity.request.SetMultiLangReqModel;
import com.uppayplugin.unionpay.javabasetest.entity.response.BaseRepModel;
import com.uppayplugin.unionpay.javabasetest.test.URLConstants;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/6 13:53
 * @email：zhanghuan@xinguodu.com
 */
public interface PersonInfoRequestService {
    //设置多语言接口
    @POST(URLConstants.URL_APP_SERVICE_REQUEST)
    Flowable<BaseRepModel> setMultiLanguage(@Body SetMultiLangReqModel setMultiLangReqModel);
}