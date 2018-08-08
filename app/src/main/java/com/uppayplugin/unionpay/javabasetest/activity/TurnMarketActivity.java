package com.uppayplugin.unionpay.javabasetest.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;

public class TurnMarketActivity extends ToolBarActivity {
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_turn_market;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    public void click(View view) {
        if (isAppInstalled("com.tencent.android.qqdownloader")) {
            //跳转到应用市场的该版本的详情页
            gotoAppInfoPage("com.tencent.android.qqdownloader");
        } else {
            //判断有无浏览器
            if (new Intent().resolveActivity(getPackageManager()) != null) { //有浏览器
                //应用未安装,浏览器下载新版本
                gotoBrowserDownload();
            } else { //天哪，这还是智能手机吗？
                Toast.makeText(this, "天啊，您没安装应用市场，连浏览器也没有，您买个手机干啥？", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     * 检测某个应用是否安装
     *
     * @param packageName
     * @return
     */
    public boolean isAppInstalled(String packageName) {
        try {
            this.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    private void gotoBrowserDownload() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://sj.qq.com/myapp/detail.htm?apkName=" + "com.zhongfu.sinapay");
        intent.setData(content_url);
        startActivity(intent);
    }

    private void gotoAppInfoPage(String appStorePackageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("market://details?id=" + "com.zhongfu.sinapay");
        intent.setData(uri);
        intent.setPackage(appStorePackageName);
        startActivity(intent);
    }
}
