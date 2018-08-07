package com.uppayplugin.unionpay.javabasetest.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;

public class ImageScanActivity extends ToolBarActivity {

    private TextView etCode;
    private final static int REQ_CODE = 1028;
    private String result = "";
    private RxPermissions rxPermissions;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_image_scan;
    }

    @Override
    protected void initViewsAndEvents() {
        etCode = findViewById(R.id.et_code);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    public void click(View view) {
        //判断权限
        rxPermissions();
    }

    private void rxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(
                        Manifest.permission.CAMERA
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .subscribe(permission -> {
                    switch (permission.name) {
                        case Manifest.permission.CAMERA:
                            Intent intent = new Intent(mContext, CaptureActivity.class);
                            startActivityForResult(intent, REQ_CODE);
                            break;
                        /*case Manifest.permission.READ_PHONE_STATE:
                            ToastUtils.showLong("请打开存储权限");
                            break;*/
                        /*case Manifest.permission.ACCESS_COARSE_LOCATION:
                            new Thread(() -> {
                                //获取地理位置
                                getLocation();
                            }).start();
                            break;*/
                    }
                }, error -> {
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*result = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
        if (result != null) {
            etCode.setText("扫码结果：" + result);
        }*/
    }
}
