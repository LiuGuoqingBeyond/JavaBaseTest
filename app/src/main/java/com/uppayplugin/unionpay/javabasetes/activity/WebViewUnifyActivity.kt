package com.uppayplugin.unionpay.javabasetes.activity

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.axl.android.frameworkbase.view.statusbar.StatusBarCompat
import com.orhanobut.logger.Logger
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.libcommon.utils.TextUtil
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_web_view_unify.*

class WebViewUnifyActivity : AppToolBarActivity() {
    private var title: String = ""
    private var url: String = ""
    private var data: String = ""
    private var isPost: Boolean = false
    private var needRefresh: Boolean = true
    override fun initToolBar() {
        mToolbar.setCenterTitle(title)
    }

    override fun getBundleExtras(extras: Bundle) {
        title = TextUtil.setText(extras.getString("title"))
        url = TextUtil.setText(extras.getString("url"))
        data = TextUtil.setText(extras.getString("data"))
        isPost = TextUtil.setText(extras.getBoolean("isPost").toString(), "false").toBoolean()
        needRefresh = TextUtil.setText(extras.getBoolean("needRefresh").toString(), "true").toBoolean()
        Logger.i("URL:$url")
    }

    override fun getContentViewLayoutID(): Int = R.layout.activity_web_view_unify

    override fun initViewsAndEvents() {

        rl_title.visibility = View.GONE
        toolbarLayout.visibility = View.VISIBLE

        StatusBarCompat.setStatusBarColor(this@WebViewUnifyActivity!!, ContextCompat.getColor(this@WebViewUnifyActivity!!,R.color.colorPrimary))

        mRefreshLayout.setColorSchemeColors(resources.getColor(R.color.colorPrimary))

        mRefreshLayout.setOnRefreshListener { webView.reload() }

        mRefreshLayout.isEnabled = needRefresh

        //webView配置相关信息
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setGeolocationEnabled(true) //启用地理定位
        webSettings.databaseEnabled = true//启用数据库
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = true
        webSettings.blockNetworkImage = false
        webSettings.useWideViewPort = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        webSettings.loadWithOverviewMode = true

        webView.setDownloadListener({ url1, _, _, _, _ ->
            val uri = Uri.parse(url1)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })
        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                result.confirm()
                return true
            }

            override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
                callback.invoke(origin, true, false)
                super.onGeolocationPermissionsShowPrompt(origin, callback)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    loadProgress.visibility = View.GONE
                } else {
                    if (loadProgress.visibility == View.GONE)
                        loadProgress.visibility = View.VISIBLE
                    loadProgress.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }

            //For Android  >= 4.1
            fun openFileChooser(valueCallback: ValueCallback<Uri>, acceptType: String, capture: String) {
                uploadMessage = valueCallback
                openImageChooserActivity()
            }

            // For Android >= 5.0
            override fun onShowFileChooser(webView: WebView, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams: WebChromeClient.FileChooserParams): Boolean {
                uploadMessageAboveL = filePathCallback
                openImageChooserActivity()
                return true
            }

        }

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                mRefreshLayout.isRefreshing = false
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                mRefreshLayout.isRefreshing = false
                super.onReceivedError(view, request, error)
            }
        }

        webView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()   //后退
                    return@OnKeyListener true    //已处理
                }
            }
            false
        })



        if (!isPost) {
            webView.loadUrl(url)
        } else {
            webView.postUrl(url, data.toByteArray())
        }
    }

    override fun getLoadingTargetView(): View? = null


    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }

    private var uploadMessage: ValueCallback<Uri>? = null
    private var uploadMessageAboveL: ValueCallback<Array<Uri>>? = null
    private val FILE_CHOOSER_RESULT_CODE = 10000


    private fun openImageChooserActivity() {
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return
            val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data)
            } else if (uploadMessage != null) {
                uploadMessage!!.onReceiveValue(result)
                uploadMessage = null
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onActivityResultAboveL(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return
        var results: Array<Uri>? = null
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                val dataString = intent.dataString
                if (dataString != null)
                    results = arrayOf(Uri.parse(dataString))
            }
        }
        uploadMessageAboveL!!.onReceiveValue(results)
        uploadMessageAboveL = null
    }
}
