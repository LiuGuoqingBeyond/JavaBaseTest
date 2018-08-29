package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uppayplugin.unionpay.javabasetes.R;
import com.whty.xzfpos.base.AppToolBarActivity;

public class AddVGAActivity extends AppToolBarActivity {
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_add_vga;
    }

    @Override
    protected void initViewsAndEvents() {
        //SVG图---->1、res下drawable右键new---->vector...2、选第二个，选择svg图片路径  3、选择大小，或者默认24X24大小
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
