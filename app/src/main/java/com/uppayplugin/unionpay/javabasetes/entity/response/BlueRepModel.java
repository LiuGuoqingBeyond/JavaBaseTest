package com.uppayplugin.unionpay.javabasetes.entity.response;

import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;

/**
 * User: LiuGuoqing
 * Data: 2018/8/27 0027.
 */

public class BlueRepModel {
    ArrayList<BluetoothDevice> blueList;

    public ArrayList<BluetoothDevice> getBlueList() {
        return blueList;
    }

    public void setBlueList(ArrayList<BluetoothDevice> blueList) {
        this.blueList = blueList;
    }
}
