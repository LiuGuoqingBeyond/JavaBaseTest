package com.uppayplugin.unionpay.javabasetest.activity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.camera.CameraManager;
import com.google.zxing.decoding.CaptureActivityHandler;
import com.google.zxing.decoding.InactivityTimer;
import com.google.zxing.view.ViewfinderView;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetest.view.BaseToolbar;

import java.io.IOException;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author King_wangyao
 * @version 1.0.0
 * @description Initial the camera
 * @date 2014-2-11
 */
public class CaptureActivity extends BaseActivity implements Callback {

    // 上下文对象
    private Context mContext = CaptureActivity.this;

    @BindView(R.id.img_light)
    ImageView imgLight;

    @BindView(R.id.tv_click_capture)
    TextView clickCapture;

    // UI
    private CaptureActivityHandler mHandler;
    private ViewfinderView mViewfinderView;

    // Data
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    // 二维码扫描结果
    private String qrCodeString = "";

    private PreferencesUtil prefes;
    private String securityKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mView = View.inflate(this, R.layout.activity_captrue, null);
        setContentView(mView);
        ButterKnife.bind(this);

        findView();
        initData();
        initView();

        CameraManager.init(getApplication());

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    protected void findView() {
        this.mViewfinderView = findViewById(R.id.viewfinder_view);
    }

    @Override
    protected void initData() {
        // TODO Auto-generated method stub
        prefes = new PreferencesUtil(mContext);
        securityKey = prefes.readPrefs(Constant.PREFES_KEY);
    }

    @Override
    protected void initView() {
        mToolbar = new BaseToolbar(mView, this);
        mToolbar.setNavigationOnClickListener(v -> {
            new Thread(() -> {
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
            }).start();
            finish();
        });
        mToolbar.setCenterTitle(getString(R.string.scan_code_text_title));
        imgLight.setBackgroundResource(R.drawable.icon_flashlight_close);
        imgLight.setOnClickListener(v -> {
            if (!isOpen) {
                CameraManager.get().openF();
                isOpen = true;
                imgLight.setBackgroundResource(R.drawable.icon_flashlight_open);
                clickCapture.setText(getString(R.string.tv_light_close));
                Logger.d("click light" + "1");
            } else {
                CameraManager.get().stopF();
                isOpen = false;
                imgLight.setBackgroundResource(R.drawable.icon_flashlight_close);
                clickCapture.setText(R.string.code_click_light);
                Logger.d("click light" + "2");
            }
        });
    }

    boolean isOpen;

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);//音频管理器
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {//声音模式
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHandler != null) {
            mHandler.quitSynchronously();
            mHandler = null;
        }
        CameraManager.get().closeDriver();//关闭摄像头
    }

    @Override
    public void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {

        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();//开启手机振动

        try {
            qrCodeString = result.getText().trim();
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }

        //-----------------------这里是扫码   信息返回---------------------------------------------------------------------------------------
        Toast.makeText(mContext, qrCodeString, Toast.LENGTH_SHORT).show();
        Logger.e("active scan Result:" + qrCodeString);

        /*if (!TextUtils.isEmpty(qrCodeString)) {
            BankInfoListRepModel cards = PayPwdManagerUtils.getInstance(mContext).getBindedBankCardInfo();
            // 扫码成功
            if (qrCodeString.startsWith(ConstantUtils.ZHONGFU_CODE)) {  // 中付码二维码
                Logger.e("ZHONGFU_CODE");
                *//** 2017/11/11 zhanghuan 中付码扫码处理逻辑
                 * 1: 先获取本地是否有绑定过中付卡，未绑定过，提示用户到绑卡页面
                 * 2：判断二维码类型，是固定二维码还是非固定二维码，然后请求解析二维码
                 * 3：根据返回的区域信息，判断是否有当前国家绑定的银行卡，没有的话，跳转到绑卡提示页面
                 * 4: 查询用户是否有绑定过当前国家的银行卡(sysareaId),未绑定，提示用户到绑卡提示页面
                 *//*
                if (null != cards && null != cards.list && cards.list.size() > 0) {
                    boolean bindZFCard = PayPwdManagerUtils.getInstance(mContext).bindBankCard(0x00, cards);
                    Logger.d("bindZFCard:" + bindZFCard);
                    if (bindZFCard) {
                        // 有绑定过中付卡，请求解析二维码
                        if (qrCodeString.contains(Constant.URL_ALL_PAY_CODE)) {
                            // 非固定二维码，跳转到请求解析非固定二维码接口
                            reqAnalyticUnFixedQrCode(cards,"m");
                        } else if(qrCodeString.contains(Constant.URL_ALL_PAY_CODE2)){
                            reqAnalyticUnFixedQrCode(cards,"s");
                        } else if (qrCodeString.contains(Constant.URL_ALL_PAY_CODE1)) {
                            // 固定二维码，跳转到请求解析固定二维码接口
                            reqAnalyticFixedQrCode(cards);
                        }
                    } else {
                        // 2017/11/10 zhanghuan 未绑定银行卡，跳转到绑定卡页面
                        toNextPage(false,null,qrCodeString,"1","0");
                    }
                }
            } else if (qrCodeString.startsWith(ConstantUtils.YINLIAN_CODE)) {  // 银联国际二维码
                Logger.e("YINLIAN_CODE");
                *//**
                 * 1:先查询绑定的银行卡列表里面是否有国际卡,如果没有,跳转到提示绑定银行卡页面
                 * 2:有银行卡则默认取第一张银行卡去拉取商户信息
                 *//*
                if (null != cards && null != cards.list && cards.list.size() > 0) {
                    boolean bindUnionCard = PayPwdManagerUtils.getInstance(mContext).bindBankCard(0x01, cards);
                    Logger.d("bindUnionCard:" + bindUnionCard);
                    if (bindUnionCard) {
                        // 有绑定过银联卡,上送二维码信息，拉取商户和交易信息,然后根据返回是否有金额,决定是否跳转到输入金额页面
                        requestPreTradeInfoFromService(cards.list.get(0));
                    } else {
                        // 2017/11/10 zhanghuan 未绑定银行卡，跳转到绑定卡页面
                        toNextPage(false,null,qrCodeString,"2","0");
                    }
                }
            } else if (qrCodeString.startsWith(ConstantUtils.HTTP_URL_CODE)) {  // http_url 二维码
                // 1: 先判断有没有绑定过卡片
                if (null != cards && null != cards.list && cards.list.size() > 0) {
                    boolean bindUnionCard = PayPwdManagerUtils.getInstance(mContext).bindBankCard(0x01, cards);
                    Logger.d("bindUnionCard:" + bindUnionCard);
                    if (bindUnionCard) {
                        // 绑定过卡片, 请求解析二维码,获取商户信息
                        requestHttpUrlTradeInfoFromService(cards);
                    } else {
                        // 未绑定卡片,提示去绑卡
                        toNextPage(false,null,qrCodeString,"2","0");
                    }
                }
            } else {
                DialogShowUtils.showNoticeDialogWithFinish(mContext, "", getString(R.string.not_support_qrcode)
                        , getString(R.string.text_confirm_msg), false);
            }
        } else {
            // 扫码失败,直接提示客户重新扫码
            new ToastUtil(CaptureActivity.this).showShortToast(getString(R.string.active_scan_fail));
            finish();
        }*/
    }

    /**
     * 请求解析固定二维码接口
     *
     */
    /*public void reqAnalyticFixedQrCode(BankInfoListRepModel cards) {
        try {
            Map<String, String> map = new TreeMap<>();
            map.put("payId", qrCodeString.substring(qrCodeString.indexOf(ConstantUtils.SPILT_FILTER) +
                    ConstantUtils.SPILT_FILTER.length()));
            map.put("countryCode", countryCode);
            map.put("mobile", mobile);
            map.put("sessionID", sessionID);
            map.put("txnType", "29");

            String str = PayUtils.joinMapValue(map, '&');
            map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n", ""));
            String result = mGson.toJson(map);
            Logger.e("result", result);

            TradeRequestImpl
                    .reqAnalyticFixedQrCode((ReqFixedZFQrCodeModel) TransMapToBeanUtils.mapToBean(map, ReqFixedZFQrCodeModel.class))
                    .subscribe(new ProgressSubscriber<TradeInfoRespModel>(mContext) {
                        @Override
                        protected void _onNext(TradeInfoRespModel responseModel) {
                            Logger.e("bankCard list:" + responseModel.toString());
                            if (responseModel.isOk()) {
                                // 判断本地有没有绑定当前国家区域的银行卡
                                String sysareaId = responseModel.sysareaId;
                                Logger.e("sysareaId:" + sysareaId);
                                boolean bindBankCard = PayPwdManagerUtils.getInstance(mContext).bindSysAreaCountryCard(sysareaId, cards);
                                if (bindBankCard) {
                                    toNextPage(true,responseModel,qrCodeString,"","");
                                } else {
                                    toNextPage(false,responseModel,qrCodeString,"1","1");
                                }
                            } else if (responseModel.needLogin()) {
                                DialogShowUtils.showReloginDailog(mContext, responseModel.getMsg());
                            } else {
                                DialogShowUtils.showCommonDialogWithFinish(mContext, responseModel.msg);
                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            Logger.e("message:" + message);
                            DialogShowUtils.showCommonDialogWithFinish(mContext, message);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            finish();
        }
    }

    *//**
     * 请求解析非固定二维码接口
     *
     *//*
    public void reqAnalyticUnFixedQrCode(BankInfoListRepModel cards,String flag) {
        try {
            Map<String, String> mapCode = RequestParse.URLRequest(qrCodeString);
            String motosn = mapCode.get(flag).toUpperCase();
            Logger.e("motosn:" + motosn);
            motosn = DESCoder2.decryptMode(motosn, "J2OKyoMA0fcKROy51sWNhPpk", "omcZNJ4X");

            String key = DESCoder.decryptMode(securityKey, randomKey);
            Map<String, String> map = new TreeMap<>();
            map.put("merId", DESCoder.encryptMode(motosn, key).replaceAll("\n", ""));
            map.put("qrcode", qrCodeString);
            map.put("mobile", mobile);
            map.put("countryCode", countryCode);
            map.put("sessionID", sessionID);
            map.put("longitude", TextUtils.isEmpty(prefes.readPrefs(Constant.PREFES_LONGITUDE))?"12.23422":prefes.readPrefs(Constant.PREFES_LONGITUDE));
            map.put("latitude", TextUtils.isEmpty(prefes.readPrefs(Constant.PREFES_LATITUDE))?"23.3421":prefes.readPrefs(Constant.PREFES_LATITUDE));
            map.put("IP", prefes.readPrefs(Constant.PREFES_IP));
            map.put("baseStation", prefes.readPrefs(Constant.PREFES_BASESTATION));
            map.put("txnType", "22");
            String str = PayUtils.joinMapValue(map, '&');
            Logger.e("str:" + str);
            map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n", ""));
            Logger.e("signature:" + RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n", ""));
            String result = mGson.toJson(map);
            Logger.e("sign:" + result);

            TradeRequestImpl
                    .reqUnAnalyticFixedQrCode((ReqUnFixedZFQrCodeModel) TransMapToBeanUtils.mapToBean(map, ReqUnFixedZFQrCodeModel.class))
                    .subscribe(new ProgressSubscriber<TradeInfoRespModel>(mContext) {
                        @Override
                        protected void _onNext(TradeInfoRespModel responseModel) {
                            Logger.e("bankCard list:" + responseModel.toString());
                            if (responseModel.isOk()) {
                                // 判断本地有没有绑定当前国家区域的银行卡
                                String sysareaId = responseModel.sysareaId;
                                Logger.e("sysareaId:" + sysareaId);
                                boolean bindBankCard = PayPwdManagerUtils.getInstance(mContext).bindSysAreaCountryCard(sysareaId, cards);
                                if (bindBankCard) {
                                    toNextPage(true,responseModel,qrCodeString,"","");
                                } else {
                                    // 2017/11/11 zhanghuan 未绑定当地区域的银行卡，跳转到绑定银行卡提示页面
                                    toNextPage(false,responseModel,qrCodeString,"1","1");
                                }
                            } else if (responseModel.needLogin()) {
                                DialogShowUtils.showReloginDailog(mContext, responseModel.msg);
                            } else {
                                DialogShowUtils.showCommonDialogWithFinish(mContext, responseModel.msg);
                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            Logger.e("message:" + message);
                            DialogShowUtils.showCommonDialogWithFinish(mContext, message);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            finish();
        }
    }

    *//**
     * 获取交易前商户信息
     *
     * @param bankCardInfo 绑定的银行卡信息
     *//*
    public void requestPreTradeInfoFromService(BankCardInfo bankCardInfo) {
        try {
            if (!TextUtils.isEmpty(qrCodeString) && null != bankCardInfo) {
                Map<String, String> map = new TreeMap<>();
                map.put("emvcode", qrCodeString);
                map.put("countryCode", countryCode);
                map.put("mobile", mobile);
                map.put("sessionID", sessionID);
                map.put("txnType", "74");
                String str = PayUtils.joinMapValue(map, '&');
                map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n", ""));
                String result = mGson.toJson(map);
                Logger.e("result", result);

                TradeRequestImpl
                        .getUnionPayPreTradeInfo((PreTradeInfoReqModel) TransMapToBeanUtils.mapToBean(map, PreTradeInfoReqModel.class))
                        .subscribe(new ProgressSubscriber<TradeInfoRespModel>(mContext) {
                            @Override
                            protected void _onNext(TradeInfoRespModel responseModel) {
                                Logger.e("后台查询到的预交易信息:" + responseModel.toString());
                                if (responseModel.isOk()) {
                                    Logger.e("交易金额：" + responseModel.getTxnAmt());
                                    toNextPage(true,responseModel,qrCodeString,"","");
                                } else if (responseModel.needLogin()) {
                                    DialogShowUtils.showReloginDailog(mContext, responseModel.getMsg());
                                } else {
                                    DialogShowUtils.showCommonDialogWithFinish(mContext, responseModel.getMsg());
                                }
                            }

                            @Override
                            protected void _onError(String message) {
                                Logger.e("message:" + message);
                                DialogShowUtils.showCommonDialogWithFinish(mContext, message);
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            finish();
            return;
        }
    }

    *//**
     * 请求解析httpUrl二维码，获取商户信息
     *//*
    public void requestHttpUrlTradeInfoFromService(BankInfoListRepModel cards) {
        try {
            if (!TextUtils.isEmpty(qrCodeString) && null != cards) {
                Map<String, String> map = new TreeMap<>();
                map.put("countryCode", countryCode);
                map.put("mobile", mobile);
                map.put("sessionID", sessionID);
                map.put("txnType", "78");
                map.put("urlcode", qrCodeString);
                String str = PayUtils.joinMapValue(map, '&');
                map.put("signature", RSACoder.sign(str.getBytes(), Constant.privateKey).replaceAll("\n", ""));
                String result = mGson.toJson(map);
                Logger.e("result", result);

                TradeRequestImpl
                        .requestAnalyHttpUrlCode((AnalyHttpUrlReqModel) TransMapToBeanUtils.mapToBean(map, AnalyHttpUrlReqModel.class))
                        .subscribe(new ProgressSubscriber<TradeInfoRespModel>(mContext) {
                            @Override
                            protected void _onNext(TradeInfoRespModel responseModel) {
                                Logger.e("analyticQrCode info:" + responseModel.toString());
                                if (responseModel.isOk()) {
                                    Logger.e("pay amount：" + responseModel.getTxnAmt());
                                    toNextPage(true,responseModel,qrCodeString,"","");
                                } else if (responseModel.needLogin()) {
                                    DialogShowUtils.showReloginDailog(mContext, responseModel.getMsg());
                                } else {
                                    DialogShowUtils.showCommonDialogWithFinish(mContext, responseModel.getMsg());
                                }
                            }

                            @Override
                            protected void _onError(String message) {
                                Logger.e("message:" + message);
                                DialogShowUtils.showCommonDialogWithFinish(mContext, message);
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            return;
        }
    }

    *//**
     * 跳转到下一页
     * @param bindedCard  是否绑定过卡
     * @param tradeInfoRespModel 二维码解析对象
     * @param qrCodeString 二维码
     * @param qrPayCardType 要绑定卡的标识  0:未知  1:中付银行卡  2:银联国际银行卡
     * @param bindLocalCard 绑定当前地区银行卡还是由用户自己选择  0:用户选择  1：当前地区
     *//*
    public void toNextPage(boolean bindedCard,TradeInfoRespModel tradeInfoRespModel,String qrCodeString,
                           String qrPayCardType, String bindLocalCard) {
        try {
            Bundle bundle = new Bundle();
            if(bindedCard) {
                bundle.putString("result", qrCodeString);
                if (tradeInfoRespModel != null)
                    bundle.putParcelable(ConstantUtils.QRCODE_PAY_RESPONSE_MODEL, tradeInfoRespModel);
                openActivity(InputPayAmountActivity.class, bundle);
            } else {
                bundle.putString("qrPayCardType", qrPayCardType);
                bundle.putString("bindLocalCard", bindLocalCard);
                openActivity(AddBankCardTipsActivity.class, bundle);
            }
            finish();
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }*/

    /**
     * 初始化相机
     *
     * @param surfaceHolder
     */
    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);//打开相机
        } catch (IOException | RuntimeException ioe) {
            return;
        }
        if (mHandler == null) {
            mHandler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return mViewfinderView;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public void drawViewfinder() {
        mViewfinderView.drawViewfinder();
    }

    /**
     * 扫描正确后的震动声音,如果感觉apk大了,可以删除
     */
    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it too loud, so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//系统铃声
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);//来读取(android)app的raw文件夹下的数据
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();//以上两句读流后关闭流
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);//设置声音
                mediaPlayer.prepare();//准备播放
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);//手机振动
            vibrator.vibrate(VIBRATE_DURATION);//振动时间
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);//播放完成     seekto方法的参数是毫秒
        }
    };
}
