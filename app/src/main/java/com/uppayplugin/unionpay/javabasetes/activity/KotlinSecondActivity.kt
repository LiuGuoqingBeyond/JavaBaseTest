package com.uppayplugin.unionpay.javabasetes.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_kotlin_second.*
import java.util.*

class KotlinSecondActivity : AppToolBarActivity() {
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_kotlin_second
    }

    override fun initViewsAndEvents() {
        click.setOnClickListener {
            var map = TreeMap<String, String>()
            map["key"] = "哈哈"
            ToastUtils.showLong(map["key"])

        }
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
