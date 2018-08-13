package com.uppayplugin.unionpay.javabasetes.utils.net;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @description 网络判断公共类
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class NetWorkUtil {

	/**
	 * 判断是否有网络连接
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断WIFI网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断MOBILE网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 获取当前的网络状态： -1：没有网络 1：WIFI网络2：WAP网络3：NET网络
	 * @param context
	 * @return
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * GPS是否打开
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = locationManager.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * 判断服务器链接
	 *
	 * @return
	 */
	public static boolean isConnect(String httpURL) {
		boolean fing = false;
		HttpURLConnection connection = null;

		try {
			URL url = new URL(httpURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(1000 * 8);
			if (connection.getResponseCode() == 200) {
				fing = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		return fing;
	}

}
