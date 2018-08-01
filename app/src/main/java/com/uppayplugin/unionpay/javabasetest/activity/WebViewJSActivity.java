package com.uppayplugin.unionpay.javabasetest.activity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.utils.PayUtils;
import com.uppayplugin.unionpay.javabasetest.utils.file.CertUtils;

import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import butterknife.BindView;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewJSActivity extends ToolBarActivity {

    private static final int QRCODESTRING = 1;
    @BindView(R.id.small_ticket_web)
    WebView small_ticket_web;
    private RxPermissions rxPermissions;
    private String a = "";
    private String countryCode;
    private String mobile;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        countryCode = extras.getString("countryCode");
        mobile = extras.getString("mobile");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_web_view_js;
    }

    @Override
    protected void initViewsAndEvents() {
        //检查版本
        checkSdkVersion();
        Map<String, String> map = new TreeMap<String, String>();
        map.put("agencyCode", "123");
        map.put("countryCode", countryCode);
        map.put("phoneNo", mobile);
        String str = PayUtils.joinMapValue(map, '&');
        String str2=str+"&"+ PayUtils.sha("4LNZmt4yuvqfUwO");
        map.put("signature", PayUtils.sha(str2));
        map.put("signMethod", "SHA");

        String str3 = PayUtils.joinMapValue(map, '&');//这个str3为拼接的参数

        small_ticket_web.postUrl("https://u.sinopayonline.com/UGateWay/APPCallEntryServlet", EncodingUtils.getBytes(str3, "UTF-8"));//webView的post请求


//        small_ticket_web.loadUrl(String.format("http://test13.qtopay.cn/UGateWay/h5unionpayScanTest?mobile=%1$s&countryCode=%2$s",mobile,countryCode));
//        Logger.e("拼接参数="+String.format("http://test13.qtopay.cn/UGateWay/h5unionpayScanTest?mobile=%1$s&countryCode=%2$s",mobile,countryCode));
        requestWebView();
    }

    private void checkSdkVersion() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
    }

    private void requestWebView() {
        small_ticket_web.getSettings().setJavaScriptEnabled(true);
        small_ticket_web.getSettings().setDefaultTextEncodingName("gb2312");
        small_ticket_web.setDrawingCacheEnabled(true);

        small_ticket_web.getSettings().setSupportZoom(true); //支持缩放
        small_ticket_web.requestFocusFromTouch();

        small_ticket_web.addJavascriptInterface(new JsInterface(), "AndroidWebView");


        small_ticket_web.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (view.getUrl() != null){
                    CertUtils.validSSLCert(handler, view.getUrl(), mContext);
                }else {
                    handler.proceed();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                DialogUtils.dissmiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

        });
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    private class JsInterface {
        @JavascriptInterface
        public void appScan() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //判断权限
                    rxPermissions();
                }
            });
        }
        @JavascriptInterface
        public void Back(){
            finish();
        }
    }

    private void rxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(
                        Manifest.permission.CAMERA
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .subscribe(permission -> {
                    switch (permission.name) {
                        case Manifest.permission.CAMERA:
                            Intent intent = new Intent(WebViewJSActivity.this, CaptureActivity.class);
                            startActivityForResult(intent, QRCODESTRING);
                            break;
                        /*case Manifest.permission.READ_PHONE_STATE:
                            ToastUtils.showLong("请打开存储权限");
                            break;*/
                        /*case Manifest.permission.ACCESS_COARSE_LOCATION:
                            new Thread(() -> {
                                //获取地理位置
                                getLocation();
                            }).start();
                            break;*/
                    }
                }, error -> {
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode  == 2){
            if (requestCode == 1) {
                //这个值得回传给js
                a = data.getStringExtra("qrCodeString");
                small_ticket_web.loadUrl("javascript:showInfoFromJava('" + a + "')");
            }
        }
    }
    public String createAutoFormHtml(String action, Map<String, String> hiddens,String encoding) {
        StringBuffer sf = new StringBuffer();
        sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
        sf.append("<form id = \"pay_form\" action=\"" + action
                + "\" method=\"Post\">");
        if (null != hiddens && 0 != hiddens.size()) {
            Set<Map.Entry<String, String>> set = hiddens.entrySet();
            Iterator<Map.Entry<String, String>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> ey = it.next();
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
                        + key + "\" value=\"" + value + "\"/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");
        return sf.toString();
    }
}
