package com.whty.xzfpos.base

import android.content.Context
import android.os.Bundle
import com.axl.android.frameworkbase.ui.AbsBaseActivity
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil
import com.uppayplugin.unionpay.javabasetes.utils.dialog.DialogUtils
import com.uppayplugin.unionpay.javabasetes.utils.language.LanguageUtil
import com.uppayplugin.unionpay.javabasetes.utils.multilanguage.MultiLanguageUtils

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/11/7
 * Time: 11:53
 */
abstract class AppBaseActivity : AbsBaseActivity() {
    protected val sp: PreferencesUtil by lazy {
        PreferencesUtil(this)
    }

    protected val mGson: Gson by lazy {
        Gson()
    }

    protected val permissions: RxPermissions by lazy {
        RxPermissions(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MultiLanguageUtils.updateSystemLanguage()
        Logger.i("${this.localClassName}----onCreate")
    }

    override fun onStart() {
        super.onStart()
        Logger.i("${this.localClassName}----onStart")
    }

    override fun onResume() {
        super.onResume()
        Logger.i("${this.localClassName}----onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Logger.i("${this.localClassName}----onRestart")
    }

    override fun onPause() {
        super.onPause()
        Logger.i("${this.localClassName}----onPause")
    }

    override fun onStop() {
        super.onStop()
        Logger.i("${this.localClassName}----onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.i("${this.localClassName}----onDestroy")
        //只要走到这里就关闭当前dialog，但是交易不能关闭，怎么处理？
        // 发现交易 TradeActivity 是一个单独的父类，不受影响。目前此父类下面的activity不受影响
//        DialogUtils.dissmiss()
    }

    override fun attachBaseContext(newBase: Context) {
        val preferencesUtil = com.axl.android.frameworkbase.utils.PreferencesUtil(newBase)
        val selectedLanguage = preferencesUtil.readPrefs("language_selected")
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, selectedLanguage))
    }
}