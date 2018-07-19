package com.uppayplugin.unionpay.javabasetest.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.view.EditTextWithDEL;

import butterknife.BindView;

public class GotoWebActivity extends ToolBarActivity {
    @BindView(R.id.et_countryCode)
    EditTextWithDEL etCountryCode;
    @BindView(R.id.et_mobile)
    EditTextWithDEL etMobile;
    @BindView(R.id.btn_text)
    Button btnText;
    private String countryCode;
    private String mobile;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goto_web;
    }

    @Override
    protected void initViewsAndEvents() {
        countryCode = etCountryCode.getText().toString().trim();
        mobile = etMobile.getText().toString().trim();
        btnText.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("countryCode",countryCode);
            bundle.putString("mobile",mobile);
            openActivity(WebViewJSActivity.class,bundle);
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
