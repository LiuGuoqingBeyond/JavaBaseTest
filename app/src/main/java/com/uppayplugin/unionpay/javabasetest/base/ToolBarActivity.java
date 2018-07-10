package com.uppayplugin.unionpay.javabasetest.base;

import android.content.Context;
import android.os.Bundle;

import com.axl.android.frameworkbase.ui.AbsBaseActivity;
import com.axl.android.frameworkbase.widget.BaseToolbar;
import com.google.gson.Gson;
import com.uppayplugin.unionpay.javabasetest.utils.language.LanguageUtil;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/10/24
 * Time: 16:46
 */

public abstract class ToolBarActivity extends AbsBaseActivity {


    protected BaseToolbar mToolbar;
    protected Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGson = new Gson();
        mToolbar = new BaseToolbar(mContentView, this);
        mToolbar.setDefaultNavigation();
        initToolBar();


    }

    protected abstract void initToolBar();

    @Override
    protected void attachBaseContext(Context newBase) {
        com.axl.android.frameworkbase.utils.PreferencesUtil preferencesUtil = new com.axl.android.frameworkbase.utils.PreferencesUtil(newBase);
        String selectedLanguage = preferencesUtil.readPrefs("language_selected");
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, selectedLanguage));
    }
}
