package com.uppayplugin.unionpay.javabasetes.utils.net;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.uppayplugin.unionpay.javabasetes.R;


public class NetUtil {

	/**
	 * 判断网络
	 *
	 * @param context
	 * @return
	 */
	public static boolean checkNet(Context context) {// // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// // 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// // 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}else {
					setNetwork(context);
				}
			}
		} catch (Exception e) {
			System.out.println("Check network error !");
			return false;
		}
		return false;
	}

	/**
	 * 网络未连接时，调用设置方法
	 */
	public static void setNetwork(Context context) {
		// TODO Auto-generated method stub..
		//Toast.makeText(this, "wifi is closed!", Toast.LENGTH_SHORT).show();

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(context.getString(R.string.net_tips));
		builder.setMessage(context.getString(R.string.net_set));
		builder.setPositiveButton(context.getString(R.string.text_set), (dialog, which) -> {
			// TODO Auto-generated method stub
			Intent intent = null;
			/**
			 * 判断手机系统的版本！如果API大于10 就是3.0+ 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
			 */
			intent = new Intent(
					Settings.ACTION_WIFI_SETTINGS);
			context.startActivity(intent);
		});
		builder.setNegativeButton(context.getString(R.string.text_cancel), (dialog, which) -> {
			// TODO Auto-generated method stub
		});
		builder.create();
		builder.show();
	}

}
