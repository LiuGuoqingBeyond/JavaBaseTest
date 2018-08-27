package com.uppayplugin.unionpay.javabasetes.receiver;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.RxBus.RxBus;
import com.whty.xzfpos.utils.RxBus.RxBusEvent.SearchBluetoothDeviceEvent;

public class BlueToothDeviceReceiver extends BroadcastReceiver {


    public BlueToothDeviceReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Bundle b = intent.getExtras();
        Object[] lstName = b.keySet().toArray();

        // 显示所有收到的消息及其细节
        for (Object aLstName : lstName) {
            String keyName = aLstName.toString();
            Log.d(keyName, String.valueOf(b.get(keyName)));
        }

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {

            BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//            handler.obtainMessage(SharedMSG.BT_DEVICE_FOUND, bluetoothDevice).sendToTarget();
            RxBus.getInstance().post(new SearchBluetoothDeviceEvent("BlueToothDeviceReceiver", bluetoothDevice));

        }

        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            Logger.d("收到连接蓝牙的广播");
        }

        if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
//            RxBus
//            handler.obtainMessage(SharedMSG.BT_DEVICE_DISCONNECTED).sendToTarget();
        }

        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            Logger.d("收到蓝牙状态改变的广播");
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
//				 if(state==BluetoothAdapter.STATE_ON){
//					 SharedMSG.btState=true;
//				 }else if(state==BluetoothAdapter.STATE_OFF){
//					 SharedMSG.btState=false;
//				 }
        }
    }


}
