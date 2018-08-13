package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinopaylib.api.APIProcxy;
import com.sinopaylib.entity.respons.QueryMerchantRep;
import com.sinopaylib.inter.MerChantInter;
import com.sinopaylib.listener.MerChantInfoListener;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

public class MerChantInfoActivity extends ToolBarActivity {

    private MerChantInter merChantInter;
    private PreferencesUtil prefes;
    @BindView(R.id.merchantCountry)
    TextView merchantCountry;
    @BindView(R.id.merchantName)
    TextView merchantName;
    @BindView(R.id.merchantRelation)
    TextView merchantRelation;
    @BindView(R.id.merchantRelationPhone)
    TextView merchantRelationPhone;
    @BindView(R.id.merchantContinueRate)
    TextView merchantContinueRate;
    @BindView(R.id.merchantUnionPay)
    TextView merchantUnionPay;
    @BindView(R.id.merchantBeneficiaryName)
    TextView merchantBeneficiaryName;
    @BindView(R.id.merchantBankName)
    TextView merchantBankName;
    @BindView(R.id.merchantBankAccount)
    EditText merchantBankAccount;
    @BindView(R.id.merchantBranchNumber)
    TextView merchantBranchNumber;
    @BindView(R.id.merchant_number)
    TextView merchantNumber;
    @BindView(R.id.terminal_number)
    TextView terminalNumber;
    @BindView(R.id.mer_simpleName)
    TextView mer_simpleName;
    @BindView(R.id.ll_visible)
    LinearLayout llVisible;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_mer_chant_info;
    }

    @Override
    protected void initViewsAndEvents() {
        prefes = new PreferencesUtil(mContext);
        merChantInter = APIProcxy.getInstance().getmMerChantInfo();
        Map<String, String> map = new TreeMap<>();
        map.put("mobile", prefes.readPrefs(Constant.PREFES_MOBILE));
        map.put("countryCode", prefes.readPrefs(prefes.readPrefs(Constant.PREFES_MOBILE) + Constant.PREFES_COUNTRYCODE));
        map.put("sessionID", prefes.readPrefs(Constant.PREFES_SESSIONID));
        merChantInter.getMerChantInfo(mContext, map, new MerChantInfoListener() {
            @Override
            public void _onNext(QueryMerchantRep queryMerchantRep) {
                if (queryMerchantRep.isOk()) {
                    //获取资料
                    merchantCountry.setText(TextUtils.isEmpty(queryMerchantRep.country) ? "" : queryMerchantRep.country);
                    merchantName.setText(TextUtils.isEmpty(queryMerchantRep.getMerName()) ? "" : queryMerchantRep.getMerName());
                    merchantRelation.setText(TextUtils.isEmpty(queryMerchantRep.getContact()) ? "" : queryMerchantRep.getContact());
                    merchantRelationPhone.setText(TextUtils.isEmpty(queryMerchantRep.getPhoneNumber()) ? "" : queryMerchantRep.getPhoneNumber());
                    merchantContinueRate.setText(TextUtils.isEmpty(queryMerchantRep.getEmvContractRate()) ? "" : queryMerchantRep.getEmvContractRate());
                    merchantUnionPay.setText(TextUtils.isEmpty(queryMerchantRep.getUpopContractRate()) ? "" : queryMerchantRep.getUpopContractRate());
                    merchantBeneficiaryName.setText(TextUtils.isEmpty(queryMerchantRep.getAccountName()) ? "" : queryMerchantRep.getAccountName());
                    merchantBankName.setText(TextUtils.isEmpty(queryMerchantRep.getBankName()) ? "" : queryMerchantRep.getBankName());
                    merchantBankAccount.setText(TextUtils.isEmpty(queryMerchantRep.getCardNum()) ? "" : queryMerchantRep.getCardNum());
                    merchantBranchNumber.setText(TextUtils.isEmpty(queryMerchantRep.getBranchNum()) ? "" : queryMerchantRep.getBranchNum());
                    terminalNumber.setText(TextUtils.isEmpty(queryMerchantRep.getTermCode()) ? "" : queryMerchantRep.getTermCode());
                    merchantNumber.setText(TextUtils.isEmpty(queryMerchantRep.getMerCode()) ? "" : queryMerchantRep.getMerCode());
                }
            }

            @Override
            public void _onError(String error) {
                ToastUtils.showLong(error);
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
