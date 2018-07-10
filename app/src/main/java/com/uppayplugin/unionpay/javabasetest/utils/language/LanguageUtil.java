package com.uppayplugin.unionpay.javabasetest.utils.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;


import com.uppayplugin.unionpay.javabasetest.MApplication;

import java.util.Locale;

public class LanguageUtil {

    public static void applyLanguage(Context context, String newLanguage) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = SupportLanguageUtil.getSupportLanguage(newLanguage);

        if(locale.getLanguage().equals("en")) {
            MApplication.setEnglishSystem(true);
            MApplication.setChineseSystem(false);
            MApplication.setChineseTwSystem(false);
        } else if(locale.getLanguage().equals("zh"))  {
            if(locale.getCountry().toLowerCase().equals("cn")) {
                MApplication.setEnglishSystem(false);
                MApplication.setChineseSystem(true);
                MApplication.setChineseTwSystem(false);
            } else {
                MApplication.setEnglishSystem(false);
                MApplication.setChineseSystem(false);
                MApplication.setChineseTwSystem(true);
            }
        } else {
            MApplication.setEnglishSystem(false);
            MApplication.setChineseSystem(false);
            MApplication.setChineseTwSystem(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
            DisplayMetrics dm = resources.getDisplayMetrics();
            resources.updateConfiguration(configuration, dm);
        }
    }

    public static Context attachBaseContext(Context context, String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context, language);
        } else {
            applyLanguage(context, language);
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context, String language) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale;
        if (TextUtils.isEmpty(language)) {//如果没有指定语言使用系统首选语言
            locale = SupportLanguageUtil.getSystemPreferredLanguage();
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = SupportLanguageUtil.getSupportLanguage(language);
        }
        if(locale.getLanguage().equals("en")) {
            MApplication.setEnglishSystem(true);
            MApplication.setChineseSystem(false);
            MApplication.setChineseTwSystem(false);
        } else if(locale.getLanguage().equals("zh"))  {
            if(locale.getCountry().toLowerCase().equals("cn")) {
                MApplication.setEnglishSystem(false);
                MApplication.setChineseSystem(true);
                MApplication.setChineseTwSystem(false);
            } else {
                MApplication.setEnglishSystem(false);
                MApplication.setChineseSystem(false);
                MApplication.setChineseTwSystem(true);
            }
        } else {
            MApplication.setEnglishSystem(false);
            MApplication.setChineseSystem(false);
            MApplication.setChineseTwSystem(true);
        }
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
}
