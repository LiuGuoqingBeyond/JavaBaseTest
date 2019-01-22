package com.uppayplugin.unionpay.javabasetes.activity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.axl.android.frameworkbase.ui.ToolBarActivity
import com.uppayplugin.unionpay.javabasetes.R
import kotlinx.android.synthetic.main.activity_auto_complete_text_view.*

class AutoCompleteTextViewActivity : ToolBarActivity() {
    private val autoStrs = arrayOf("a", "abc", "abcd", "abcde", "ba")
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_auto_complete_text_view
    }

    override fun initViewsAndEvents() {
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, autoStrs)
        actv.setAdapter(adapter)
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
