package com.uppayplugin.unionpay.javabasetes.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;

public class AndroidEightSureActivity extends ToolBarActivity {
    private int INSTALL_PERMISS_CODE = 108;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_android_eight_sure;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    public void click(View view) {
        setInstallPermission();
    }

    /**
     * 8.0以上系统设置安装未知来源权限
     */
    public void setInstallPermission() {
        boolean haveInstallPermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先判断是否有安装未知来源应用的权限
            haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
                mDialog.setTitle(getString(R.string.notifcation_installation_permission));
                mDialog.setMessage(getString(R.string.need_open_permission));
                mDialog.setPositiveButton(getString(R.string.text_go_setting), (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        //此方法需要API>=26才能使用
                        toInstallPermissionSettingIntent();
                    }
                    dialog.dismiss();
                });
                mDialog.setNegativeButton(getString(R.string.text_cancel), (dialogInterface, i) -> dialogInterface.dismiss());
                mDialog.setCancelable(false);
                mDialog.create().show();
                return;
            } else {
                ToastUtils.showShort(getResources().getString(R.string.notifcation_check) + "");
            }
        } else {
            ToastUtils.showShort(getResources().getString(R.string.notifcation_check) + "");
        }
    }

    /**
     * 开启安装未知来源权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void toInstallPermissionSettingIntent() {
        Uri packageURI = Uri.parse("package:" + getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        startActivityForResult(intent, INSTALL_PERMISS_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == INSTALL_PERMISS_CODE) {
            ToastUtils.showShort(getResources().getString(R.string.notifcation_check) + "");
        }
    }
}
