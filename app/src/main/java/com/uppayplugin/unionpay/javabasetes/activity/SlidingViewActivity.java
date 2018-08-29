package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.axl.android.frameworkbase.view.statusbar.StatusBarCompat;
import com.uppayplugin.unionpay.javabasetes.R;
import com.whty.xzfpos.base.AppToolBarActivity;

public class SlidingViewActivity extends AppToolBarActivity {
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_sliding_view;
    }

    @Override
    protected void initViewsAndEvents() {
        StatusBarCompat.setStatusBarColor(SlidingViewActivity.this, ContextCompat.getColor(SlidingViewActivity.this,R.color.red));
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
