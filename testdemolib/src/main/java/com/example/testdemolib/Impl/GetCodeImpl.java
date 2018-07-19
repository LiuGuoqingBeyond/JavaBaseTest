package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.GetCodeInterface;
import com.example.testdemolib.Listener.GetCodeListener;
import com.example.testdemolib.entity.request.GetBoundCardMobileCodeModel;
import com.example.testdemolib.entity.respons.GetBoundCardMobileRespons;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 09:37
 * E-Mail Address：1076790023@qq.com
 */

public class GetCodeImpl implements GetCodeInterface {
    @Override
    public void getMessage(Context context, Map<String, String> map, GetCodeListener getCodeListener) {
        map.put("txnType", "20");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", com.uppayplugin.unionpay.libcommon.rsa.RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        GetMobileCodeModel.getMessage((GetBoundCardMobileCodeModel) TransMapToBeanUtils.mapToBean(map, GetBoundCardMobileCodeModel.class))
                .subscribe(new ProgressSubscriber<GetBoundCardMobileRespons>(context) {

                    @Override
                    protected void _onNext(GetBoundCardMobileRespons responseModel) {
                        Logger.e("获取验证码=" + responseModel.toString());
                        getCodeListener.getMessage(responseModel.toString());
//                        status = responseModel.getStatus();
                        if (responseModel.isOk()) {
                            // 获取短信验证码成功
                            /*orderId = responseModel.getOrderId();
                            btnGetValidateCode.setEnabled(false);
                            CountDownButtonHelper helper = new CountDownButtonHelper(mContext,
                                    btnGetValidateCode, btnGetValidateCode.getText().toString(), 60, 1);
                            helper.setOnFinishListener(() -> {
                                btnGetValidateCode.setEnabled(true);
                                btnGetValidateCode.setText(getString(R.string.get_code_again));
                            });
                            helper.start();*/
                        } else if (responseModel.needLogin()) {
                            /*DialogShowUtils.showReloginDailog(VerificationPhoneActivity.this, responseModel.msg);*/
                        } else {
                            /*btnGetValidateCode.setEnabled(true);
                            btnGetValidateCode.setText(getString(R.string.matchemail_send_verification));
                            DialogShowUtils.showNoticeDialogWithFinish(mContext, "", responseModel.getMsg(), getString(R.string.text_confirm_msg), false);*/
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        /*btnGetValidateCode.setEnabled(true);
                        btnGetValidateCode.setText(getString(R.string.matchemail_send_verification));
                        Logger.e("_onError-> message:" + message);
                        DialogShowUtils.showNoticeDialog(mContext,"",message,getString(R.string.text_know),true);*/
                    }
                });
    }
}
