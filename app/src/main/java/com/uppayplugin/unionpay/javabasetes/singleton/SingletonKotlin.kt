package com.uppayplugin.unionpay.javabasetes.singleton

/**
 * User: LiuGuoqing
 * Data: 2018/8/14 0014.
 * 简单的单例模式:饿汉式（常用）
 */
class SingletonKotlin private constructor() {
    companion object {
        private var instance: SingletonKotlin? = null
            get() {
                if (field == null) {
                    field = SingletonKotlin()
                }
                return field
            }

        @Synchronized
        fun get(): SingletonKotlin {
            return instance!!
        }
    }
    fun getMessage() :String{
        return "这个是调用kotlin单例模式里边的getMessage方法返回的参数"
    }
}