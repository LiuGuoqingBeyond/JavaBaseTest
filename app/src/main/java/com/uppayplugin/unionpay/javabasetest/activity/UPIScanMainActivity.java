package com.uppayplugin.unionpay.javabasetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.libcommon.des.DESCoder;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class UPIScanMainActivity extends ToolBarActivity {
    @BindView(R.id.add_card)
    Button addCard;
    @BindView(R.id.card_list)
    Button cardList;
    @BindView(R.id.settle_record)
    Button settleRecord;
    @BindView(R.id.upi_scan)
    Button upiScan;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_upiscan_main;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
    @OnClick({R.id.add_card,R.id.card_list,R.id.settle_record,R.id.upi_scan})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_card:
                //添加卡
                openActivity(AddCardActivity.class);
                break;
            case R.id.card_list:
                //查询卡列表
                openActivity(CardListActivity.class);
                break;
            case R.id.settle_record:
                //查询交易记录
                openActivity(QuerySellectActivity.class);
                break;
            case R.id.upi_scan:
                //扫一扫
                openActivity(CaptureActivity.class);
                break;
        }
    }
}
