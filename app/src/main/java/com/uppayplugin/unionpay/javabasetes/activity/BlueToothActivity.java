package com.uppayplugin.unionpay.javabasetes.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.RxBus.RxBus;
import com.uppayplugin.unionpay.javabasetes.receiver.BlueToothDeviceReceiver;
import com.uppayplugin.unionpay.javabasetes.utils.device.BlueToothManagerUtils;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.whty.xzfpos.base.AppToolBarActivity;
import com.whty.xzfpos.utils.RxBus.RxBusEvent.SearchBluetoothDeviceEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class BlueToothActivity extends AppToolBarActivity {
    private BluetoothAdapter btAdapter = null;
    private BluetoothDevice device = null;
    private String devName = null;
    private String devAddress = null;
    // 蓝牙扫描广播
    private BlueToothDeviceReceiver btReceiver;
    private static final int PERMISSION_COARSE_LOCATION = 1;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_blue_tooth;
    }

    @Override
    protected void initViewsAndEvents() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btReceiver = new BlueToothDeviceReceiver();
//        新增动态权限判断
        GrantLocationPermissionToApp();
        // 设置该界面不允许被截屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        registerBoardCast();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick(R.id.btn_bluetooth)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bluetooth:
                //首先检测蓝牙是否打开
                if (BlueToothManagerUtils.queryMobileBluetoothState(mContext)) {
//                    searchBlueTooth();
                    openActivity(BlueActivity.class);
                } else {
                    openBlueTooth();
                }
                break;
        }
    }

    private void searchBlueTooth() {
        RxBus.getInstance().toObservable(SearchBluetoothDeviceEvent.class)
                .compose(bindToLifecycle())
                .subscribe(baseEvent -> {
                    device = baseEvent.getEntity();
                    devName = device.getName();
                    devAddress = device.getAddress();

                    // FIXME: 2017/10/18 zhanghuan 过滤非厂商设备
                    if (!TextUtils.isEmpty(devName)) {
                        devName = devName.toLowerCase();
                        ToastUtils.showLong(devName);
                    }
                });
    }

    /**
     * 注册搜索设备广播
     */
    private void registerBoardCast() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intent.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intent.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intent.setPriority(-1000);
        registerReceiver(btReceiver, intent);
    }

    private void openBlueTooth() {
        BlueToothManagerUtils.openMobileBluetooth(mContext);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void GrantLocationPermissionToApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

            List<String> permissionList = new ArrayList<String>();
            if (ContextCompat.checkSelfPermission(mContext, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[0]);
            }
            if (ContextCompat.checkSelfPermission(mContext, permissions[1]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[1]);
            }
            if (ContextCompat.checkSelfPermission(mContext, permissions[2]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[2]);
            }
            if (ContextCompat.checkSelfPermission(mContext, permissions[3]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[3]);
            }

            String[] permission = permissionList.toArray(new String[permissionList.size()]);
            if (permission.length > 0) {
                ActivityCompat.requestPermissions(BlueToothActivity.this, permission, PERMISSION_COARSE_LOCATION);
            }
        }
    }
}
