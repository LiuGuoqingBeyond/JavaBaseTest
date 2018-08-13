package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SinoPayMainActivity extends ToolBarActivity {
    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.translate_record)
    Button translateRecord;
    @BindView(R.id.merchant_info)
    Button merchantInfo;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sino_pay_main;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
    @OnClick({R.id.btn_scan,R.id.translate_record,R.id.merchant_info})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_scan:
                //二维码
                openActivity(QRCodeActivity.class);
                break;
            case R.id.translate_record:
                //交易记录
                openActivity(TradeRecordActivity.class);
                break;
            case R.id.merchant_info:
                //商户信息
                openActivity(MerChantInfoActivity.class);
                break;
        }
    }
}
