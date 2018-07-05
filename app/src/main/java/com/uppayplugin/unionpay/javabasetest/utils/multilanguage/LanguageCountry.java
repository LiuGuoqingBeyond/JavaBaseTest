package com.uppayplugin.unionpay.javabasetest.utils.multilanguage;

import android.content.Context;
import android.text.TextUtils;

import com.uppayplugin.unionpay.javabasetest.R;

/**
 * 语言配置类
 *
 * @author zh
 */
public class LanguageCountry {
    // 语言
    public static final String LANGUAGE_OPTION_DEFAULT = "language_default";
    public static final String LANGUAGE_OPTION_EN = "en";

    public static final String LANGUAGE_OPTION_ZH = "zh";

    // 国家地区
    public static final String COUNTRY_OPTION_DEFAULT = "country_default";
    public static final String COUNTRY_OPTION_CN = "CN";

    private String mLanguage;
    private String mCountry = "";
    private String mLanguageName = "";
    private Context mContext = null;

    public LanguageCountry(Context context, String language) {
        mLanguage = language;
        mContext = context;
        matchLanguageName();
    }

    // 同语种但不同国家的语言，需要传入country参数，
    // 例如language：LANGUAGE_OPTION_ZH，country：COUNTRY_OPTION_CN
    public LanguageCountry(Context context, String language, String country) {
        mLanguage = language;
        if (null == country) country = "";
        mCountry = country;
        mContext = context;
        matchLanguageName();
    }

    private void matchLanguageName() {
        mLanguageName = getStringOfLanguageName(R.string.multi_language_english, mContext.getString(R.string.multi_language_english));
        if (!TextUtils.isEmpty(mLanguage)) {
            if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_ZH)) {
                mLanguageName = getStringOfLanguageName(R.string.multi_language_chinese, mContext.getString(R.string.multi_language_chinese));
            }
        }
        // 如果上面匹配不到，则是默认的英语（包括英语国家和不支持的语种），所以将mLanguage和mCountry置为英语
        if (getStringOfLanguageName(R.string.multi_language_english, mContext.getString(R.string.multi_language_english)).equalsIgnoreCase(mLanguage)) {
            mLanguage = LANGUAGE_OPTION_EN;
            mCountry = "";
        }
    }

    private String getStringOfLanguageName(int id, String defaultStr) {
        try {
            return mContext.getString(id);
        } catch (Exception e) {
            return defaultStr;
        }
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getLanguageName() {
        return mLanguageName;
    }

}
