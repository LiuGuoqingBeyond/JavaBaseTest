package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_kotlin_base.*

class KotlinBaseActivity : AppToolBarActivity() {
    override fun getLoadingTargetView(): View? {
        return null
    }

    override fun initToolBar() {
        mToolbar.setCenterTitle(R.string.card_add)
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_kotlin_base
    }

    override fun initViewsAndEvents() {
        btnKotlin.setOnClickListener {
            openActivity(KotlinSecondActivity::class.java)
        }
    }
}
