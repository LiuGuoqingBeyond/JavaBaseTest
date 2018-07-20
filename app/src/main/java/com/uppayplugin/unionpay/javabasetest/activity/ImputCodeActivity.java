package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.testdemolib.Impl.BoundCardImpl;
import com.example.testdemolib.Interface.BoundCardInterface;
import com.example.testdemolib.Listener.BoundCardListener;
import com.example.testdemolib.entity.respons.BankCardMobel;
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

public class ImputCodeActivity extends ToolBarActivity {
    @BindView(R.id.et_code)
    EditTextWithDEL etCode;
    @BindView(R.id.btn_code)
    Button btnCode;
    private String orderId;
    private BoundCardInterface boundCardInterface;
    private PreferencesUtil prefs;
    private String mobile;
    private String countryCode;
    private String sessionID;
    private String cardNum;
    private String idCard;
    private String cvn2;
    private String expired;
    private String name;
    private String bindCountryCode;
    private String idType;
    private String phoneNo;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        orderId = extras.getString("orderId");
        cardNum = extras.getString("cardNum");
        idCard = extras.getString("idCard");
        cvn2 = extras.getString("cvn2");
        expired = extras.getString("expired");
        name = extras.getString("name");
        bindCountryCode = extras.getString("bindCountryCode");
        idType = extras.getString("idType");
        phoneNo = extras.getString("phoneNo");

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_imput_code;
    }

    @Override
    protected void initViewsAndEvents() {
        prefs = new PreferencesUtil(mContext);
        mobile = prefs.readPrefs(Constant.PREFES_MOBILE);
        countryCode = prefs.readPrefs(Constant.PREFES_COUNTRYCODE);
        sessionID = prefs.readPrefs(Constant.PREFES_SESSIONID);
        btnCode.setOnClickListener(view -> {
            if (codeNull()) {
                //去绑卡
                boundCard();
            }
        });
    }

    private void boundCard() {
        try {
            boundCardInterface = new BoundCardImpl();
            Map<String, String> map = new TreeMap<>();
            map.put("countryCode", prefs.readPrefs(Constant.PREFES_COUNTRYCODE));
            map.put("mobile", prefs.readPrefs(Constant.PREFES_MOBILE));
            map.put("sysareaid", "SG");
            map.put("province", "");
            map.put("city", "");
            map.put("district", "");
            try {
                map.put("cardNum", DESCoder.encryptMode(cardNum, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
                map.put("idCard", DESCoder.encryptMode(idCard, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
                map.put("phoneNo", DESCoder.encryptMode(phoneNo, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
                map.put("cvn2", DESCoder.encryptMode(cvn2, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
                map.put("expired", DESCoder.encryptMode(expired, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("name", name);
            map.put("bindCountryCode", "86");
            map.put("userKey", prefs.readPrefs(Constant.PREFES_IMEI_CODE));
            map.put("sessionID", prefs.readPrefs(Constant.PREFES_SESSIONID));
            map.put("smsCode", etCode.getText().toString());
            map.put("orderId", orderId);

            boundCardInterface.getMessage(mContext, map, boundCardListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    BoundCardListener boundCardListener = new BoundCardListener() {
        @Override
        public void _onNext(BankCardMobel bankCardMobel) {
                ToastUtils.showLong(bankCardMobel.getMsg());
                if (bankCardMobel.getStatus().equals("0")) {
                    finish();
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
    public boolean codeNull() {
        if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
            ToastUtils.showLong("请输入验证码");
            return false;
        }
        return true;
    }
}
