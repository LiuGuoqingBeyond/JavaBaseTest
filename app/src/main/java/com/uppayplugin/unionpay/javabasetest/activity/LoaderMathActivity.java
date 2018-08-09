package com.uppayplugin.unionpay.javabasetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.utils.device.DotLoadingUtils;

import butterknife.BindView;

public class LoaderMathActivity extends ToolBarActivity {
    @BindView(R.id.tv_search_device)
    TextView tvSearchDevice;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_loader_math;
    }

    @Override
    protected void initViewsAndEvents() {
        tvSearchDevice.setText("search");
        DotLoadingUtils.getInstance().loadingDotView(tvSearchDevice);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
