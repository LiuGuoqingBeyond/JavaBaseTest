package com.uppayplugin.unionpay.javabasetes.entity.response

/**
 * User: LiuGuoqing
 * Data: 2018/8/14 0014.
 */
class KotlinTestRep : BaseRepModel() {
    var name = ""
    override fun toString(): String {
        return "KotlinTestRep(name='$name') \${super.toString()"
    }
}