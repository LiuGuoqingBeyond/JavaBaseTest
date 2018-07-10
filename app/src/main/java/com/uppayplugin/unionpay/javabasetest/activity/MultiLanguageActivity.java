package com.uppayplugin.unionpay.javabasetest.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.axl.android.frameworkbase.net.utils.ProgressSubscriber;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetest.Impl.PersonalInfoRequestImpl;
import com.uppayplugin.unionpay.javabasetest.MApplication;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.config.ConstantUtils;
import com.uppayplugin.unionpay.javabasetest.entity.request.SetMultiLangReqModel;
import com.uppayplugin.unionpay.javabasetest.entity.response.BaseRepModel;
import com.uppayplugin.unionpay.javabasetest.utils.PayUtils;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetest.utils.dialog.DialogShowUtils;
import com.uppayplugin.unionpay.javabasetest.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetest.utils.language.LanguageConstants;
import com.uppayplugin.unionpay.javabasetest.utils.mapbean.TransMapToBeanUtils;
import com.uppayplugin.unionpay.javabasetest.utils.multilanguage.LanguageCountry;
import com.uppayplugin.unionpay.javabasetest.utils.multilanguage.MultiLanguageUtils;
import com.uppayplugin.unionpay.javabasetest.utils.net.NetUtil;
import com.uppayplugin.unionpay.libcommon.rsa.RSACoder;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class MultiLanguageActivity extends ToolBarActivity {

    @BindView(R.id.ray_multi_chinese)
    RelativeLayout ray_multi_chinese;
    @BindView(R.id.ray_multi_english)
    RelativeLayout ray_multi_english;
    @BindView(R.id.ray_multi_unsimplified)
    RelativeLayout ray_multi_unsimplified;

    @BindView(R.id.iv_multi_chinese)
    ImageView iv_multi_chinese;
    @BindView(R.id.iv_multi_english)
    ImageView iv_multi_english;
    @BindView(R.id.iv_multi_unsimplified)
    ImageView iv_multi_unsimplified;

    private Context mContext;

    private LanguageCountry languageCountry;
    private boolean autoLanguage = true;

    private PreferencesUtil preferencesUtil;
    // 语言标识  0:简体  1:英语 2:繁体
    private String merLanguage = "0";

    private String selectLanuage = "";

    @Override
    protected void initToolBar() {
        mToolbar.setCenterTitle(getString(R.string.multi_language));
        //使用mToolBar 设置标题  左上角 返回按钮已设置返回功能。
        mToolbar.getRightText().setText(R.string.activity_finish);
        mToolbar.getRightText().setOnClickListener(view -> {
            if (NetUtil.checkNet(mContext)) {
                //请求语言
                setMultiLanguage(merLanguage);
            }
        });
    }

    private void setMultiLanguage(String merLanguage) {
        Map<String, String> map = new TreeMap<String, String>();
        String countryCode = preferencesUtil.readPrefs(preferencesUtil.readPrefs(Constant.PREFES_MOBILE) + Constant.PREFES_COUNTRYCODE);
        countryCode = TextUtils.isEmpty(countryCode) ? "86" : countryCode;
        map.put("countryCode", countryCode);
        map.put("mobile", preferencesUtil.readPrefs(Constant.PREFES_MOBILE));
        map.put("sessionID", preferencesUtil.readPrefs(Constant.PREFES_SESSIONID));
        map.put("txnType", "50");
        map.put("merLanguage", merLanguage);
        String str = PayUtils.joinMapValue(map, '&');
        String sign = RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n", "");
        map.put("signature", sign);
        String result = mGson.toJson(map);
        Logger.e("result:" + result);

        PersonalInfoRequestImpl.setMultiLanguage((SetMultiLangReqModel) TransMapToBeanUtils.mapToBean(map, SetMultiLangReqModel.class))
                .subscribe(new ProgressSubscriber<BaseRepModel>(mContext) {

                    @Override
                    protected void _onNext(BaseRepModel responseModel) {
                        Logger.e("_onNext-> status:" + responseModel.getStatus());
                        if (responseModel.isOk()) {
                            // MultiLanguageUtils.selectLanguage(mContext, languageCountry, autoLanguage);
                            preferencesUtil.writePrefs("language_selected", selectLanuage);
//                            MApplication.getInstance().isHomeHiddenFragment = false;//重置标识为false，不然回到主页，标识还是true，导致切换语言之后，在主页没有显示推送
                            ConstantUtils.needQueryUserAccount = true;
                            MultiLanguageUtils.finishNowActivity(mContext);
                        } else if (responseModel.needLogin()) {
                            DialogShowUtils.showReloginDailog(mContext, responseModel.msg);
                        } else {
                            ToastUtils.showShort(responseModel.getMsg());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        Logger.e("_onError-> message:" + message);
                        ToastUtils.showShort(getString(R.string.service_exception));
                    }
                });
    }

    @OnClick({R.id.ray_multi_chinese, R.id.ray_multi_english,R.id.ray_multi_unsimplified})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ray_multi_chinese:
                merLanguage = "0";
                iv_multi_chinese.setVisibility(View.VISIBLE);
                iv_multi_english.setVisibility(View.GONE);
                iv_multi_unsimplified.setVisibility(View.GONE);
                // languageCountry = new LanguageCountry(this, LANGUAGE_OPTION_ZH, LanguageCountry.COUNTRY_OPTION_CN);

                selectLanuage = LanguageConstants.SIMPLIFIED_CHINESE;

                autoLanguage = false;
                break;
            case R.id.ray_multi_english:
                merLanguage = "1";
                iv_multi_chinese.setVisibility(View.GONE);
                iv_multi_unsimplified.setVisibility(View.GONE);
                iv_multi_english.setVisibility(View.VISIBLE);
                // languageCountry = new LanguageCountry(this, LANGUAGE_OPTION_EN);

                selectLanuage = LanguageConstants.ENGLISH;

                autoLanguage = false;
                break;
            // FIXME: 2017/12/8 Lgq 新增繁体中文
            case R.id.ray_multi_unsimplified:
                merLanguage = "2";
                iv_multi_chinese.setVisibility(View.GONE);
                iv_multi_english.setVisibility(View.GONE);
                iv_multi_unsimplified.setVisibility(View.VISIBLE);
                // languageCountry = new LanguageCountry(this, LANGUAGE_OPTION_TW, LanguageCountry.COUNTRY_OPTION_TW);

                selectLanuage = LanguageConstants.TRADITIONAL_CHINESE;

                autoLanguage = false;
                break;
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_multi_language;
    }

    @Override
    protected void initViewsAndEvents() {
        mContext = MultiLanguageActivity.this;

        preferencesUtil = new PreferencesUtil(this);

        selectLanuage = preferencesUtil.readPrefs("language_selected");
        switch (selectLanuage) {
            case "":
                iv_multi_chinese.setVisibility(View.VISIBLE);
                iv_multi_english.setVisibility(View.GONE);
                iv_multi_unsimplified.setVisibility(View.GONE);
                break;
            case LanguageConstants.ENGLISH:
                iv_multi_chinese.setVisibility(View.GONE);
                iv_multi_unsimplified.setVisibility(View.GONE);
                iv_multi_english.setVisibility(View.VISIBLE);
                break;
            case LanguageConstants.SIMPLIFIED_CHINESE:
                iv_multi_chinese.setVisibility(View.VISIBLE);
                iv_multi_english.setVisibility(View.GONE);
                iv_multi_unsimplified.setVisibility(View.GONE);
                break;
            case LanguageConstants.TRADITIONAL_CHINESE:
                iv_multi_chinese.setVisibility(View.GONE);
                iv_multi_english.setVisibility(View.GONE);
                iv_multi_unsimplified.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
