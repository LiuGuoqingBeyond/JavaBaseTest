package com.uppayplugin.unionpay.javabasetes.utils.multilanguage;

import android.content.Context;

import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;

/**
 * 语言配置类
 *
 * @author zh
 */
public class LanguageConfig {

    private PreferencesUtil shardPreferences = null;

    private static LanguageConfig languageConfig = null;

    public static LanguageConfig newInstance(Context context) {
        if(null == languageConfig) {
            languageConfig = new LanguageConfig(context);
        }
        return languageConfig;
    }

    private LanguageConfig(Context context) {
        if (null != context) {
            shardPreferences = new PreferencesUtil(context);
        }
    }

    private static final String LANGUAGE_SELECTED = "language_selected";
    private static final String COUNTRY_SELECTED = "country_selected";

    public static final String LANGUAGE_EN = "en";
    public static final String COUNTRY_ZH = "cn";

    public void setLanguageValue(String language) {
        shardPreferences.writePrefs(LANGUAGE_SELECTED, language);
    }

    public String getLanguageValue() {
        return shardPreferences.readPrefs(LANGUAGE_SELECTED);
    }

    public void setCountryNameValue(String language) {
        shardPreferences.writePrefs(COUNTRY_SELECTED, language);
    }

    public String getCountryNameValue() {
        return shardPreferences.readPrefs(COUNTRY_SELECTED);
    }

}
