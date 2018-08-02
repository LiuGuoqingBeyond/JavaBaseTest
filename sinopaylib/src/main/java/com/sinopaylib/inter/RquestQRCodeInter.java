package com.sinopaylib.inter;

import android.content.Context;

import com.sinopaylib.listener.QRCodeListener;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/2 0002.
 */

public interface RquestQRCodeInter {
    void getQRCode(Context context, Map<String, String> map, QRCodeListener qrCodeListener);
}
