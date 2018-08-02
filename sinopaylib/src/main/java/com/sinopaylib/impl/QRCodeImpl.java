package com.sinopaylib.impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.sinopaylib.entity.request.QrCodeReqModel;
import com.sinopaylib.entity.respons.QrCodeRepModel;
import com.sinopaylib.inter.RquestQRCodeInter;
import com.sinopaylib.listener.QRCodeListener;
import com.sinopaylib.mapbean.TransMapToBeanUtils;
import com.sinopaylib.utils.PayUtils;
import com.sinopaylib.utils.RSACoder;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/2 0002.
 */

public class QRCodeImpl implements RquestQRCodeInter {
    @Override
    public void getQRCode(Context context, Map<String, String> map, QRCodeListener qrCodeListener) {
        map.put("txnType", "12");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", RSACoder.sign(str.getBytes()).replaceAll("\n\r", ""));
        String sign = RSACoder.sign(str.getBytes()).replaceAll("\n", "");
        map.put("signature", sign);

        MerchantTradeRequestServiceImpl.requestGenerateQRCode((QrCodeReqModel) TransMapToBeanUtils.mapToBean(map, QrCodeReqModel.class))
                .subscribe(new ProgressSubscriber<QrCodeRepModel>(context) {
                    @Override
                    protected void _onNext(QrCodeRepModel qrCodeRepModel) {
                        qrCodeListener._onNext(qrCodeRepModel);
                    }

                    @Override
                    protected void _onError(String message) {
                        qrCodeListener._onError(message);
                    }
                });
    }
}
