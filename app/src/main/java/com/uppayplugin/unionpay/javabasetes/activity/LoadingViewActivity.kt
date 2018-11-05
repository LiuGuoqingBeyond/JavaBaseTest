package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.whty.xzfpos.base.AppToolBarActivity

class LoadingViewActivity : AppToolBarActivity() {
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_loading_view
    }

    override fun initViewsAndEvents() {
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
