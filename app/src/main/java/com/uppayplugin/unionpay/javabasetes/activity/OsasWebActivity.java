package com.uppayplugin.unionpay.javabasetes.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.utils.PayUtils;
import com.uppayplugin.unionpay.javabasetes.utils.file.CertUtils;

import org.apache.http.util.EncodingUtils;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

public class OsasWebActivity extends ToolBarActivity {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.find_web)
    WebView findWeb;
    private String osasWeb;
    private String osasAccount = "";
    private String osasPassWord = "";

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        osasWeb = extras.getString("osasWeb");
        osasAccount = extras.getString("osasAccount");
        osasPassWord = extras.getString("osasPassWord");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_find_osas_pwd;
    }

    @Override
    protected void initViewsAndEvents() {
        progressBar.setVisibility(View.VISIBLE);
        findWeb.getSettings().setJavaScriptEnabled(true);
        findWeb.getSettings().setDefaultTextEncodingName("gb2312");
        findWeb.setDrawingCacheEnabled(true);

        findWeb.getSettings().setSupportZoom(true); //支持缩放
        findWeb.requestFocusFromTouch();

        findWeb.addJavascriptInterface(new JsInterface(), "AndroidWebView");


        //检查版本
        checkSdkVersion();
        Map<String, String> map = new TreeMap<String, String>();
        map.put("agencyCode", "123");
        map.put("countryCode", "86");
        map.put("phoneNo", "17665232288");
        String str = PayUtils.joinMapValue(map, '&');
        String str2 = str + "&" + PayUtils.sha("4LNZmt4yuvqfUwO");
        map.put("signature", PayUtils.sha(str2));
        map.put("signMethod", "SHA");

        String str3 = PayUtils.joinMapValue(map, '&');//这个str3为拼接的参数

        //生产   https://u.sinopayonline.com/UGateWay/APPCallEntryServlet     测试   http://test13.qtopay.cn/UGateWay/APPCallEntryServlet
        findWeb.postUrl("http://test13.qtopay.cn/UGateWay/APPCallEntryServlet", EncodingUtils.getBytes(str3, "UTF-8"));//webView的post请求
        /*if (!TextUtils.isEmpty(osasWeb) && osasWeb.equals("1")){
            findWeb.loadUrl(String.format(Constans.AGENTWEBURL, osasAccount,osasPassWord));
        }else {
            findWeb.loadUrl(Constans.FINDPWDURL);
        }*/
        findWeb.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                if (view.getUrl() != null){
                    CertUtils.validSSLCert(handler, view.getUrl(), mContext);
                }else {
                    handler.proceed();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

        });
    }
    private void checkSdkVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//  >5.0
            WebView.enableSlowWholeDocumentDraw();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            findWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
    private class JsInterface {
        @JavascriptInterface
        public void Back() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        }
    }
    //使用Webview的时候，返回键没有重写的时候会直接关闭程序，这时候其实我们要其执行的知识回退到上一步的操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这是一个监听用的按键的方法，keyCode 监听用户的动作，如果是按了返回键，同时Webview要返回的话，WebView执行回退操作，因为mWebView.canGoBack()返回的是一个Boolean类型，所以我们把它返回为true
        if (keyCode == KeyEvent.KEYCODE_BACK && findWeb.canGoBack()) {
            findWeb.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
