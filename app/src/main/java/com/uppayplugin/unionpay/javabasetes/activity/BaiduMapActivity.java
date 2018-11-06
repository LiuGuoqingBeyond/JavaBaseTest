package com.uppayplugin.unionpay.javabasetes.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.location.LocationUtilss;
import com.whty.xzfpos.base.AppToolBarActivity;

public class BaiduMapActivity extends AppToolBarActivity implements LocationUtilss.onLocationBackListener {
    protected LocationManager locationManager;
    private RxPermissions rxPermissions = null;
    private int PRIVATE_CODE = 136;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_baidu_map;
    }

    @Override
    protected void initViewsAndEvents() {
        rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(permission -> {
                    switch (permission.name) {
                        case Manifest.permission.CAMERA:
                            break;
                        case Manifest.permission.ACCESS_COARSE_LOCATION:
//                            getLocation();
                            changeLocation();
                            break;
                    }
                }, error -> {
                });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    private void changeLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //上传经纬度，
            LocationUtilss.getInstance().setOnLocationBackListener(this);
            LocationUtilss.getInstance().startLocation();
            Logger.i("Location:开启定位");
        }else {
            AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
//            mDialog.setTitle(getString(R.string.notifcation_installation_permission));
            mDialog.setMessage(getString(R.string.app_location));
            mDialog.setPositiveButton(getString(R.string.menu_setting), (dialog, which) -> {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, PRIVATE_CODE);
                dialog.dismiss();
            });
            mDialog.setNegativeButton(getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            mDialog.setCancelable(false);
            mDialog.create().show();
            Logger.i("Location:关闭定位");
        }
    }

    @Override
    public void location(String latitude, String longitude, String detailedAddress) {
        Logger.e("latitude="+latitude);
        Logger.e("longitude="+longitude);
        Logger.e("detailedAddress="+detailedAddress);
    }

    @Override
    public void onError(String errorMsg) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PRIVATE_CODE){
            changeLocation();
        }
    }
}
