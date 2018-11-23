package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils
import com.whty.xzfpos.base.AppBaseActivity
import com.ziyeyouhu.library.KeyboardTouchListener
import com.ziyeyouhu.library.KeyboardUtil
import kotlinx.android.synthetic.main.activity_pwd_keyboard.*

class PwdKeyboardActivity : AppBaseActivity() {
    private var keyboardUtil: KeyboardUtil? = null
    private val maxInputLength:Int = 20
    private var pwdList: ArrayList<String>? = null
    private var pwdBuffer: StringBuffer? = null
    private var mUserPwd = ""
    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_pwd_keyboard
    }

    override fun initViewsAndEvents() {
        pwdList = ArrayList()
        initMoveKeyBoard()
    }

    private fun initMoveKeyBoard() {
        new_Login.setOnClickListener {
            if(null != pwdList) {
                pwdBuffer = StringBuffer()
                for (pwdString in pwdList!!) {
                    pwdBuffer!!.append(pwdString)
                }
                mUserPwd = pwdBuffer!!.toString()
            }
            ToastUtils.showLong(mUserPwd)
        }

        keyboardUtil = KeyboardUtil(this@PwdKeyboardActivity, lay_view,maxInputLength)
        keyboardUtil!!.setOtherEdittext(new_useloginpwd)
        keyboardUtil!!.setKeyBoardStateChangeListener(keyBoardStateChangeListener)
        new_useloginpwd.setOnTouchListener(KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC, -1))
    }

    internal var keyBoardStateChangeListener: KeyboardUtil.KeyBoardStateChangeListener = object : KeyboardUtil.KeyBoardStateChangeListener {

        override fun KeyBoardStateChange(state: Int, editText: EditText) {

        }
        var encryKeyString:String? = ""
        override fun onReceivePressKey(keyValue: String,et:EditText, index:Int) {
            if (null != pwdList) {
                /*if(index == 2) {
                    Logger.e("encryKeyString:" + encryKeyString)
                    Logger.e("encryKeyString-length:" + encryKeyString!!.length)
                    pwdList!!.add(encryKeyString!!)
                } else {
//
                }*/
                pwdList!!.add(keyValue!!)
            }
        }

        override fun onReceiveDeleteKey(et: EditText) {
            if (null != pwdList && pwdList!!.size > 0) {
                pwdList!!.removeAt(pwdList!!.size - 1)
            }
        }

        override fun onReceiveMaxLength(p0: Int) {
            ToastUtils.showLong("最多只支持输入" + maxInputLength + "位密码")
        }
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
