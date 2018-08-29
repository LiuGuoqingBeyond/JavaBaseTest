package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.fragment.IncomeFragment;
import com.uppayplugin.unionpay.javabasetes.fragment.PayFragment;
import com.whty.xzfpos.base.AppToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TestFragmentActivity extends AppToolBarActivity {
    @BindView(R.id.pay)
    RadioButton pay;
    @BindView(R.id.income)
    RadioButton income;
    private FragmentManager fm;
    private IncomeFragment incomeFragment;
    private PayFragment payFragment;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_test_fragment;
    }

    @Override
    protected void initViewsAndEvents() {
        fm = getSupportFragmentManager();
        setTabSelection(0);

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick({R.id.pay, R.id.income})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay:
                setTabSelection(0);
                break;
            case R.id.income:
                setTabSelection(1);
                break;
        }
    }

    private void setTabSelection(int index) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if (incomeFragment == null) {
                    incomeFragment = new IncomeFragment();
                    ft.add(R.id.fl, incomeFragment);
                }
                ft.show(incomeFragment);
                break;

            case 1:
                if (payFragment == null) {
                    payFragment = new PayFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("hehe","哈哈");
                    payFragment.setArguments(bundle);
                    ft.add(R.id.fl, payFragment);
                }
                ft.show(payFragment);
                break;
        }
        ft.commit();

    }

    //用于隐藏fragment
    private void hideFragment(FragmentTransaction ft) {
        if (incomeFragment != null) {
            ft.hide(incomeFragment);
        }
        if (payFragment != null) {
            ft.hide(payFragment);
        }
    }

}
