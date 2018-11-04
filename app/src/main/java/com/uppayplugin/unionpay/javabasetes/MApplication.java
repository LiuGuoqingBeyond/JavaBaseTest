package com.uppayplugin.unionpay.javabasetes;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.axl.android.frameworkbase.BaseApplication;
import com.axl.android.frameworkbase.net.HttpEngine;
import com.axl.android.frameworkbase.utils.PreferencesUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.utils.language.LanguageUtil;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by admin on 2017/8/29.
 */

public class MApplication extends BaseApplication {

    private PreferencesUtil preferencesUtil;
    private static MApplication instance;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        MultiDex.install(this);
        HttpEngine.init(BuildConfig.BASEURL);
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        // initMultiLanguage();

        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);

        try {
            // 获取当前应用包名、版本名称、版本号
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            // 当前应用的包名
            Constant.PACKAGE_NAME = info.packageName;
        } catch (Exception e) {
            Logger.d("MApplication", e.getMessage());
        }

        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setAppVersion(BuildConfig.VERSION_NAME);

        //初始化bugly
//        CrashReport.initCrashReport(getApplicationContext(), "2d025c7888", false, strategy);

    }

    public static MApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        preferencesUtil = new PreferencesUtil(newBase);
        String selectedLanguage = preferencesUtil.readPrefs("language_selected");
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, selectedLanguage));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        preferencesUtil = new PreferencesUtil(this);
        String selectedLanguage = preferencesUtil.readPrefs("language_selected");
        LanguageUtil.attachBaseContext(this, selectedLanguage);
        super.onConfigurationChanged(newConfig);
    }

    public static Context getContext() {
        return mContext;
    }

}
