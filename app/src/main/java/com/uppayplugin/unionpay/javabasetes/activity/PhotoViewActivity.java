package com.uppayplugin.unionpay.javabasetes.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.widget.PreviewViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewActivity extends ToolBarActivity {
    @BindView(R.id.preview_pager)
    PreviewViewPager viewPager;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void initViewsAndEvents() {
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        /*viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_title.setText(position + 1 + "/" + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });*/
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
