package com.sinopaylib.listener;
import com.sinopaylib.entity.respons.QrCodeRepModel;

/**
 * Created by Administrator on 2018/8/2 0002.
 */

public interface QRCodeListener {
    void _onNext(QrCodeRepModel qrCodeRepModel);
    void _onError(String error);
}
