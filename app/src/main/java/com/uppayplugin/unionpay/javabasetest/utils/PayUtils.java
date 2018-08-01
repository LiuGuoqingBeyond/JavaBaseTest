package com.uppayplugin.unionpay.javabasetest.utils;


import com.uppayplugin.unionpay.javabasetest.MApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;


public class PayUtils {

	public static String trimToEmpty(String s) {
		return s == null ? "" : s.trim();
	}

	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}

	public static boolean isBlank(String s) {
		return (s == null || s.trim().length() == 0);
	}

	public static String getCurrentTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}


	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(
				Character.toTitleCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}

	public static String getProperty(Object instance, String propertyName) {
		String value = null;
		try {
			String methodName = "get" + PayUtils.capitalize(propertyName);
			Method method = instance.getClass().getMethod(methodName);
			value = String.class.cast(method.invoke(instance));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return value;
	}

	public static String getPropFilename(Class<?> clazz) {
		return clazz.getSimpleName() + ".properties";
	}

//	public static String createBackStr(String[] valueVo, String[] keyVo) {
//
//		Map<String, String> map = new TreeMap<String, String>();
//		for (int i = 0; i < keyVo.length; i++) {
//			map.put(keyVo[i], valueVo[i]);
//		}
//		map.put("signature", signMap(map, QuickPayConf.signType));
//		// map.put("signature", "7787wew54e5w8ra4d5a484ra4");
//		map.put("signMethod", QuickPayConf.signType);
//		System.out.println(joinMapValue(map, '&'));
//		return joinMapValue(map, '&');
//	}

//	public static String createBackStrForBackTrans(String[] valueVo,
//			String[] keyVo) {
//
//		Map<String, String> map = new TreeMap<String, String>();
//		for (int i = 0; i < keyVo.length; i++) {
//			map.put(keyVo[i], valueVo[i]);
//		}
//		map.put("signature", signMap(map, QuickPayConf.signType));
//		map.put("signMethod", QuickPayConf.signType);
//		return joinMapValueBySpecial(map, '&');
//	}

//	/**
//	 * 生成加密钥
//	 *
//	 * @param map
//	 * @param secretKey
//	 *            商城密钥
//	 * @return
//	 */
//	private static String signMap(Map<String, String> map, String signMethod) {
//		String joinString = joinMapValue(map, '&');
//		System.out.println(">>>signMethod: " + signMethod);
//		System.out.println(">>>signData: " + joinString);
//
//		String signature = null;
//
//		if ("RSA".equalsIgnoreCase(signMethod)) {
//			String signData = sha1(joinString, QuickPayConf.charset);
//			// signature = signWithRSA(signData);
//		} else {
//			String strBeforeMd5 = joinString + "&"
//					+ md5(PropertyConstant.SECURITYKEY, QuickPayConf.charset);
//
//			System.out.println(strBeforeMd5);
//			signature = md5(strBeforeMd5, QuickPayConf.charset);
//		}
//
//		return signature;
//	}

	public static String joinMapValue(Map<String, String> map, char connector) {
		StringBuffer b = new StringBuffer();
		if(null != map) {
			// FIXME: 2017/11/18 zhanghuan 后面在这个地方拼接一个language字段
			map.put("language", MApplication.isEnglishSystem()?"1":"0");
		}
		boolean first = true;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (!first) {
				b.append(connector);
			}
			first = false;

			b.append(entry.getKey());
			b.append('=');
			if (entry.getValue() != null) {
				b.append(trimToEmpty(entry.getValue()));
			}
		}
		return b.toString();
	}
	public static String sha(String str) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer strBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				strBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				strBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return strBuff.toString();
	}

//	public static String joinMapValueBySpecial(Map<String, String> map,
//			char connector) {
//		StringBuffer b = new StringBuffer();
//		boolean first = true;
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			if (!first) {
//				b.append(connector);
//			}
//			first = false;
//			b.append(entry.getKey());
//			b.append('=');
//			if (entry.getValue() != null) {
//				try {
//					b.append(java.net.URLEncoder.encode(trimToEmpty(entry
//							.getValue()), QuickPayConf.charset));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return b.toString();
//	}

//	public static String joinMapValueBySpecial2(Map<String, String> map,
//			String connector) {
//		StringBuffer b = new StringBuffer();
//		boolean first = true;
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			if (!first) {
//				b.append(connector);
//			}
//			first = false;
//			b.append(entry.getKey());
//			b.append('=');
//			if (entry.getValue() != null) {
//				try {
//					b.append(java.net.URLEncoder.encode(trimToEmpty(entry
//							.getValue()), QuickPayConf.charset));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return b.toString();
//	}

	/**
	 * get the md5 hash of a string
	 *
	 * @param str
	 * @return
	 */
	private static String md5(String str, String encoding) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes(encoding));
		} catch (NoSuchAlgorithmException e) {

			return str;
		} catch (UnsupportedEncodingException e) {
			return str;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	public static String doGetMerchant(String strURL, String req) {
		String result = null;
		System.out.println("send URL>>:" + strURL + "?" + req);
		try {
			URL url = new URL(strURL + "?" + req );
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.setInstanceFollowRedirects(true);
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.setDefaultUseCaches(false);
			http.setDoOutput(true);

			http.connect();// 连接
			InputStream in = http.getInputStream();// 返回流
			result = convertStreamToString(in);
		} catch (Exception e) {
			System.out.println(e);
			result = "";
		}
		return result;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 生成自动提交到银联系统的表单数据
	 *
	 * @param 提交的银联系统地址
	 * @param 表单中请求数据map
	 * @return
	 */
	private static String generateAutoSubmitForm(String actionUrl,
			Map<String, String> paramMap) {
		StringBuilder html = new StringBuilder();
		html
				.append("<script language=\"javascript\">window.onload=function(){document.pay_form.submit();}</script>\n");
		html.append("<form id=\"pay_form\" name=\"pay_form\" action=\"")
				.append(actionUrl).append("\" method=\"post\">\n");

		for (String key : paramMap.keySet()) {
			String value = paramMap.get(key);
			value = (value == null) ? "" : value;
			html.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
					+ key + "\" value=\"" + value + "\">\n");
		}
		html.append("</form>\n");
		return html.toString();
	}

	private static PrivateKey getPrivateKey(String certName, String passwd) {

		try {
			KeyStore ks = KeyStore.getInstance("pkcs12");
			InputStream is = PayUtils.class.getResourceAsStream("/" + certName);

			char[] nPassword = null;
			if ((passwd == null) || passwd.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = passwd.toCharArray();
			}
			ks.load(is, nPassword);
			is.close();

			Enumeration<String> e = ks.aliases();
			String keyAlias = null;
			if (e.hasMoreElements()) // we are reading just one certificate.
			{
				keyAlias = (String) e.nextElement();
			}

			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			// Certificate cert = ks.getCertificate(keyAlias);

			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sha1(String str, String encoding) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(str.getBytes(encoding));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer strBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				strBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				strBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return strBuff.toString();
	}

}
