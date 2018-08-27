package com.whty.xzfpos.utils.RxBus.RxBusEvent

import android.bluetooth.BluetoothDevice

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2018/3/13
 * Time: 11:44
 */
class SearchBluetoothDeviceEvent(from: String, entity: BluetoothDevice) : BaseEvent<BluetoothDevice>(from, entity) {
}