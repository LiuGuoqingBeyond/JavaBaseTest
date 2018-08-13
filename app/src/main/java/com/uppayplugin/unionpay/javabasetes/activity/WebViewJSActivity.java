package com.uppayplugin.unionpay.javabasetes.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.utils.PayUtils;
import com.uppayplugin.unionpay.javabasetes.utils.file.CertUtils;
import com.uppayplugin.unionpay.javabasetes.utils.personal.FileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

import static com.uppayplugin.unionpay.javabasetes.utils.personal.FileUtil.getRealFilePathFromUri;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewJSActivity extends BaseActivity {

    private static final int QRCODESTRING = 1;
    @BindView(R.id.small_ticket_web)
    WebView small_ticket_web;
    private RxPermissions rxPermissions;
    private String a = "";
    private String countryCode;
    private String mobile;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片文件
    private File tempFile;
    private String imagePath;
    private WebSettings webSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = View.inflate(this, R.layout.activity_web_view_js, null);
        setContentView(mView);
        ButterKnife.bind(this);
        initData();
        findView();
        initView();
    }

    @Override
    protected void findView() {
        //检查版本
        checkSdkVersion();
        Map<String, String> map = new TreeMap<String, String>();
        map.put("agencyCode", "123");
        map.put("countryCode", countryCode);
        map.put("phoneNo", mobile);
        String str = PayUtils.joinMapValue(map, '&');
        String str2 = str + "&" + PayUtils.sha("4LNZmt4yuvqfUwO");
        map.put("signature", PayUtils.sha(str2));
        map.put("signMethod", "SHA");

        String str3 = PayUtils.joinMapValue(map, '&');//这个str3为拼接的参数
        Logger.d("str3="+str);

        //生产   https://u.sinopayonline.com/UGateWay/APPCallEntryServlet     测试   http://test13.qtopay.cn/UGateWay/APPCallEntryServlet
//        small_ticket_web.postUrl("http://test13.qtopay.cn/UGateWay/APPCallEntryServlet", EncodingUtils.getBytes(str3, "UTF-8"));//webView的post请求,有问题，在android7.0以下，在网页返回时，报传参为空
        small_ticket_web.loadUrl(String.format(Constant.SCAN_PATH,countryCode,mobile,PayUtils.sha(str2)));


//        small_ticket_web.loadUrl(String.format("http://test13.qtopay.cn/UGateWay/h5unionpayScanTest?mobile=%1$s&countryCode=%2$s",mobile,countryCode));
//        Logger.e("拼接参数="+String.format("http://test13.qtopay.cn/UGateWay/h5unionpayScanTest?mobile=%1$s&countryCode=%2$s",mobile,countryCode));
        requestWebView();
    }

    private void checkSdkVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//  >5.0
            WebView.enableSlowWholeDocumentDraw();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            small_ticket_web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存
        }
    }

    private void requestWebView() {
        webSettings = small_ticket_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("gb2312");
        small_ticket_web.setDrawingCacheEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setSupportZoom(true); //支持缩放
        small_ticket_web.requestFocusFromTouch();

        small_ticket_web.addJavascriptInterface(new JsInterface(), "AndroidWebView");
        small_ticket_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
//        		return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                if (view.getUrl() != null){
                    CertUtils.validSSLCert(handler, view.getUrl(), WebViewJSActivity.this);
                }else {
                    handler.proceed();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

        });
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
        public void Back() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        }

        @JavascriptInterface
        public void openCamera() {
            chooseOpen();
        }
    }

    private void chooseOpen() {
        try {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
            TextView btnCarema = view.findViewById(R.id.btn_camera);
            TextView btnPhoto = view.findViewById(R.id.btn_photo);
            TextView btnCancel = view.findViewById(R.id.btn_cancel);
            final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
            popupWindow.setOutsideTouchable(true);
            View parent = LayoutInflater.from(this).inflate(R.layout.activity_web_view_js, null);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            //popupWindow在弹窗的时候背景半透明
            final WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 0.5f;
            getWindow().setAttributes(params);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    params.alpha = 1.0f;
                    getWindow().setAttributes(params);
                }
            });

            btnCarema.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //权限判断
                    if ((ContextCompat.checkSelfPermission(WebViewJSActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(WebViewJSActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                            ) {
                        //申请WRITE_EXTERNAL_STORAGE权限  READ_EXTERNAL_STORAGE
                        ActivityCompat.requestPermissions((Activity) WebViewJSActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);

                        ActivityCompat.requestPermissions((Activity) WebViewJSActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    popupWindow.dismiss();
                }
            });
            btnPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //权限判断
                    if (ContextCompat.checkSelfPermission(WebViewJSActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions((Activity) WebViewJSActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        gotoPhoto();
                    }
                    popupWindow.dismiss();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    }

    private void gotoCamera() {
        //创建拍照存储的图片文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(WebViewJSActivity.this, "com.zhongfu.sinapay" + ".fileprovider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    private void gotoPhoto() {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.personal_select_img)), REQUEST_PICK);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == 2) {
            if (requestCode == 1) {
                //这个值得回传给js
                a = intent.getStringExtra("qrCodeString");
                Logger.d("扫码出来的码=" + a);
                small_ticket_web.loadUrl("javascript:showInfoFromJava('" + a + "')");
            }
        }
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    Uri uri = Uri.fromFile(tempFile);
                    if (uri == null) {
                        return;
                    }

                    List<String> photos = new ArrayList<>();
                    String path = getRealFilePathFromUri(WebViewJSActivity.this, uri);
                    photos.add(path);

                    compressWithRx(photos);

                    // gotoClipActivity(uri);
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    // gotoClipActivity(uri);

                    List<String> photos = new ArrayList<>();
                    String path = getRealFilePathFromUri(WebViewJSActivity.this, uri);
                    photos.add(path);

                    compressWithRx(photos);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);

                    // bitMap = ImageCompressUtils.bitmapImageCompress(bitMap);
//                    setImageValue(bitMap);
                    //此处后面可以将bitMap转为二进制上传后台网络
                    getBitmapString(bitMap);//得到图片后转成string传给h5
                }
                break;
        }
    }

    private void getBitmapString(Bitmap bitMap) {

        try {
            if (null == bitMap) return;
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); // outputStream
            bitMap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
            byte[] appicon = baos.toByteArray();// 转为byte数组
            imagePath = Base64.encodeToString(appicon, Base64.DEFAULT);
            imagePath = URLEncoder.encode(imagePath, "utf-8");

            //把字符串回传给h5
            small_ticket_web.loadUrl("javascript:showInfoFromJava('" + imagePath + "')");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩图片
     *
     * @param photos
     */
    private void compressWithRx(final List<String> photos) {
        Flowable.just(photos)
                .observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<String> list) throws Exception {
                        return Luban.with(WebViewJSActivity.this).load(list).get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(@NonNull List<File> list) throws Exception {
                        for (File file : list) {
                            /*int[] thumbSize = PublicMethodUtils.computeSize(file.getAbsolutePath());
                            String thumbArg = String.format(Locale.CHINA, "压缩后参数：%d*%d, %dk", thumbSize[0], thumbSize[1], file.length() >> 10);*/

                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                            int h = bitmap.getHeight();
                            int w = bitmap.getWidth();
                            if (w < 960 && h < 720) {
//                                setImageValue(bitmap);
                                //此处后面可以将bitMap转为二进制上传后台网络
                                getBitmapString(bitmap);//得到图片后转成string传给h5
                            } else {
                                float hSize = (float) (960.0 / w);
                                float wSize = (float) (720.0 / h);
                                float size;
                                if (hSize < wSize) {
                                    size = hSize;
                                } else {
                                    size = wSize;
                                }
                                int height = (int) (bitmap.getHeight() * size);
                                int width = (int) (bitmap.getWidth() * size);

                                bitmap = Bitmap.createScaledBitmap(
                                        bitmap, width, height, true);

//                                setImageValue(bitmap);
                                //此处后面可以将bitMap转为二进制上传后台网络
                                getBitmapString(bitmap);//得到图片后转成string传给h5
                            }
                        }
                    }
                });
    }

    //使用Webview的时候，返回键没有重写的时候会直接关闭程序，这时候其实我们要其执行的知识回退到上一步的操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这是一个监听用的按键的方法，keyCode 监听用户的动作，如果是按了返回键，同时Webview要返回的话，WebView执行回退操作，因为mWebView.canGoBack()返回的是一个Boolean类型，所以我们把它返回为true
        if(keyCode==KeyEvent.KEYCODE_BACK&&small_ticket_web.canGoBack()){
            small_ticket_web.goBack();
            return true;
        }else{
            small_ticket_web.clearCache(true);
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initData() {
        countryCode = getIntent().getStringExtra("countryCode");
        mobile = getIntent().getStringExtra("mobile");
    }

    @Override
    protected void initView() {

    }
}
