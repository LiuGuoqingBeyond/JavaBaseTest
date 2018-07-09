package com.uppayplugin.unionpay.javabasetest.utils.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	private Context context;
	public static final String NAME = "soccisConfig";
    private static SharedPreferencesHelper sHelper = null;

	private SharedPreferencesHelper() {

	}
	public static SharedPreferencesHelper getInstance() {
		if (sHelper == null) {
			sHelper = new SharedPreferencesHelper();
		}
		return sHelper;
	}

	public SharedPreferencesHelper init(Context context) {
		this.context = context;
		this.sp = this.context.getSharedPreferences(NAME, 0);
		editor = this.sp.edit();
		return this;
	}

	public void putStringValue(String key, String value) {
		editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getStringValue(String key) {
		return sp.getString(key, null);
	}

	public void putBooleanValue(String key, Boolean value) {
		editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getBooleanValue(String key) {
		return sp.getBoolean(key, false);
	}

	public void clear() {
		editor.clear().commit();
	}
}
