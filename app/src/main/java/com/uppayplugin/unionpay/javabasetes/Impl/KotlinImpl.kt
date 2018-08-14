package com.uppayplugin.unionpay.javabasetes.Impl

import android.content.Context
import com.uppayplugin.unionpay.javabasetes.Interface.KotlinTestInterface
import com.uppayplugin.unionpay.javabasetes.Interface.KotlinTestListener

/**
 * User: LiuGuoqing
 * Data: 2018/8/14 0014.
 */
class KotlinImpl :KotlinTestInterface{
    override fun test(context: Context, message: String, kotlinTestListener: KotlinTestListener) {
        kotlinTestListener._onNext(message)
    }
}