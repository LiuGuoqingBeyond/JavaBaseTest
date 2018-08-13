package com.uppayplugin.unionpay.javabasetes.utils.multilanguage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.uppayplugin.unionpay.javabasetes.MApplication;
import com.uppayplugin.unionpay.javabasetes.activity.MainActivity;

import java.util.Locale;

/**
 * @project：MultiLanguage-master
 * @author：- octopus on 2017/11/18 23:17
 * @email：zhanghuan@xinguodu.com
 */
public class MultiLanguageUtils {

    /**
     * 获取选择的语言类型
     *
     * @return
     */
    public static LanguageCountry getSelectLanguage(Context context) {
        LanguageConfig config = LanguageConfig.newInstance(context);
        LanguageCountry languageCountry = null;
        try {
            String langStr = config.getLanguageValue();
            if(TextUtils.isEmpty(langStr) || langStr.equals(LanguageCountry.LANGUAGE_OPTION_DEFAULT)) {
                languageCountry = new LanguageCountry(context, LanguageCountry.LANGUAGE_OPTION_DEFAULT, LanguageCountry.COUNTRY_OPTION_DEFAULT);
            } else if(langStr.equals(LanguageCountry.LANGUAGE_OPTION_ZH)) {
                languageCountry = new LanguageCountry(context, LanguageCountry.LANGUAGE_OPTION_ZH, LanguageCountry.COUNTRY_OPTION_CN);
            } else if(langStr.equals(LanguageCountry.LANGUAGE_OPTION_EN)) {
                languageCountry = new LanguageCountry(context, LanguageCountry.LANGUAGE_OPTION_EN, LanguageCountry.LANGUAGE_OPTION_EN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return languageCountry;
    }

    /**
     * 选择语言
     * @param context
     * @param languageCountry
     * @param isAutoSet
     */
    public static void selectLanguage(Context context,LanguageCountry languageCountry, boolean isAutoSet) {
        LanguageConfig config = LanguageConfig.newInstance(context);
        if (isAutoSet) {
            String langStr = Locale.getDefault().getLanguage();
            String countryStr = Locale.getDefault().getCountry();
            // 暂默认非英文即中文,后期有新需求再改
            if(langStr.equals(LanguageConfig.LANGUAGE_EN)) {
                languageCountry = new LanguageCountry(context, LanguageCountry.LANGUAGE_OPTION_EN, LanguageCountry.LANGUAGE_OPTION_EN);
                MApplication.setEnglishSystem(true);
            } else {
                languageCountry = new LanguageCountry(context, LanguageCountry.LANGUAGE_OPTION_ZH, LanguageCountry.COUNTRY_OPTION_CN);
                MApplication.setEnglishSystem(false);
            }
        }
        if (null != languageCountry) {
            config.setLanguageValue(languageCountry.getLanguage());
            config.setCountryNameValue(languageCountry.getCountry());
            if(languageCountry.getLanguage().equals(LanguageConfig.LANGUAGE_EN)) {
                MApplication.setEnglishSystem(true);
            } else {
                MApplication.setEnglishSystem(false);
            }
        }
        setLanguage(languageCountry, context);
    }

    /**
     * 退回到主界面
     * @param context
     */
    public static void finishNowActivity(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity)context).finish();
        /*android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);*/
    }

    /**
     * 更新app当前语言
     */
    public static void setLanguage(LanguageCountry languageCountry, Context context) {
        if (null == languageCountry || null == context) {
            return;
        }
        Locale locale = new Locale(languageCountry.getLanguage(), languageCountry.getCountry());
        Resources res = context.getResources();
        if (null != res) {
            Configuration config = res.getConfiguration();
            if (null != config) {
                config.locale = locale;
            }
            DisplayMetrics dm = res.getDisplayMetrics();
            if (null != config && null != dm) {
                res.updateConfiguration(config, dm);
            }
        }
    }

    /**
     * 更新系统语言
     */
    public static void updateSystemLanguage() {
        if(MApplication.isEnglishSystem()) {
            setLanguage(new LanguageCountry(MApplication.getAppContext(), LanguageCountry.LANGUAGE_OPTION_EN, LanguageCountry.LANGUAGE_OPTION_EN),MApplication.getAppContext());
        } else {
            setLanguage(new LanguageCountry(MApplication.getAppContext(), LanguageCountry.LANGUAGE_OPTION_ZH, LanguageCountry.COUNTRY_OPTION_CN),MApplication.getAppContext());
        }
    }

}
