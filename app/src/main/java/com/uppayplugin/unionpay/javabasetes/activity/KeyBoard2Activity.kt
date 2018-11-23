package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.fragment.PayDialogFragment
import com.whty.xzfpos.base.AppBaseActivity
import kotlinx.android.synthetic.main.activity_key_board2.*

class KeyBoard2Activity : AppBaseActivity(), View.OnClickListener {
    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_key_board2
    }

    override fun initViewsAndEvents() {

        pay.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var payDialogFragment = PayDialogFragment(KeyBoard2Activity@this)
        payDialogFragment.show(supportFragmentManager,"payFragment")
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
