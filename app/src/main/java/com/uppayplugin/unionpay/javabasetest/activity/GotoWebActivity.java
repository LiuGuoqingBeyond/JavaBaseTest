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
        btnText.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("countryCode",etCountryCode.getText().toString().trim());
            bundle.putString("mobile",etMobile.getText().toString().trim());
            openActivity(WebViewJSActivity.class,bundle);
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
