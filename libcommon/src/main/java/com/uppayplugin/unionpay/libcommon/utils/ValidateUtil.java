package com.uppayplugin.unionpay.libcommon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 验证公共类
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class ValidateUtil {

	/**
	 * 检查中文名输 入是否正确
	 *
	 * @param value
	 * @return
	 */
	public static boolean checkChineseName(String value) {
		return value.matches("^[\u4e00-\u9fa5]+$");
	}

	/**
	 * 手机号码的验证，严格验证
	 * @param mobile 要验证的手机号码
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 电子邮箱的验证
	 * @param strEmail
	 * @return
	 */
	public static boolean isEmail(String strEmail) {
		int count = 0, start = 0;
		int index = strEmail.indexOf("@", start);
		while (index != -1 && start < strEmail.length()) {
			count++;
			start = index + 1;
			index = strEmail.indexOf("@", start);
		}

		if (count != 1) {
			return false;
		} else {
			if (start != strEmail.length()) {
				return true;
			} else {
				return false;
			}
		}

		/*
		 * String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		 * Pattern p = Pattern.compile(strPattern);
		 * Matcher m = p.matcher(strEmail); return m.matches();
		 */
	}

	/**
	 * 判断给定字符串是否空白串。
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isBlank(String input) {
		if (input == null || "".equals(input)) {
			return true;
		}

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}
}
