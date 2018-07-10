package com.uppayplugin.unionpay.javabasetest.config;

import android.os.Environment;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/7 17:21
 * @email：zhanghuan@xinguodu.com
 */
public class ConstantUtils {

    // 默认每次登录需要查询个人账户信息
    public static boolean needQueryUserAccount = true;

    // 用户账户信息对象
//    public static UserAccountResponseModel mUserAccountResponseModel;

    // 头像存储路径
    public static final String IMAGE_PATH = Environment.getExternalStorageDirectory().toString()+"/unionPay/user_icon/";

    // 用户头像文件名称
    public static final String IMAGE_NAME = "user_icon.jpg";

    // 中付二维码前缀
    public static final String ZHONGFU_CODE = "https://u.sinopayonline.com";

    // 银联国际二维码前缀
    public static final String YINLIAN_CODE = "00";

    // URL二维码前缀
    public static final String HTTP_URL_CODE = "https://qr";

    // 中付卡标识
    public static final String ZHONGFU_CARDTYPE= "000001";
    // 国际卡标识
    public static final String YINLIAN_CARDTYPE = "000003";

    // 重复登录错误码
    public static final String RE_LOGIN_ERROR = "168";

    // 银联二维码请求响应模型
    public static final String UNIONPAY_RESPONSE_MODEL = "responseInfoModel";

    // 中付二维码请求响应模型
    public static final String ZFPAY_RESPONSE_MODEL = "zfResponseInfoModel";

    // 中付非固定二维码解析响应模型
    public static final String ZFPAY_UNFIXEDCODE_RESMODEL = "analyticUnFixedModel";

    // 中付非固定二维码 --费率响应模型
    public static final String ZFPAY_UNFIXEDCODE_RATE_RESMODEL = "unFixedRateModel";

    // 中付非固定二维码 --下单响应模型
    public static final String ZFPAY_UNFIXEDCODE_TRADE_RESMODEL = "unFixedTradeModel";

    // 中付固定二维码类型过滤
    public static final String SPILT_FILTER = "payId=";

    // 二维码交易请求响应模型
    public static final String QRCODE_PAYRESULT_REPMODEL = "qrCodePayRepModel";

    // 请求最大次数
    public static final int REQUEST_TRYTIME = 3;

    // 本地设置了支付密码标识
    public static final String IS_SET_PAYPWD_ON_LOCAL = "true";

    // 本地未设置支付密码标识
    public static final String NOT_SET_PAYPWD_ON_LOCAL = "false";

    public static final String TRADE_RECORD_DETAIL_MODEL = "tradeRecordDetailModel";
}
