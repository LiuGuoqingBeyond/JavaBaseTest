package com.uppayplugin.unionpay.javabasetes.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.axl.android.frameworkbase.view.statusbar.StatusBarCompat;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.glide.GlideImageLoader;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BannerActivity extends ToolBarActivity {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.bannerTwo)
    Banner bannerTwo;
    private List<String> urlList;
    private List<String> titleList;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initViewsAndEvents() {
        StatusBarCompat.translucentStatusBar(BannerActivity.this,true);//设置为沉浸式
        StatusBarCompat.setStatusBarColor(BannerActivity.this,getResources().getColor(R.color.red));//设置状态栏颜色
        //第一种
        urlList = new ArrayList<>();
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        runOnUiThread(() -> banner.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImages(urlList)
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start());

        titleList = new ArrayList<>();
        titleList.add("第一张");
        titleList.add("第二张");
        titleList.add("第三张");
        titleList.add("第四张");
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showLong(position+"");//此处可以得到每一条banner的position的url
            }
        });

        runOnUiThread(() -> bannerTwo.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setIndicatorGravity(BannerConfig.CENTER)//标题显示居中
                .setImages(urlList)
                .setBannerAnimation(Transformer.DepthPage)
                .setBannerTitles(titleList)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start());
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
