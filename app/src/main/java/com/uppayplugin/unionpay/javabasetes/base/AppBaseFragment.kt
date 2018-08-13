package com.whty.xzfpos.base

import android.content.Context
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import com.axl.android.frameworkbase.ui.BaseFragment
import com.google.gson.Gson
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil
import com.uppayplugin.unionpay.javabasetes.utils.multilanguage.MultiLanguageUtils

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/11/7
 * Time: 15:22
 */
open abstract class AppBaseFragment : BaseFragment() {
    protected val sp: PreferencesUtil by lazy {
        PreferencesUtil(context)
    }

    protected val mGson: Gson by lazy {
        Gson()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MultiLanguageUtils.updateSystemLanguage()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
        ButterKnife.bind(this, this.view!!)
    }

    override fun onResume() {
        super.onResume()
    }

}