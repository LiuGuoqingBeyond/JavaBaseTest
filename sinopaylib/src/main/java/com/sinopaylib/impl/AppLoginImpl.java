package com.sinopaylib.impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.sinopaylib.entity.request.LoginAppModel;
import com.sinopaylib.entity.request.LoginGetSessionModel;
import com.sinopaylib.entity.respons.GetTemsessionRepModel;
import com.sinopaylib.entity.respons.LoginAppRepModel;
import com.sinopaylib.inter.AppLoginInterface;
import com.sinopaylib.listener.AppLoginListener;
import com.sinopaylib.mapbean.TransMapToBeanUtils;
import com.sinopaylib.utils.PayUtils;
import com.sinopaylib.utils.RSACoder;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public class AppLoginImpl implements AppLoginInterface {
    @Override
    public void appToLogin(Context context, Map<String, String> map, AppLoginListener appLoginListener) {
        map.put("txnType", "04");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes()).replaceAll("\n\r", ""));
        LoginGetSessionModelRequestModel.getLoginAppMessage((LoginAppModel) TransMapToBeanUtils.mapToBean(map, LoginAppModel.class))
                .subscribe(new ProgressSubscriber<LoginAppRepModel>(context) {
                    @Override
                    protected void _onNext(LoginAppRepModel loginAppRepModel) {
                        appLoginListener._onNext(loginAppRepModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        appLoginListener._onError(message);
                    }
                });
    }
}
