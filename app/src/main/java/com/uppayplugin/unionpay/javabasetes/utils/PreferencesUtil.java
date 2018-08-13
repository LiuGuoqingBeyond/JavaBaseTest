package com.uppayplugin.unionpay.javabasetes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.bean.SharedInfo;

import java.util.Map;
import java.util.Set;


/**
 * @description 配置环境Preferences类
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class PreferencesUtil {

	private static final String TAG = PreferencesUtil.class.getSimpleName();

	private Context mContext;

	private static final String PREFES_NAME = SharedInfo.PACKAGE_NAME;// 配置文件名称

	private static final String PREFES_DEFAULT_VALUE = "";// 配置文件默认值

	public PreferencesUtil(Context context) {
		this.mContext = context;
	}

	/**
	 * 以Preferences文件方式存储信息
	 *
	 * @param key
	 * @param value
	 */
	public void writePrefs(String key, String value) {
		// 指定操作的文件名称
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFES_NAME, Activity.MODE_PRIVATE);
		// 编辑文件
		SharedPreferences.Editor edit = sharedPreferences.edit();
		// 保存内容
		try {
			edit.putString(key, value);
		} catch (Exception e) {
			Logger.e(TAG, "AESEncryptor ocure error:" + e.getMessage());
		}
		// 提交更新
		edit.commit();
	}

	/**
	 * 以Preferences文件方式读取信息
	 *
	 * @param key
	 * @return
	 */
	public String readPrefs(String key) {
		// 指定操作的文件名称
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFES_NAME, Activity.MODE_PRIVATE);
		// 指定读取字符的名称
		String value = sharedPreferences.getString(key, PREFES_DEFAULT_VALUE);
		return value;
	}

	public void getAll() {
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFES_NAME, Activity.MODE_PRIVATE);
		Map<String,?> map =  sharedPreferences.getAll();
		Set<String> s = map.keySet();
		Log.d("t________size " , s.size()+"");
		for(String t : s)
			Log.d("t________" , t);


	}

	/**
	 * 清除Preferences文件记录
	 */
	public void clearPrefs() {
		// 指定操作的文件名称
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFES_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
}
