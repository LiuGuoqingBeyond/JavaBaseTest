package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.SimpleFragmentStateAdapter;
import com.uppayplugin.unionpay.javabasetes.fragment.tabfragment.LiveChatFragment;
import com.uppayplugin.unionpay.javabasetes.fragment.tabfragment.LiveInfoFragment;
import com.uppayplugin.unionpay.javabasetes.fragment.tabfragment.LiveVideoFragment;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class TabViewActivity extends AppToolBarActivity {
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.pager)
    ViewPager mPager;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tab_view;
    }

    @Override
    protected void initViewsAndEvents() {
        initTab();
    }

    private void initTab() {
        String[] title = {"聊天", "主播", "排行榜"};
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        titles.addAll(Arrays.asList(title).subList(0, 3));
        fragments.add(new LiveChatFragment());
        fragments.add(new LiveInfoFragment());
        fragments.add(new LiveVideoFragment());
        SimpleFragmentStateAdapter pagerAdapter = new SimpleFragmentStateAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(pagerAdapter);
        String[] titleArray = titles.toArray(new String[titles.size()]);
        mPager.setAdapter(pagerAdapter);
        mTabLayout.setViewPager(mPager, titleArray);
        pagerAdapter.notifyDataSetChanged();
        mPager.setOffscreenPageLimit(title.length);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        ToastUtils.showLong("滑动了"+position);
//                        mDanmuProcess.start();
                        break;
                    case 1:
                    case 2:
//                        mDanmuProcess.finish();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                mDanmuProcess.finish();
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:

//                        mDanmuProcess.start();
                        break;
                    case 1:
                    case 2:
//                        mDanmuProcess.finish();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
