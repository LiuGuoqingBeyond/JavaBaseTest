package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.GetAppLoginMessageInterface;
import com.example.testdemolib.Listener.GetAppLoginMessageListener;
import com.example.testdemolib.entity.request.LoginAppModel;
import com.example.testdemolib.entity.respons.LoginAppReqModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/17 0017 16:39
 * E-Mail Address：1076790023@qq.com
 */

public class GetAppLoginImpl implements GetAppLoginMessageInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, GetAppLoginMessageListener getAppLoginMessageListener) {
        map.put("txnType", "06");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        LoginAppRequest.getLoginAppMessage((LoginAppModel) TransMapToBeanUtils.mapToBean(map, LoginAppModel.class))
                .subscribe(new ProgressSubscriber<LoginAppReqModel>(context) {
                    @Override
                    protected void _onNext(LoginAppReqModel loginAppReqModel) {
                        Logger.e("app登陆返回=" + loginAppReqModel.toString());
                        getAppLoginMessageListener._onNext(loginAppReqModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        getAppLoginMessageListener._onError(message);
                    }
                });
    }
}
