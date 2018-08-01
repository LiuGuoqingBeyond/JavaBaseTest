package com.sinopaylib.impl;

import android.os.Environment;

import java.io.File;

//import com.zhongfu.upop.BuildConfig;

public class Constant {
    public static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwUje6Zui/mX7Uns+uTb8VGQEK4Qoq4veeobUljfsiNuTg8FQECw6dMUje6pic/gwW2SGqzQJjfP1YqFc2FCHOsoCGPNazU74kYmRzpL1m1jTwatS8Km7dE9oENyhpItXMYemuEV9RTR/z8OsQbPVo/mp7axYYJtkmcR3P9eeAFkOTNO51N775JXTRbchceTDiyxNAg4Nq/fHjBOzPx5VmgYBY2qF1YNZqGZ0/kIU31Ei9z7aLjM5aPuGYTDEe5nz0E3zXizfpAPoNBlIX9rgI7rhhye1jTRwzoOVIsx7JGF9QeWoir0krzLzF6Yt7Jfn9GKO5PzC3KsnUjJDWQfC7QIDAQAB";
    public static final String privateKey = "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";
    public static final String TAG_COUNTRTCODE = "codelist";
    public static final String TAG_CARDLISTCODE = "cardcodelist";
    public static String ssy_privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDBSN7pm6L+ZftSez65NvxUZAQrhCiri956htSWN+yI25ODwVAQLDp0xSN7qmJz+DBbZIarNAmN8/VioVzYUIc6ygIY81rNTviRiZHOkvWbWNPBq1Lwqbt0T2gQ3KGki1cxh6a4RX1FNH/Pw6xBs9Wj+antrFhgm2SZxHc/154AWQ5M07nU3vvkldNFtyFx5MOLLE0CDg2r98eME7M/HlWaBgFjaoXVg1moZnT+QhTfUSL3PtouMzlo+4ZhMMR7mfPQTfNeLN+kA+g0GUhf2uAjuuGHJ7WNNHDOg5UizHskYX1B5aiKvSSvMvMXpi3sl+f0Yo7k/MLcqydSMkNZB8LtAgMBAAECggEBALk7s92feUJiy8uNL1xjNMlPnKtzmWJlxg4Yny1jZPz3qdSP88G8ET2NHGdMr9PCG93KZkE2up1fGmCukgMAkcimhgIRXoj50L/xXlnhfx2ksDOfk/nuQCbEXtAiEYtWunZOMVnRxq8HFCIxvSiuRuVBxDdxfrT+1xeCMGJvqg1bn04ATVRlnVMtlLiBAYgLaNC5LYArhX+9pcy5Z/OyvFKKUzuZqIZW+YdG33tNQmVwTDqcbHUGGOszcJAUyTHVOTs0pKLkyhWqmnhsx9kUbARJ/malhdt5EtCp51vT/CvDVQUxXRY1bAxqeCcvlzojRkdg/9kXYJZw17gSq6FS50UCgYEA9KA8TLL6+LvzXnSwi1Q0PhY4RTfQVbMlf12lfoEwxubYVbU13YHuo4exYT41U3raj+FZkf0hYhI8mqlINLZMUJFLySc0GmiW51baaaV8LZzSGHVqacH52mZyhQzx0Avxtnfd5tVGqyh7bHpRzzPMDzdE4rF30uWmp4hflf9iaLMCgYEAykWGGh9KWVzJgS25BkIXo9vpmV3CQjMWQT4W7aw3DBeaktRdji3Y9+N58Z8OSxagHkfT6fpJSXT2sHSjFulOAAvX3Gf70cF5Sa514eVG0eSQI2LkefVauKuWHWtCxd4VNpZJy2c8Clm6a+oLa6fyvfPrnmQ2zmVZNrLBYkyYtd8CgYEAvlxe8DSrVpoz9YIKzDGXgeUansqjJb2F5YpDOaFhCrcjC/c61ofA46K5ZvnY14CXyjVL1W5M5xtX6+blmJdv5ZZbikomopJXM+kDiAjODFSnrX7ZczkOrGTioyUCm6pfy2WV2YaIOrMC8NRzCzG8ju6sfVgq7YPmXyKhd4FH3D8CgYA9W2macvNyuBrH/L6rj1Dse8EYrMBIpIaTs+nwntTz7hUNhS37+xxd1bsf+Ee1r19S0CcQSsQAbGKIlFkEkjD/ApiwWzqgHjhUiX9ozsv0z4iJs23rMOocKrCWq/rNWtjEfkJUWTR1T1KleZGNTpzr0hofKfcWFNhDJ2ZSEzofsQKBgQDZXi2wN4gVvRjfc5Xr8QFRDpjqg5RYYcr3jNaEBQzjezM9rPm/6dApwcia2L9WNTBBk3r0xt1w/ubehJDqp7a4BzL0efvmVL3iOQpfRvlhev0H90IgdmHdPXG6wiJhLDqk23xxgj8KW9PZCIaAOp71/6MrcJ5m1pqDOkdfYc4VGg==";
    // Package Name
    public static String PACKAGE_NAME = "com.zhongfu.upop";

    // APP Version Code
    public static int VERSION_CODE = 5;

    // APP Version Name
    public static String VERSION_NAME = "1.1.1";

    // HTTP PATH
    //192.168.200.247
    //public static final String HTTP_PATH = "http://192.168.200.247:8080/newSWApp/";
//	public static final String HTTP_PATH = "http://210.13.122.106:8083/newSWApp/";http://192.168.1.86:8080/UGateWay_2v/appService
//	public static final String HTTP_PATH = "https://u.sinopayonline.com/UGateWay/appService";

    // 本地测试环境地址
//    public static final String HTTP_PATH = BuildConfig.BASEURL + "UGateWay/appService";

    public static final String URL_ALL_PAY_CODE = "https://u.sinopayonline.com/UGateWay/scanCode?m=";
    public static final String URL_ALL_PAY_CODE1 = "https://u.sinopayonline.com/UGateWay/scanCode?payId=";
    // SD Card路径
    public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    // 存储路径
    public static final String BASE_PATH = SD_PATH + File.separator + PACKAGE_NAME + File.separator;

    // 缓存图片路径
    public static final String BASE_IMAGE_CACHE = BASE_PATH + "/cache/images/";

    // 屏幕高度
    public static int SCREEN_HEIGHT = 800;

    // 屏幕宽度
    public static int SCREEN_WIDTH = 480;

    // 屏幕密度
    public static float SCREEN_DENSITY = 1.5f;

    // 服务端读取数据时，返回数据的状态
    public static final String RESULT_SUCCESS = "0";// 表示返回数据成功
    public static final String RESULT_ERROR = "-1";// 表示返回数据失败

    // Preferences常量定义
    public static final String CVM = "cvm";
    public static final String OTPMETHOD = "otpMethod";
    public static final String QRCARD_INTERCARD = "qrcardintercard";
    public static final String PREFES_MOBILE = "mobile";
    public static final String PREFES_USERNAME = "username";
    public static final String PREFES_PASSWORD = "password";
    public static final String PREFES_REMEMBER = "remember";
    public static final String PREFES_TEMPKEY = "tempKey";
    public static final String PREFES_TEMPSESSIONID = "tempSessionId";
    public static final String PREFES_KEY = "key";
    public static final String PREFES_SESSIONID = "sessionID";
    public static final String PREFES_RANDOMKEY = "randomKey";
    public static final String PREFES_CODELIST = "countrycodelist";
    public static final String PREFES_COUNTRYCODE = "countrycode";
    public static final String PREFES_COUNTRYCODEPOSITION = "countryCode_position";
    public static final String PREFES_CARDCODELIST = "cardlist";
    public static final String PREFES_RANDOMNO = "randomNo";
    public static final String UPOPTIME = "upopTime";
    public static final String PREFES_LATITUDE = "latitude";//纬度
    public static final String PREFES_LONGITUDE = "longitude";//经度
    public static final String PREFES_BASESTATION = "basestation";//基站
    public static final String PREFES_IP = "ip";
    public static final String PREFES_CODE = "countrycodelist";
    public static final String PREFES_CARDCODE = "cardcodelist";
    //public static final String PREFES_COUNTRYCODE=cou
    public static final String PREFES_AUTO = "auto";
    public static final String PREFES_CARDNAME = "cardname";
    public static final String PREFES_IDCARD = "idcard";
    public static final String PREFES_CARDMOBILE = "cardmobile"; //手机号
    public static final String PREFES_CREDIT = "credit";
    public static final String PREFES_IS_SET_PASSWROD = "isSetPassword";//是否设置了支付密码;
    public static final String PREFES_COUNTRY_CODE = "country_code";
    public static final String PREFES_IMEI_CODE = "IMEI";
    public static final String PREFES_LAST_CONSUMPTION_CARD = "last_consumption_card";
    public static final String PREFES_INTRADEORDERNO = "intradeorderno";

    // 2017/11/7 zhanghuan 新增多语言配置设置  // 1:简体中文  2：英文  3：繁体中文
    public static final String PREFES_MULTI_LANGUAGE = "1";


    public static final String AUTH_STATUS_0 = "审核通过";
    public static final String AUTH_STATUS_1 = "审核未通过";
    public static final String AUTH_STATUS_2 = "审核中";
    public static final String AUTH_STATUS_3 = "未实名认证";
    public static final String SIGN = "SIGN";
    public static final String LOGIN_KEY = "判断登陆名是否是当前登陆信息";

    // 支付宝配置
    public static final String ALIPAY_MERCHANT_NO = "950999999999999";// 商户号
    public static final String ALIPAY_TERMINAL_NO = "95999999";// 终端号

    // 公共事业费配置
    public static final String GGSYF_MERCHANT_NO = "58888888";// 商户号
    // public static final String GGSYF_KEY = "@zsf2014";// 请求序号
    public static final String GGSYF_KEY = "@sanwing@";// 请求序号

    // 生产环境蓝牙MPOS刷卡交易配置
    public static String MPOS_MERCHANT_NO = "110100072970003";// 商户号
    public static String MPOS_TERMINAL_NO = "00000003";// 终端号

    // IP PORT
    public static final String KEY_IP = "ip";
    public static final String KEY_PORT = "port";

    //
    public static final String KEY_MERCHANT_NO = "merchant_no";
    public static final String KEY_TERMINAL_NO = "terminal_no";
    public static final String KEY_USERID = "userId";

    public static final String APPTYPE_PERSON = "1";
    public static final String APPTYPE_BUSINESS = "3";
    public static final String APPTYPE_FOREIGN = "4";

    public static final String SMS_TYPE_REGISTER = "1";
    public static final String SMS_TYPE_OTHER = "2";

    //
    public static final String KEY_TPDU = "tpdu";
}
