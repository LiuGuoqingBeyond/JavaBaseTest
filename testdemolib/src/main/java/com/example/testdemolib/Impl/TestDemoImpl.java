package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.TestDemoInterface;
import com.example.testdemolib.Listener.TestDemoListener;
import com.example.testdemolib.entity.request.LoginGetSessionModel;
import com.example.testdemolib.entity.respons.GetTemsessionReqModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.example.testdemolib.utils.RSACoder;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/7/6 0006.
 */

public class TestDemoImpl implements TestDemoInterface {
    private Context mContext;
    private TestDemoListener testDemoListener;
    private String model;
    private String password;
    private Map<String, String> map;

    @Override
    public void getMessage(Context context, Map<String, String> map, TestDemoListener testDemoListener) {
        this.mContext = context;
        this.model = model;
        this.password = password;
        this.map = map;
        this.testDemoListener = testDemoListener;
        map.put("txnType", "01");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);
        LoginGetSessionModelRequestModel.getSessionMessage((LoginGetSessionModel) TransMapToBeanUtils.mapToBean(map, LoginGetSessionModel.class))
                .subscribe(new ProgressSubscriber<GetTemsessionReqModel>(mContext) {
                    @Override
                    protected void _onNext(GetTemsessionReqModel getTemsessionReqModel) {
                        Logger.e("请求sessionId=" + getTemsessionReqModel.toString());
                        testDemoListener._onNext(getTemsessionReqModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        testDemoListener._onError(message);
                    }
                });
    }

    public static String joinMapValue(Map<String, String> map, char connector) {
        StringBuffer b = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            b.append(entry.getKey());
            b.append('=');
            if (entry.getValue() != null) {
                b.append(entry.getValue());
            }
            b.append(connector);
        }
        return b.toString();
    }

    public String[] getResArr(String str) {
        String regex = "(.*?cupReserved\\=)(\\{[^}]+\\})(.*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);

        String reserved = "";
        if (matcher.find()) {
            reserved = matcher.group(2);
        }

        String result = str.replaceFirst(regex, "$1$3");
        String[] resArr = result.split("&");
        for (int i = 0; i < resArr.length; i++) {
            if ("cupReserved=".equals(resArr[i])) {
                resArr[i] += reserved;
            }
        }
        return resArr;
    }
}
