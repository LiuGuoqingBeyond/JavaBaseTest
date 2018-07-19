package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testdemolib.Impl.PayResultImpl;
import com.example.testdemolib.Interface.PayResultInterface;
import com.example.testdemolib.Listener.PayResultListener;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.JSONUtil;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetest.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetest.view.EditTextWithDEL;
import com.uppayplugin.unionpay.libcommon.des.DESCoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

public class PayActivity extends ToolBarActivity {
    @BindView(R.id.et_carNumber)
    EditTextWithDEL etCarNumber;
    @BindView(R.id.et_money)
    EditTextWithDEL etMoney;
    @BindView(R.id.et_password)
    EditTextWithDEL etPassword;
    @BindView(R.id.btn_pay)
    Button btnPay;
    private String cardNum;
    private String mobile = "";
    private String countryCode = "";
    private String sessionID = "";
    private String userKey = "";
    private PreferencesUtil prefes;
    private String emvcode;
    private String txnAmt;
    private String password;
    private String txnCurr;
    private String key;
    private PayResultInterface payResultInterface;
    private String securityKey;
    private String randomKey;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        emvcode = extras.getString("emvcode");
        txnCurr = extras.getString("txnCurr");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViewsAndEvents() {
        prefes = new PreferencesUtil(mContext);
        mobile = prefes.readPrefs(Constant.PREFES_MOBILE);
        countryCode = prefes.readPrefs(Constant.PREFES_COUNTRYCODE);
        sessionID = prefes.readPrefs(Constant.PREFES_SESSIONID);
        userKey = prefes.readPrefs(Constant.PREFES_IMEI_CODE);
        securityKey = prefes.readPrefs(Constant.PREFES_KEY);
        randomKey = prefes.readPrefs(Constant.PREFES_RANDOMKEY);
        try {
            key = DESCoder.decryptMode(securityKey, randomKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    payResultInterface = new PayResultImpl();
                    cardNum = etCarNumber.getText().toString().trim();
                    txnAmt = etMoney.getText().toString().trim();
                    password = etPassword.getText().toString().trim();
                    Map<String, String> map = new TreeMap<>();

                    map.put("countryCode", countryCode);
                    map.put("mobile", mobile);
                    map.put("sessionID", sessionID);
                    map.put("txnCurr", txnCurr);
                    map.put("billingCurr", "156");
                    map.put("txnAmt", txnAmt);
                    map.put("sysArea", "702");
                    map.put("payPassword", DESCoder.encryptMode(password, key).replaceAll("\n", ""));
                    map.put("cardNum", DESCoder.encryptMode(cardNum, DESCoder.decryptMode(securityKey, randomKey)).replaceAll("\n", ""));
                    map.put("userKey", userKey);
                    map.put("emvcode", emvcode);

                    payResultInterface.getMessage(mContext, map, payResultListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    PayResultListener payResultListener = new PayResultListener() {
        @Override
        public void getMessage(String message) {
            try {
                Map<String, Object> jsonMap = JSONUtil.jsonToMap(new JSONObject(message));
                String status = (jsonMap.get("status") != null ? jsonMap.get("status") : "").toString();
                String msg = (jsonMap.get("msg") != null ? jsonMap.get("msg") : "").toString();
                ToastUtils.showLong(msg);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
