package com.uppayplugin.unionpay.javabasetes.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.axl.android.frameworkbase.ui.ToolBarActivity
import com.uppayplugin.unionpay.javabasetes.R

class ImageViewActivity : ToolBarActivity() {
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_image_view
    }

    override fun initViewsAndEvents() {
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
