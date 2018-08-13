package com.uppayplugin.unionpay.javabasetes.Impl;

import com.axl.android.frameworkbase.net.HttpEngine;
import com.uppayplugin.unionpay.javabasetes.api.PersonInfoRequestService;
import com.uppayplugin.unionpay.javabasetes.entity.response.BaseRepModel;
import com.uppayplugin.unionpay.javabasetes.entity.request.SetMultiLangReqModel;
import com.uppayplugin.unionpay.javabasetes.utils.helper.RxSchedulersHelper;

import io.reactivex.Flowable;

/**
 * Created by LiuGuoqing on 2018/7/10 0010 09:22
 * E-Mail Address：1076790023@qq.com
 */

public class PersonalInfoRequestImpl {
    /**
     * 设置多语言
     * @param setMultiLangReqModel
     * @return
     */
    public static Flowable<BaseRepModel> setMultiLanguage(SetMultiLangReqModel setMultiLangReqModel) {
        return Flowable.just(setMultiLangReqModel)
                .concatMap(stringStringMap ->
                        HttpEngine.getInstance()
                                .createServices(PersonInfoRequestService.class)
                                .setMultiLanguage(setMultiLangReqModel)
                                .compose(RxSchedulersHelper.io_main()));
    }
}
