package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReFurbishActivity extends ToolBarActivity {
    @BindView(R.id.swipFresh)
    SwipeRefreshLayout swipFresh;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_re_furbish;
    }

    @Override
    protected void initViewsAndEvents() {
        ButterKnife.bind(ReFurbishActivity.this);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
