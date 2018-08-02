package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.sinopaylib.api.APIProcxy;
import com.sinopaylib.entity.respons.GetTemsessionRepModel;
import com.sinopaylib.entity.respons.LoginAppRepModel;
import com.sinopaylib.inter.AppLoginInterface;
import com.sinopaylib.inter.SessionIdInterface;
import com.sinopaylib.listener.AppLoginListener;
import com.sinopaylib.listener.SessionIdListener;
import com.sinopaylib.utils.DESCoder;
import com.sinopaylib.utils.MD5Util;
import com.sinopaylib.utils.RSACoder;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetest.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetest.view.EditTextWithDEL;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

import static com.sinopaylib.impl.Constant.publicKey;
import static com.sinopaylib.utils.DESCoder.get3DesKeyRandom;
import static com.uppayplugin.unionpay.javabasetest.bean.SharedInfo.IMEI;
import static com.uppayplugin.unionpay.javabasetest.utils.PublicMethodUtils.getPhoneIp;

public class SinoPayActivity extends ToolBarActivity {
    @BindView(R.id.et_countryCode)
    EditTextWithDEL etCountryCode;
    @BindView(R.id.et_account)
    EditTextWithDEL etAccount;
    @BindView(R.id.et_password)
    EditTextWithDEL etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String mobile;
    private String password;
    private SessionIdInterface sessionIdInterface;
    private String randomKey;
    private String alias;
    private AppLoginInterface appLoginInterface;
    private String countryCode;
    private PreferencesUtil prefs;
    private String temSessionID;
    private String securityKey;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sino_pay;
    }

    @Override
    protected void initViewsAndEvents() {
        prefs = new PreferencesUtil(mContext);
        btnLogin.setOnClickListener(view -> {
            getData();
            runOnUiThread(() -> {
                sessionIdInterface = APIProcxy.getInstance().getmSessionIdInterface();
                Map<String, String> map = new TreeMap<>();
                map.put("countryCode", countryCode);
                map.put("mobile", mobile);
                sessionIdInterface.getSessionId(mContext, map, sessionIdListener);
            });
        });
    }

    SessionIdListener sessionIdListener = new SessionIdListener() {
        @Override
        public void _onNext(GetTemsessionRepModel getTemsessionRepModel) {
            if (getTemsessionRepModel.isOk()) {
                //去登陆
                temSessionID = getTemsessionRepModel.getSessionID();
                securityKey = getTemsessionRepModel.getSecurityKey();
                gotoLogin(getTemsessionRepModel.getSessionID(), getTemsessionRepModel.getSecurityKey());
            }
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    private void gotoLogin(String sessionID, String securityKey) {
        try {
            Map<String, String> map = new TreeMap<String, String>();
            randomKey = get3DesKeyRandom();
            Logger.e("ranzdomKey", randomKey);
            byte[] signStr = RSACoder.encryptByPublicKey(randomKey.getBytes(), publicKey);
            String base64key = RSACoder.encryptBASE64(signStr).replaceAll("\n", "");
            map.put("countryCode", countryCode);
            map.put("mobile", mobile);
            map.put("sessionID", sessionID);
            map.put("password", DESCoder.encryptMode(password, randomKey).replaceAll("\n", ""));
            alias = MD5Util.generateMd5String(mobile + IMEI);
            map.put("alias", alias);
            map.put("MD5Data", base64key);

            appLoginInterface = APIProcxy.getInstance().getmAppLoginInterface();
            appLoginInterface.appToLogin(mContext,map,appLoginListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    AppLoginListener appLoginListener = new AppLoginListener() {
        @Override
        public void _onNext(LoginAppRepModel loginAppRepModel) {
            //登陆成功之后，保存值，跳转到主页，有获取二维码页，交易记录、 退出
            prefs.writePrefs(Constant.PREFES_MERSIMPLENAME,loginAppRepModel.getMerShortName());
            prefs.writePrefs(Constant.PREFES_MERNAME, loginAppRepModel.getMerName());
            prefs.writePrefs(Constant.PREFES_SESSIONID, loginAppRepModel.getSessionID());
            prefs.writePrefs(Constant.PREFES_KEY, loginAppRepModel.getSecurityKey());
            Logger.e("SecurityKey", loginAppRepModel.getSecurityKey());
            prefs.writePrefs(Constant.PREFES_TEMPSESSIONID, temSessionID);
            prefs.writePrefs(Constant.PREFES_RANDOMKEY, randomKey);
            Logger.e("randomKey", randomKey);
            prefs.writePrefs(Constant.PREFES_IP, getPhoneIp());
            prefs.writePrefs(Constant.PREFES_BASESTATION, "ww");
            prefs.writePrefs(mobile+Constant.PREFES_COUNTRYCODE, "86");
            prefs.writePrefs(Constant.PREFES_FILINGSTATUS, loginAppRepModel.getFillingStatus());
//            prefs.writePrefs(Constant.CASHIERACCOUNT,etCashierAccount.getText().toString());
//            prefs.writePrefs(Constant.DESKACCOUNT,etDeskAccount.getText().toString());
//            prefs.writePrefs(Constant.PHONELOGIN,etAccount.getText().toString().trim());
            prefs.writePrefs(mobile+Constant.TERMCODE,loginAppRepModel.getTermCode());
            prefs.writePrefs(mobile+Constant.EMAIL,loginAppRepModel.getEmail());
            prefs.writePrefs(Constant.PREFES_MOBILE, etAccount.getText().toString().trim());

            prefs.writePrefs(Constant.TERID,loginAppRepModel.getTerID());

            // FIXME: 2018/5/16 LGQ 新增商户号
            prefs.writePrefs(mobile+ Constant.MERID,loginAppRepModel.getMerID());
            openActivity(SinoPayMainActivity.class);
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    private void getData() {
        countryCode = etCountryCode.getText().toString().trim();
        mobile = etAccount.getText().toString().trim();
        password = etPassword.getText().toString().trim();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
