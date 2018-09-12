package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.view.View;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.BottomView;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.TopView;

public class TopPopUpWindowActivity extends ToolBarActivity {

    private TopView topView;
    private BottomView bottomView;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_top_pop_up_window;
    }

    @Override
    protected void initViewsAndEvents() {
        //点击“顶部出现”按钮
        findViewById(R.id.top_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topView = new TopView(TopPopUpWindowActivity.this);
            }
        });

        //点击“底部出现”按钮
        findViewById(R.id.bottom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomView = new BottomView(TopPopUpWindowActivity.this);
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
