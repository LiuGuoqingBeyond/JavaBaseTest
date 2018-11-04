package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.Arith
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_add_or_reduce.*

class AddOrReduceActivity : AppToolBarActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.add->{
                et_account.setText(Arith.add(et_account.text.toString(),"1"))
            }
            R.id.reduce->{
                et_account.setText(Arith.sub(et_account.text.toString(),"1"))
            }
        }
    }

    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_add_or_reduce
    }

    override fun initViewsAndEvents() {
        add.setOnClickListener(this)
        reduce.setOnClickListener(this)
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
