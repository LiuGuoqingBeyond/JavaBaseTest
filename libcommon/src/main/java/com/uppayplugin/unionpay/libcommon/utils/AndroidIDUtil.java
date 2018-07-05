package com.uppayplugin.unionpay.libcommon.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

/**
 * @description Android ID类
 * @author King_wangyao
 * @date 2014-5-20
 * @version 1.0.0
 *
 */
public class AndroidIDUtil {

	private Context mContext;

	public AndroidIDUtil(Context context) {
		this.mContext = context;
	}

	/**
	 * 获取手机IMEI信息（许可：android.permission.READ_PHONE_STATE）
	 * @return
	 */
	public String getIMEI() {
		TelephonyManager TelephonyMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
		return imei;
	}

	/**
	 * 获取物理唯一标识码
	 * @return
	 */
	public String getUnique_ID() {
		String devIDShort = "35" + // we make this look like a valid IMEI
		Build.BOARD.length()%10 +
		Build.BRAND.length()%10 +
		Build.CPU_ABI.length()%10 +
		Build.DEVICE.length()%10 +
		Build.DISPLAY.length()%10 +
		Build.HOST.length()%10 +
		Build.ID.length()%10 +
		Build.MANUFACTURER.length()%10 +
		Build.MODEL.length()%10 +
		Build.PRODUCT.length()%10 +
		Build.TAGS.length()%10 +
		Build.TYPE.length()%10 +
		Build.USER.length()%10 ; // 13 digits
		return devIDShort;
	}

	/**
	 * 获取AndroidID
	 * @return
	 */
	public String getANDROID_ID() {
		String androidID = Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID);
		return androidID;
	}

	/**
	 * 获取手机网络地址（许可：android.permission.ACCESS_WIFI_STATE）
	 * @return
	 */
	public String getWLAN_MAC() {
		WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
		String wlan_mac = wm.getConnectionInfo().getMacAddress();
		return wlan_mac;
	}

	/**
	 * 获取手机蓝牙地址（许可：android.permission.BLUETOOTH）
	 * @return
	 */
	public String getBluetooth_MAC() {
		BluetoothAdapter m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		String bt_mac = m_BluetoothAdapter.getAddress();
		return bt_mac;
	}
}
