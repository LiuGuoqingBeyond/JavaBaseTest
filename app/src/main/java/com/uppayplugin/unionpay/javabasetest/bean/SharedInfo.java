package com.uppayplugin.unionpay.javabasetest.bean;

import java.util.Map;

public class SharedInfo {

    public static String CHOOSE_ADDCARD = "";
    public static String FASTMONEYRESULT = "";
    public static String FASTNOCARD = "";
    public static String CARDNUBER = "";
    public static String FAST_TO_LAST_CARDTYPE = "";
    public static String FAST_TO_LAST_CARDNB = "";
    public static String FASTMONEY = "";
    public static String CHOOSECARD = "";
    public static String EXTENDMSG = "";
    public static String EXIT = "";
    public static String CODE = "";

    public static String PASSWORD = "XZF_PASSWORD";


    public static String TOKENKEY = "TOKENKEY";

    public static String PACKAGE_NAME = "com.whty.xzfpos";
    public static String CARD_LIST = "";
    public static String CARD_NAME = "";
    public static String CARD_NUM = "";
    public static String CARD_PHONENO = "";
    public static String CARD_IDCARD = "";
    public static String CARD_TYPE = "";
    public static String CARD_EXPIRED = "";
    public static String CARD_CVN = "";

    //保存密码登录连接设备的点击状态
    public static String Clicknet = "";
    //	public static data
    public static String mac = "";
    public static String btName = "";
    public static String sn = "";
    public static String BDsn = "BDsn";
    public static String account = "";
    public static String terminalNo = "";
    public static String BDterminalNo = "BDterminalNo";
    public static String merchantNo = "";
    public static String BDmerchantNo = "BDmerchantNo";
    // 设备类型63250或71249
    public static String deviceType = "";
    // 固件版本
    public static String deviceVer = "";
    // 小票地址
    public static String TICKET_URL = "";
    // 二维码地址
    public static String QRCODE_URL = "";
    //行业分类
    public static String industryType = "0";
    public static String OptionalMerchantCode = "";
    public static String OptionalMerchantName = "";
    // 密码地址
    // public static String pwd_url =
    // "https://113.106.94.135:8443/mposp/pl/forgetpwd.jsp";
    public static String pwd_url = "https://mpos.weilapay.com:8443/mposp/pl/rpwd.jsp?loginNameStr=";
    // 代付
    // public static String PAY_FOR_OTHER_URL =
    // "https://113.106.94.135:8443/mposp/pl/pl.jsp";
    public static String PAY_FOR_OTHER_URL = "https://mpos.weilapay.com:8443/mposp/pl/pl.jsp?uid=";
    // 默认收款人
    // public static String DEFAULT_PEOPLE_URL =
    // "https://113.106.94.135:8443/mposp/pl/pl.jsp?set=1";
    public static String DEFAULT_PEOPLE_URL = "https://mpos.weilapay.com:8443/mposp/pl/pl.jsp?set=1&uid=";
    // 交易金额
    public static String amount = "0.01";
    // 查询小票时获取的响
    // public static TradeInfo TradeInfo = null;
//    public static TranAmtInfo tranAmtInfo = null;
    // 本手刷收款人信息
    public static Map<String, String> mapDefa = null;
    // 刷卡头标志
    public static String infoState = "1";
    // 是否允许交易标志
    public static String isAllowTrade = "";
    /**
     * 手机IMEI 设备ID
     */
    public static String IMEI = "";
    /**
     * 手机号码 有的能拿到，有的不可以，这个要看生产商是否注册了
     */
    public static String deviceNumber = "";
    /**
     * 位置信息，经纬度，格式为纬度/经度，+表示北纬、东经，-表示南纬、西经。举例：+37.12/-121.23或者+37/-121
     */
    public static String lbs = "";
    //public static NewLoginInfo data;
    public static String devName = "";
    public static String checked_fastpay = "";
    public static String MID = "";
    public static String BDMID = "";
    public static String OPENID = "";
    public static String TOKEN = "";

    // VIP秒到是否显示标识
    public static boolean tvFunOptionalShowed = false;
    // 是否自动进入VIP秒到标识
    public static boolean autoGoVIPFunOptional = false;

    public static String mUserName = "";
    public static String mUserPwd = "";



    /**
     * 重置所有共享信息(退出的时候调用)
     *
     * @return 重置成功返回true，否则返回false
     */
    public static boolean resetAllSharedInfos() {
        mac = "";
        btName = "";
        sn = "";
        account = "";
        terminalNo = "";
        merchantNo = "";
        // 设备类型63250或71249
        deviceType = "";
        // 固件版本
        deviceVer = "";
        // 小票地址
        TICKET_URL = "";

        // 交易金额
        amount = "0.01";
        // 查询小票时获取的响
//        tranAmtInfo = null;
        // TradeInfo = null;
        deviceNumber = "";
        isAllowTrade = "";
//        AccountInfo.logout();
        return true;
    }
}
