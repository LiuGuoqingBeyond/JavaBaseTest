package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.glide.GlideUtil
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_load_round.*

class LoadRoundActivity : AppToolBarActivity() {
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_load_round
    }

    override fun initViewsAndEvents() {
        GlideUtil.loadRoundImage(mContext, "http://192.168.1.13:8500/UploadFile/20181115/%E7%9C%8B%E7%9C%8B%E7%9C%8B%E7%9C%8B%E4%BD%A0%E8%83%BD/30/20181115101924500--1676759797.jpg", img_round)
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
