package com.uppayplugin.unionpay.javabasetes.utils.device;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;

/**
 * 蓝牙设备管理工具类
 *
 * @project：xzfpos
 * @author：- Richard on 2017/11/24 17:23
 * @email：zhangh@qtopay.cn
 */
public class BlueToothManagerUtils {

    private static final int REQUEST_ENABLE_BT = 1;

    /**
     * 检测手机蓝牙状态
     * @param mContext
     * @return true:蓝牙被打开  false:蓝牙被关闭
     */
    public static boolean queryMobileBluetoothState(Context mContext) {
        try {
            final BluetoothManager bluetoothManager =
                    (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
            BluetoothAdapter adapter = bluetoothManager.getAdapter();
            // 打开蓝牙权限
            return adapter.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 打开手机蓝牙
     */
    public static void openMobileBluetooth(Context mContext) {
        try{
            final BluetoothManager bluetoothManager =
                    (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
            BluetoothAdapter adapter = bluetoothManager.getAdapter();
            // 打开蓝牙权限
            if (!adapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                ((Activity)mContext).startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
