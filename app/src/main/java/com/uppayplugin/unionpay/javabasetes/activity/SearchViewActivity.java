package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.whty.xzfpos.base.AppToolBarActivity;

import butterknife.BindView;

public class SearchViewActivity extends AppToolBarActivity {
    @Override
    protected void initToolBar() {
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_search_view;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
