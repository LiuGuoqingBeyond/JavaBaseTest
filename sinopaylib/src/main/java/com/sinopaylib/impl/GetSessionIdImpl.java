package com.sinopaylib.impl;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.sinopaylib.entity.request.LoginGetSessionModel;
import com.sinopaylib.entity.respons.GetTemsessionRepModel;
import com.sinopaylib.inter.SessionIdInterface;
import com.sinopaylib.listener.SessionIdListener;
import com.sinopaylib.mapbean.TransMapToBeanUtils;
import com.sinopaylib.utils.PayUtils;
import com.sinopaylib.utils.RSACoder;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public class GetSessionIdImpl implements SessionIdInterface{

    @Override
    public void getSessionId(Context context, Map<String, String> map, SessionIdListener sessionIdListener) {
        map.put("txnType", "01");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes()).replaceAll("\n\r", ""));
        LoginGetSessionModelRequestModel.getSessionMessage((LoginGetSessionModel) TransMapToBeanUtils.mapToBean(map, LoginGetSessionModel.class))
                .subscribe(new ProgressSubscriber<GetTemsessionRepModel>(context) {
                    @Override
                    protected void _onNext(GetTemsessionRepModel getTemsessionRepModel) {
                        sessionIdListener._onNext(getTemsessionRepModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        sessionIdListener._onError(message);
                    }
                });
    }
}
