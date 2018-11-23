package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils
import com.uppayplugin.unionpay.javabasetes.utils.keyboard.KeyboardUtil
import com.whty.xzfpos.base.AppBaseActivity
import kotlinx.android.synthetic.main.activity_key_board.*

class KeyBoardActivity : AppBaseActivity(), KeyboardUtil.OnOkClick, KeyboardUtil.onCancelClick, View.OnClickListener {
    private var keyboardUtil:KeyboardUtil? = null
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn_cancel->{
                ll_price_select.visibility = View.GONE
            }
            R.id.btn_commit->{
                ToastUtils.showLong(et_input.text.toString())
                ll_price_select.visibility = View.GONE
            }
        }
    }

    override fun onCancellClick() {
        ll_price_select.visibility = View.GONE
    }

    override fun onOkClick() {
        if (validate()) {
            ll_price_select.setVisibility(View.GONE)
            tv_price.text = "/价格，" + et_price.text + et_orginal_price.getText() + "/原价，" + et_freight.getText() + "/运费"
        }
    }

    private fun validate(): Boolean {
        if (et_price.text.toString() == "") {
            Toast.makeText(application, "价格不能为空", Toast.LENGTH_SHORT).show()
            return false
        } else if (et_orginal_price.text.toString() == "") {
            Toast.makeText(application, "原价不能为空", Toast.LENGTH_SHORT).show()
            return false
        } else if (et_freight.text.toString() == "") {
            Toast.makeText(application, "运费不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_key_board
    }

    override fun initViewsAndEvents() {
        keyboardUtil = KeyboardUtil(KeyBoardActivity@this)
        keyboardUtil!!.setOnOkClick(this)

        keyboardUtil!!.setOnCancelClick(this)

        ll_price.setOnClickListener {
            keyboardUtil!!.attachTo(et_input)
            et_input.isFocusable = true
            et_input.isFocusableInTouchMode = true
            et_input.requestFocus()
            ll_price_select.visibility = View.VISIBLE
        }

        et_price.setOnTouchListener { v, event ->
            keyboardUtil!!.attachTo(et_price)
            false
        }
        et_orginal_price.setOnTouchListener { v, event ->
            keyboardUtil!!.attachTo(et_orginal_price)
            false
        }
        et_freight.setOnTouchListener { v, event ->
            keyboardUtil!!.attachTo(et_freight)
            false
        }

        et_input.setOnTouchListener { v, event ->
            keyboardUtil!!.attachTo(et_input)
            false
        }
        btn_cancel.setOnClickListener(this)
        btn_commit.setOnClickListener(this)
    }

    override fun getLoadingTargetView(): View? {
        return null
    }

    override fun onBackPressed() {
        if (ll_price_select.visibility == View.VISIBLE) {
            ll_price_select.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}
