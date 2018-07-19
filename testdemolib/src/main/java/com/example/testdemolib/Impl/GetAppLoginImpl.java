package com.example.testdemolib.Impl;

import android.content.Context;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.axl.android.frameworkbase.utils.Constant;
import com.example.testdemolib.Interface.GetAppLoginMessageInterface;
import com.example.testdemolib.Listener.GetAppLoginMessageListener;
import com.example.testdemolib.entity.respons.LoginAppReqModel;
import com.example.testdemolib.mapbean.TransMapToBeanUtils;
import com.example.testdemolib.utils.PayUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by LiuGuoqing on 2018/7/17 0017 16:39
 * E-Mail Address：1076790023@qq.com
 */

public class GetAppLoginImpl implements GetAppLoginMessageInterface{
    @Override
    public void getMessage(Context context, Map<String, String> map, GetAppLoginMessageListener getAppLoginMessageListener) {
        map.put("txnType", "06");
        String str = PayUtils.joinMapValue(map, '&');
        map.put("signature", com.uppayplugin.unionpay.libcommon.rsa.RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n\r", ""));
        String result = new Gson().toJson(map);
        Logger.e("result" + result);

        LoginAppRequest.getLoginAppMessage((LoginAppModel) TransMapToBeanUtils.mapToBean(map, LoginAppModel.class))
                .subscribe(new ProgressSubscriber<LoginAppReqModel>(context) {
                    @Override
                    protected void _onNext(LoginAppReqModel loginAppReqModel) {
                        Logger.e("app登陆返回="+loginAppReqModel.toString());
                        if (loginAppReqModel.isOk()) {
                            getAppLoginMessageListener.getMessage(loginAppReqModel.toString());
                            //注意:一次登陆成功，下次登陆标记自动登录
                            /*isAuto = true;//当成功登陆过了，就自动登录
                            isRemember = true;*/

                            // 记录信息
                            // prefs.clearPrefs();
                            /*prefs.writePrefs(Constant.PREFES_IMEI_CODE, IMEI);
                            prefs.writePrefs(Constant.PREFES_SESSIONID, loginAppReqModel.getSessionID());
                            Logger.e("sessionID", loginAppReqModel.getSessionID());
                            prefs.writePrefs(Constant.PREFES_KEY, loginAppReqModel.getSecurityKey());
                            Logger.e("SecurityKey", loginAppReqModel.getSecurityKey());
                            prefs.writePrefs(Constant.PREFES_TEMPSESSIONID, tvTempSessionID.getText().toString().trim());
                            prefs.writePrefs(Constant.PREFES_TEMPKEY, tvTempKey.getText().toString().trim());
                            prefs.writePrefs(Constant.PREFES_RANDOMKEY, randomKey);
                            Logger.e("randomKey", randomKey);
                            prefs.writePrefs(Constant.PREFES_MOBILE, mobile);
                            prefs.writePrefs(Constant.PREFES_LATITUDE, Latitude);
                            prefs.writePrefs(Constant.PREFES_LONGITUDE, Longitude);
                            prefs.writePrefs(Constant.PREFES_IP, PhoneUtils.getPhoneIp());
                            prefs.writePrefs(Constant.PREFES_BASESTATION, "ww");
                            prefs.writePrefs(Constant.PREFES_COUNTRYCODE, tvCountrycode.getText().toString().trim().replace("+",""));
                            prefs.writePrefs(Constant.PREFES_COUNTRY, country);

                            prefs.writePrefs(Constant.PREFES_RANDOMNO, loginAppReqModel.getRandomNo());//随机数
                            prefs.writePrefs(Constant.UPOPTIME, loginAppReqModel.getUpopTime());//系统时间*/

                            // 记住密码（记录登录信息）
                            /*if (isRemember) {
                                prefs.writePrefs(Constant.PREFES_REMEMBER, "Y");
                                // FIXME: 2017/11/4 zhanghuan modify encrypt pwd
                                prefs.writePrefs(Constant.PREFES_PASSWORD, DataEncryUtil.getEncryptPassWord(password));
                            }
                            if (isAuto) {
                                prefs.writePrefs(Constant.PREFES_AUTO, "Y");
                            }*/

                            // 登录成功之后,获取绑定的银行卡列表信息
                            // getAccountSinoCardListData(session.getSessionID(), countryCode);//获取银行卡列表

                            /**
                             * 登录成功之后，后续步骤
                             * 1：本地查询是否设置过支付密码
                             * 2：本地未设置,从服务端查询,服务端未设置则跳转到设置支付密码界面
                             * 3: 设置过支付密码，从服务端查询绑定的银行卡列表信息
                             */
                                /*if (PayPwdManagerUtils.getInstance(mContext).checkSetPayPwdFromLocal()) {
                                    // 本地有设置过支付密码,直接查询绑定的银行类列表信息
                                    Logger.e("have already set payPwd.");
                                    queryBankCardListFromService(loginAppReqModel.getSessionID(), countryCode);
                                } else {
                                    Logger.d("start query isSetPayPwd from Service.");
                                    queryIsSetPayPwdFromService(loginAppReqModel.getSessionID(), countryCode);
                                }*/
                            /*queryIsSetPayPwdFromService(loginAppReqModel.getSessionID(), countryCode);*/
                        } else {
                            // 登录失败,提示异常登录，让用户重新登录
                            /*prefs.writePrefs(Constant.PREFES_PASSWORD, "");//如果登陆不成功，则密码存空
//                                DialogShowUtils.showNoticeDialog(mContext,"",loginAppReqModel.getMsg(),getString(R.string.text_know),false);
                            layLoginError.setVisibility(View.VISIBLE);
                            tvLoginError.setText(loginAppReqModel.getMsg());
                            ToastUtils.showLong(loginAppReqModel.getMsg());
                            finish();*/
                        }
                    }

                    @Override
                    protected void _onError(String message) {
//                            DialogShowUtils.showNoticeDialog(mContext,"",message,getString(R.string.text_confirm_msg),true);
                        /*ToastUtils.showLong(message);
                        finish();*/
                    }
                });
    }
}
