package com.uppayplugin.unionpay.libcommon.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统工具类
 *
 * @author Eddy 2014-09-04
 *
 */
public class SystemUtil {

	public SystemUtil() {

	}

	/**
	 * 获取手机IMEI
	 *
	 * @param context
	 * @return
	 */
	public static String getPhoneIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if (imei == null) {// 当获取不到id时，赋值，否则json不能存入null
			imei = ""; // 取不到IMEI则传空
		}
		return imei;
	}

	/**
	 * 获取手机号码
	 *
	 * @param context
	 * @return
	 */
	public static String getPhoneNum(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getLine1Number();
	}

	public long getSystemTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取字节数
	 *
	 * @param str
	 * @return
	 */
	public static int count(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		int count = 0;
		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			count += (chs[i] > 0xff) ? 2 : 1;
		}
		return count;
	}

	/**
	 * 长度不足，补字符串
	 *
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String addForStr(String str, int strLength, String s) {
		int strLen = count(str);
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			// sb.append(s).append(str);// 左(前)补
			sb.append(str).append(s);// 右(后)补
			str = sb.toString();
			strLen = count(str);
		}
		return str;
	}

	/**
	 * 获取交易日期
	 * @return
	 */
	public static String getTransDate() {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		return formatDate.format(now);
	}

	/**
	 * 获取交易时间
	 *
	 * @return
	 */
	public static String getTransTime() {
		SimpleDateFormat formatDate = new SimpleDateFormat("HHmmss");
		Date now = new Date();
		return formatDate.format(now);
	}

	/**
	 * 金额字符串格式化
	 *
	 * @param paramString
	 * @return
	 */
	public static String fomartAmount(String paramString) {

		// 空金额
		if (paramString == null || paramString.length() == 0)
			return "0.00";
		// 重新格式化
		if (paramString.length() < 12) {
			String zero = "00000000000";
			paramString = (zero.substring(0, 12 - paramString.length()) + paramString);
		}

		StringBuffer localStringBuffer = new StringBuffer();
		int i = 0;
		int j = 0;
		for (j = 0; j < paramString.length(); ++j) {
			if (j + 2 == paramString.length()) {
				if (localStringBuffer.length() == 0) {
					localStringBuffer.append('0');
					i = 1;
				}
				localStringBuffer.append('.');
			}
			char c = paramString.charAt(j);
			if ((j == 0) && (c == 'D')) {
				localStringBuffer.append('-');
			} else {
				if ((j == 0) && (c == 'C'))
					continue;
				if ((i == 0) && (c == '0'))
					continue;
				if ((i == 0) && (c >= '1') && (c <= '9')) {
					localStringBuffer.append(c);
					i = 1;
				} else {
					if ((i == 0) || (c < '0') || (c > '9'))
						continue;
					localStringBuffer.append(c);
				}
			}
		}
		return localStringBuffer.toString();
	}
}
