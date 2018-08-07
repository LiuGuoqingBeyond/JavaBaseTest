package com.sinopaylib.impl;

import android.content.Context;
import android.text.TextUtils;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.sinopaylib.entity.request.QueryMerchantReqModel;
import com.sinopaylib.entity.respons.QueryMerchantRep;
import com.sinopaylib.inter.MerChantInter;
import com.sinopaylib.listener.MerChantInfoListener;
import com.sinopaylib.mapbean.TransMapToBeanUtils;
import com.sinopaylib.utils.PayUtils;
import com.sinopaylib.utils.RSACoder;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public class MerChantImpl implements MerChantInter {
    @Override
    public void getMerChantInfo(Context context, Map<String, String> map, MerChantInfoListener merChantInfoListener) {
        map.put("txnType", "13");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes()).replaceAll("\n\r", ""));
        String sign = RSACoder.sign(str.getBytes()).replaceAll("\n", "");
        map.put("signature", sign);
        QueryMerchantRequest.getMerchantInfomation((QueryMerchantReqModel) TransMapToBeanUtils.mapToBean(map, QueryMerchantReqModel.class))
                .subscribe(new ProgressSubscriber<QueryMerchantRep>(context) {
                    @Override
                    protected void _onNext(QueryMerchantRep queryMerchantRep) {
                        merChantInfoListener._onNext(queryMerchantRep);
                    }

                    @Override
                    protected void _onError(String message) {
                        merChantInfoListener._onError(message);
                    }
                });
    }
}
