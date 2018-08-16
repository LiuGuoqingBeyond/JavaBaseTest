package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import com.example.testdemolib.Listener.GetCardMessageListener
import com.example.testdemolib.entity.respons.BankTypeModel
import com.orhanobut.logger.Logger
import com.uppayplugin.unionpay.javabasetes.Impl.KotlinImpl
import com.uppayplugin.unionpay.javabasetes.Interface.KotlinTestListener
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.entity.response.KotlinTestRep
import com.uppayplugin.unionpay.javabasetes.singleton.SingletonKotlin
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_kotlin_base.*

class KotlinBaseActivity : AppToolBarActivity() {
    override fun getLoadingTargetView(): View? {
        return null
    }

    override fun initToolBar() {
        mToolbar.setCenterTitle(R.string.card_add)
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_kotlin_base
    }

    override fun initViewsAndEvents() {
        btnKotlin.setOnClickListener {
            //            openActivity(KotlinSecondActivity::class.java)
            /*var a = "哈哈"//可变变量的定义
            val b:Int = 2//不可变变量，只能赋值一次的变量*/

            //字符串模板
//            val c = 2
//            ToastUtils.showLong("${c}aadad")//之前int型的+""，，kotlin不用，直接用字符串模板的形式
            //模板中的表达式
            /*val d = 1
            val s1 = "d is $d"
            val d1 = 2
            ToastUtils.showLong("${s1.replace("is","was")},but now is $d1")*/

            //NULL检查机制
            /*//类型后面加？表示可返回为null
            var age: String = "23"
            //抛出空指针异常
            val ages = age!!.toInt()
            //不做处理返回 null
            val ages1 = age?.toInt()
            //age为空返回-1
            val ages2 = age?.toInt() ?: -1
            ToastUtils.showLong("${isNull(1)}")*/

            //类型检测及自动类型转换
//            ToastUtils.showLong("${getStringLength("222")}")

            //区间:区间表达式由具有操作符形式 .. 的rangeTo函数辅以 in 和 !in 形式
//            for( i in 1..4) ToastUtils.showLong("${i}")

            //使用step指定步长
//            for (i in 1..4 step 2) Logger.d("打印步长---"+i)

            //半包围
//            for (i in 1 until 10) Logger.d("打印步长---"+i)//i in [1, 10) 排除了 10

            //比较两个数字
            /*Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，其实 Kotlin 帮你封装了一个对象，
            这样可以保证不会出现空指针。数字类型也一样，所有在比较两个数字的时候，
            就有比较数据大小和比较两个对象是否相同的区别了。
            在 Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。*/
            /*val a: Int = 10000
            println(a === a) // true，值相等，对象地址相等

            //经过了装箱，创建了两个不同的对象
            val boxedA: Int? = a
            val anotherBoxedA: Int? = a

            if (boxedA === anotherBoxedA) ToastUtils.showLong("true") else ToastUtils.showLong("false")
            //虽然经过了装箱，但是值是相等的，都是10000
            println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
            println(boxedA == anotherBoxedA) // true，值相等*/

            //字符串
            //三引号"""多行字符串
            /*val text = """
                多行字符串
                多行字符串
                多行字符串
                """.trimMargin()
            ToastUtils.showLong(text)*/
            //for循环
            /*val poemArray: Array<String> = arrayOf("朝辞白帝彩云间", "千里江陵一日还", "两岸猿声啼不住", "轻舟已过万重山")
            var poem: String = ""
            for (item:String in poemArray) {
                poem = "$poem$item，\n"
            }
            Logger.d("诗\n$poem")

            var list  = arrayListOf<String>()
            list.add("哈哈")
            list.add("呵呵")
            list.add("嘿嘿")
            list.add("嘻嘻")
            for (dataList in list){
                ToastUtils.showLong("dataList====$dataList")
            }*/

            //kotlin使用generate
            /*var kotlin = KotlinTestRep()
            kotlin.name = "哈哈哈"
            ToastUtils.showLong(kotlin.name)*/

            //init初始化

            //Kotlin书写Interface
            /*var kotlinTestInterface = KotlinImpl()
            kotlinTestInterface.test(mContext,"哈哈",object : KotlinTestListener{
                override fun _onNext(string: String) {
                    ToastUtils.showLong(string)
                }
            })*/

            //kotlin写单例模式
//            ToastUtils.showLong(SingletonKotlin.get().getMessage())

            //各操作符的写法练习
        }
    }

    fun isNull(int: Int): Int? {
        val a = int
        val b = 2
        if (a > b) {
            return a
        }
        return null
    }

    fun getStringLength(obj: Any): Int? {
        //在"&&"运算符的右侧，"obj"的类型会被自动转换为"string"
        if (obj is String && obj.length > 0)
            return obj.length
        return null
    }
}
