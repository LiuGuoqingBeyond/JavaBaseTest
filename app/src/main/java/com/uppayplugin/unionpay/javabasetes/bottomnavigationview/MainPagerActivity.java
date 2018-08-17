package com.uppayplugin.unionpay.javabasetes.bottomnavigationview;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.axl.android.frameworkbase.ui.BaseBackFragment;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.bottomnavigationview.fragment.HomeTabManager;
import com.uppayplugin.unionpay.javabasetes.bottomnavigationview.inter.BackHandledInterface;
import com.uppayplugin.unionpay.javabasetes.utils.PublicMethodUtils;
import com.whty.xzfpos.base.AppBaseActivity;

import butterknife.BindView;

public class MainPagerActivity extends AppBaseActivity implements BackHandledInterface, BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private FragmentManager fragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mContentFragment;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main_pager;
    }

    @Override
    protected void initViewsAndEvents() {
        navigation.setOnNavigationItemSelectedListener(this);
        PublicMethodUtils.disableShiftMode(navigation);//关闭BottomNavigationView动画效果
        init();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();//创建FragmentManager
        //设置切换方向
        mFragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        mContentFragment = HomeTabManager.getInstance().getFragmentByIndex(R.id.tab_main);
        mFragmentTransaction.add(R.id.main_page_fragment_replace_layout, mContentFragment);//替换布局
        mFragmentTransaction.commitAllowingStateLoss();//提交事务
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public void setSelectedFragment(BaseBackFragment selectedFragment) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tab_main:
                //首页
                switchFragment(HomeTabManager.getInstance().getFragmentByIndex(R.id.tab_main));
                return true;
            case R.id.tab_knowledge:
                //知识体系
                switchFragment(HomeTabManager.getInstance().getFragmentByIndex(R.id.tab_knowledge));
                return true;
            case R.id.tab_project:
                //项目
                switchFragment(HomeTabManager.getInstance().getFragmentByIndex(R.id.tab_project));
                return true;
            case R.id.tab_gank:
                //分类
                switchFragment(HomeTabManager.getInstance().getFragmentByIndex(R.id.tab_gank));
                return true;
            case R.id.tab_mine:
                //我的
                switchFragment(HomeTabManager.getInstance().getFragmentByIndex(R.id.tab_mine));
                return true;
        }
        return false;
    }

    private void switchFragment(Fragment to) {
        if (mContentFragment != to){
            FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
            if (!to.isAdded()){
                mFragmentTransaction.hide(mContentFragment).add(R.id.main_page_fragment_replace_layout, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            }else {
                mFragmentTransaction.hide(mContentFragment).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
            mContentFragment = to;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HomeTabManager.getInstance().destory();
    }
}
