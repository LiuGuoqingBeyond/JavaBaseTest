package com.uppayplugin.unionpay.javabasetest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.uppayplugin.unionpay.javabasetest.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private EditText mEditText;
    private String http = "http://";
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
    }

    private void initView() {
        mEditText = findViewById(R.id.editText_http);
        mProgressBar = findViewById(R.id.baifenbi);
        mWebView = findViewById(R.id.webView);
        //设置webView打开网页而不是系统浏览器或者第三方浏览器
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //如果网页中有js则标记为webView支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //优先使用缓存
        //mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //不有限使用缓存
        //mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.mian_button:
                String URl = mEditText.getText().toString();
                mWebView.loadUrl(http + URl);
                //加载本地资源
                //mWebView.loadUrl("file:///android_asset/example.html");
                //判断页面加载过程 既滚动条
                mWebView.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        if (newProgress == 100) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            if (View.INVISIBLE == mProgressBar.getVisibility()) {
                                mProgressBar.setVisibility(View.VISIBLE);
                            }
                            mProgressBar.setProgress(newProgress);
                            mProgressBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                        super.onProgressChanged(view, newProgress);
                    }
                });
                break;
        }
    }
}
