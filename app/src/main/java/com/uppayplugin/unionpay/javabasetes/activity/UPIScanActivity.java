package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.testdemolib.Impl.GetAppLoginImpl;
import com.example.testdemolib.Impl.TestDemoImpl;
import com.example.testdemolib.Interface.GetAppLoginMessageInterface;
import com.example.testdemolib.Interface.TestDemoInterface;
import com.example.testdemolib.Listener.GetAppLoginMessageListener;
import com.example.testdemolib.Listener.TestDemoListener;
import com.example.testdemolib.entity.respons.GetTemsessionReqModel;
import com.example.testdemolib.entity.respons.LoginAppReqModel;
import com.example.testdemolib.utils.RSACoder;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetes.view.EditTextWithDEL;
import com.uppayplugin.unionpay.libcommon.des.DESCoder;
import com.uppayplugin.unionpay.libcommon.device.PhoneUtils;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

public class UPIScanActivity extends ToolBarActivity {
    @BindView(R.id.et_account)
    EditTextWithDEL etAccount;
    @BindView(R.id.et_password)
    EditTextWithDEL etPassword;
    @BindView(R.id.tv_country)
    EditTextWithDEL tvCountry;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String mobile;
    private String password;
    private TestDemoInterface testDemoInterface;
    private String tempSessionId;
    private String countryCode;
    private GetAppLoginMessageInterface getAppLoginMessageInterface;
    private PreferencesUtil prefs;
    private String randomKey;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_upiscan;
    }

    @Override
    protected void initViewsAndEvents() {
        prefs = new PreferencesUtil(mContext);
        btnLogin.setOnClickListener(view -> {
            mobile = etAccount.getText().toString();
            password = etPassword.getText().toString();
            countryCode = tvCountry.getText().toString();
            if (checkData(mobile, password)) {
                getTemsessionId();
            }
        });
    }

    private void getTemsessionId() {
        testDemoInterface = new TestDemoImpl();
        Map<String, String> map = new TreeMap<>();
        map.put("countryCode", countryCode);
        map.put("mobile", etAccount.getText().toString().trim());
        testDemoInterface.getMessage(mContext, map, testDemoListener);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    TestDemoListener testDemoListener = new TestDemoListener() {
        @Override
        public void _onNext(GetTemsessionReqModel getTemsessionReqModel) {
            if (null != getTemsessionReqModel) {
                if (getTemsessionReqModel.status.equals("0")) {
                    //去登陆
                    appLoading(getTemsessionReqModel.getSessionID());
                }
            }
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    private void appLoading(String tempSessionId) {
        getAppLoginMessageInterface = new GetAppLoginImpl();
        try {
            Map<String, String> map = new TreeMap<String, String>();
            randomKey = DESCoder.get3DesKeyRandom();
            Logger.e("ranzdomKey" + randomKey);
            byte[] signStr = RSACoder.encryptByPublicKey(randomKey.getBytes(), Constant.publicKey);
            String base64key = RSACoder.encryptBASE64(signStr).replaceAll("\n", "");
            map.put("mobile", mobile);
            map.put("countryCode", countryCode);
            map.put("password", DESCoder.encryptMode(password, randomKey).replaceAll("\n", ""));
            map.put("MD5Data", base64key);
            map.put("userKey", "123456");
            map.put("longitude", "12.23422");
            map.put("latitude", "23.3421");
            map.put("baseStation", "ww");
            map.put("IP", PhoneUtils.getPhoneIp());
            map.put("tempSessionID", tempSessionId);
            getAppLoginMessageInterface.getMessage(mContext, map, getAppLoginMessageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    GetAppLoginMessageListener getAppLoginMessageListener = new GetAppLoginMessageListener() {
        @Override
        public void _onNext(LoginAppReqModel loginAppReqModel) {
            String status = loginAppReqModel.status;
            if (status.equals("0")) {
                String sessionID = loginAppReqModel.getSessionID();
                String securityKey = loginAppReqModel.getSecurityKey();
                String randomNo = loginAppReqModel.getRandomNo();
                String upopTime = loginAppReqModel.getUpopTime();
                prefs.writePrefs(Constant.PREFES_IMEI_CODE, "123456");
                prefs.writePrefs(Constant.PREFES_SESSIONID, sessionID);
                prefs.writePrefs(Constant.PREFES_KEY, securityKey);
                prefs.writePrefs(Constant.PREFES_TEMPSESSIONID, tempSessionId);
                prefs.writePrefs(Constant.PREFES_RANDOMKEY, randomKey);
                prefs.writePrefs(Constant.PREFES_MOBILE, mobile);
                prefs.writePrefs(Constant.PREFES_LATITUDE, "23.3421");
                prefs.writePrefs(Constant.PREFES_LONGITUDE, "12.23422");
                prefs.writePrefs(Constant.PREFES_IP, PhoneUtils.getPhoneIp());
                prefs.writePrefs(Constant.PREFES_BASESTATION, "ww");
                prefs.writePrefs(Constant.PREFES_COUNTRYCODE, countryCode);
                prefs.writePrefs(Constant.PREFES_RANDOMNO, randomNo);//随机数
                prefs.writePrefs(Constant.UPOPTIME, upopTime);//系统时间
//                openActivity(UPIScanMainActivity.class);
                ToastUtils.showLong("登陆成功");
            }
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    /**
     * 判空
     */
    private boolean checkData(String mobile, String passwrod) {
        if (TextUtils.isEmpty(mobile.trim())) {
            ToastUtils.showLong(getString(R.string.phone_number_null));
            return false;
        }
        if (TextUtils.isEmpty(passwrod.trim())) {
            ToastUtils.showLong(getString(R.string.pwd_number_null));
            return false;
        }
        return true;
    }
}
