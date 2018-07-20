package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.testdemolib.Impl.GetCardMessageImpl;
import com.example.testdemolib.Impl.GetCodeImpl;
import com.example.testdemolib.Interface.GetCardMessageInterface;
import com.example.testdemolib.Interface.GetCodeInterface;
import com.example.testdemolib.Listener.GetCardMessageListener;
import com.example.testdemolib.Listener.GetCodeListener;
import com.example.testdemolib.entity.respons.BankTypeModel;
import com.example.testdemolib.entity.respons.GetBoundCardMobileRespons;
import com.orhanobut.logger.Logger;
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
import butterknife.OnClick;

public class AddCardActivity extends ToolBarActivity {
    @BindView(R.id.et_card)
    EditTextWithDEL et_card;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.ll_ifomation)
    LinearLayout ll_ifomation;
    @BindView(R.id.et_mobile)
    EditTextWithDEL et_mobile;
    @BindView(R.id.et_id)
    EditTextWithDEL et_id;
    @BindView(R.id.et_name)
    EditTextWithDEL et_name;
    @BindView(R.id.et_cvn)
    EditTextWithDEL et_cvn;
    @BindView(R.id.et_valid)
    EditTextWithDEL et_valid;
    private PreferencesUtil prefs;
    private String mobile;
    private String countryCode;
    private String sessionID;
    private String cardType = "0";
    private String cardName;
    private String etMobile;
    private String etId;
    private String etName;
    private String etCvn;
    private String etValid;
    private String key;
    private GetCardMessageInterface getCardMessageInterface;
    private GetCodeInterface getCodeInterface;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_add_card;
    }

    @Override
    protected void initViewsAndEvents() {
        try {
            prefs = new PreferencesUtil(mContext);
            mobile = prefs.readPrefs(Constant.PREFES_MOBILE);
            countryCode = prefs.readPrefs(Constant.PREFES_COUNTRYCODE);
            sessionID = prefs.readPrefs(Constant.PREFES_SESSIONID);
            et_card.setVisibility(View.VISIBLE);

            key = DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick({R.id.btn_next})
    public void onClick(View view) {

        getData();

        int i = view.getId();
        if (i == R.id.btn_next) {
            switch (cardType) {
                case "0":
                    //获取卡信息:借记卡/信用卡
                    if (checkCard()) {
                        getCardInformation();
                    }
                    break;
                case "1":
                    //借记卡
                    if (checkDebit()) {
                        boundDebit();
                    }
                    break;
                case "2":
                    if (checkCredit()) {
                        //信用卡
                    }
                    break;
            }
        }
    }

    private void boundDebit() {
        try {
            getCodeInterface = new GetCodeImpl();
            Map<String, String> map = new TreeMap<>();
            map.put("cardNum", DESCoder.encryptMode(cardName, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
            map.put("expired", DESCoder.encryptMode(etValid, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
            map.put("cvn2", DESCoder.encryptMode(etCvn, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
            map.put("phoneNo", DESCoder.encryptMode(etMobile, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
            map.put("idCard", DESCoder.encryptMode(etId, DESCoder.decryptMode(prefs.readPrefs(Constant.PREFES_KEY), prefs.readPrefs(Constant.PREFES_RANDOMKEY))).replaceAll("\n", ""));
            map.put("sysareaid", "SG");
            map.put("countryCode", prefs.readPrefs(Constant.PREFES_COUNTRYCODE));
            map.put("bindCountryCode", "86");//先写死
            map.put("mobile", prefs.readPrefs(Constant.PREFES_MOBILE));
            map.put("idType", "01");//先写死
            map.put("name", etName);
            map.put("sessionID", prefs.readPrefs(Constant.PREFES_SESSIONID));

            getCodeInterface.getMessage(mContext, map, getCodeListener);
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    /**
     * 卡号判空
     */
    private boolean checkCard() {
        if (TextUtils.isEmpty(et_card.getText().toString().trim())) {
            ToastUtils.showLong(getString(R.string.bankcard_hint_bank_card));
            return false;
        }
        return true;
    }

    /**
     * 借记卡判空
     */
    private boolean checkDebit() {
        if (TextUtils.isEmpty(etMobile)) {
            ToastUtils.showLong("请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(etId)) {
            ToastUtils.showLong("请输入证件号");
            return false;
        }
        if (TextUtils.isEmpty(etName)) {
            ToastUtils.showLong("请输入姓名");
            return false;
        }
        return true;
    }

    /**
     * 信用卡判空
     */
    private boolean checkCredit() {
        if (TextUtils.isEmpty(etMobile)) {
            ToastUtils.showLong("请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(etId)) {
            ToastUtils.showLong("请输入证件号");
            return false;
        }
        if (TextUtils.isEmpty(etName)) {
            ToastUtils.showLong("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(etCvn)) {
            ToastUtils.showLong("请输入cvn");
            return false;
        }
        if (TextUtils.isEmpty(etValid)) {
            ToastUtils.showLong("请输入有效期");
            return false;
        }
        return true;
    }

    private void getData() {
        cardName = et_card.getText().toString().trim();
        etMobile = et_mobile.getText().toString().trim();
        etId = et_id.getText().toString().trim();
        etName = et_name.getText().toString().trim();
        etCvn = et_cvn.getText().toString().trim();
        etValid = et_valid.getText().toString().trim();
    }

    private void getCardInformation() {
        getCardMessageInterface = new GetCardMessageImpl();
        Map<String, String> map = new TreeMap<>();
        map.put("mobile", mobile);
        map.put("countryCode", countryCode);
        map.put("sessionID", sessionID);
        map.put("txnType", "51");
        map.put("cardNum", DESCoder.encryptMode(cardName, key).replaceAll("\n", ""));
        getCardMessageInterface.getMessage(mContext, map, getCardMessageListener);
    }

    //获取验证码listener
    GetCodeListener getCodeListener = new GetCodeListener() {
        @Override
        public void _onNext(GetBoundCardMobileRespons getBoundCardMobileRespons) {
            if (getBoundCardMobileRespons.status.equals("0")) {
                //跳到下一页去输入验证码
                Bundle bundle = new Bundle();
                bundle.putString("orderId", getBoundCardMobileRespons.getOrderId());
                bundle.putString("cardNum", cardName);
                bundle.putString("idCard", etId);
                bundle.putString("cvn2", etCvn);
                bundle.putString("expired", etValid);
                bundle.putString("name", etName);
                bundle.putString("bindCountryCode", "86");
                bundle.putString("idType", "01");
                bundle.putString("phoneNo", etMobile);
                openActivity(ImputCodeActivity.class, bundle, true);
            }
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };

    //获取卡详情listener
    GetCardMessageListener getCardMessageListener = new GetCardMessageListener() {
        @Override
        public void _onNext(BankTypeModel bankTypeModel) {
            if (bankTypeModel.status.equals("0")) {
                cardType = bankTypeModel.getCardType();
                et_card.setVisibility(View.INVISIBLE);
                ll_ifomation.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void _onError(String error) {
            ToastUtils.showLong(error);
        }
    };
}
