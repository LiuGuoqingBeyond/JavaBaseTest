package com.uppayplugin.unionpay.javabasetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.sinopaylib.APIProcxy;
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
import com.uppayplugin.unionpay.javabasetest.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetest.view.EditTextWithDEL;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

import static com.sinopaylib.impl.Constant.publicKey;
import static com.sinopaylib.utils.DESCoder.get3DesKeyRandom;
import static com.uppayplugin.unionpay.javabasetest.bean.SharedInfo.IMEI;

public class SinoPayActivity extends ToolBarActivity {
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
        btnLogin.setOnClickListener(view -> {
            getData();
            runOnUiThread(() -> {
                sessionIdInterface = APIProcxy.getmSessionIdInterface();
                Map<String, String> map = new TreeMap<>();
                map.put("countryCode", "86");
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
            map.put("countryCode", "86");
            map.put("mobile", mobile);
            map.put("sessionID", sessionID);
            map.put("password", DESCoder.encryptMode(password, randomKey).replaceAll("\n", ""));
            alias = MD5Util.generateMd5String(mobile + IMEI);
            map.put("alias", alias);
            map.put("MD5Data", base64key);

            appLoginInterface = APIProcxy.getmAppLoginInterface();
            appLoginInterface.appToLogin(mContext,map,appLoginListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    AppLoginListener appLoginListener = new AppLoginListener() {
        @Override
        public void _onNext(LoginAppRepModel loginAppRepModel) {
            ToastUtils.showLong(loginAppRepModel.toString());
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    private void getData() {
        mobile = etAccount.getText().toString().trim();
        password = etPassword.getText().toString().trim();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
