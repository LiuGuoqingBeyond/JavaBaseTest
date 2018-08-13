package com.uppayplugin.unionpay.javabasetes.utils.pwdmanager;

import android.content.Context;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.config.Constant;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;

/**
 * 交易支付工具类
 *
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/8 17:34
 * @email：zhanghuan@xinguodu.com
 */
public class PayPwdManagerUtils {

    private static PayPwdManagerUtils instance;
    // 上下文对象
    private static Context mContext;
    // 用户本地配置文件
    private PreferencesUtil prefs;

    private PayPwdManagerUtils() {
    }

    /**
     * 获取接口实例
     *
     * @param context 上下文对象
     */
    public static PayPwdManagerUtils getInstance(Context context) {
        mContext = context;
        Logger.d("mContext:" + mContext);
        if (instance == null) {
            synchronized (PayPwdManagerUtils.class) {
                if (instance == null) {
                    instance = new PayPwdManagerUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 从本地查询用户是否设置过了支付密码
     *
     * @return true:设置过  false 未设置
     */
    public boolean checkSetPayPwdFromLocal() {
        prefs = new PreferencesUtil(mContext);
        String isSetPayPwd = prefs.readPrefs(Constant.PREFES_IS_SET_PASSWROD);
        Logger.e("isSetPayPwd:" + isSetPayPwd);
        if (!TextUtils.isEmpty(isSetPayPwd) && (isSetPayPwd.toLowerCase()).equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * 从本地查询是否有绑定银行卡
     *
     * @return
     *//*
    public boolean checkAddedBankCardFromLocal() {
        prefs = new PreferencesUtil(mContext);
        if (!TextUtils.isEmpty(prefs.readPrefs(Constant.PREFES_CARDCODELIST))) {
            Logger.e("JSON", prefs.readPrefs(Constant.PREFES_CARDCODELIST));
            BankInfoListModel cards = new Gson().fromJson(prefs.readPrefs(Constant.PREFES_CARDCODELIST), BankInfoListModel.class);
            if (cards != null && cards.list != null && cards.list.size() > 0) {
                return true;
            }
        }

        return false;
    }

    *//**
     * 获取绑定的银行卡列表
     *//*
    public BankInfoListRepModel getBindedBankCardInfo() {
        prefs = new PreferencesUtil(mContext);
        BankInfoListRepModel cards = null;
        try {
            cards = new Gson().fromJson(prefs.readPrefs(Constant.PREFES_CARDCODELIST),
                    BankInfoListRepModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cards;
    }

    *//**
     * 更新银行卡状态标识
     *
     * @param list
     * @param choseCard
     * @return
     *//*
    public ArrayList<BankCardInfos> replaceBankCardStatus(ArrayList<BankCardInfos> list,BankCardInfos choseCard
                                                         ,String cardStatus) {
        if(null == list || null == choseCard) return list;
        try {
            if(!TextUtils.isEmpty(cardStatus) && cardStatus.equals("1")) {
                for (BankCardInfos bankCardInfo : list) {
                    if (bankCardInfo.getCardNum().equals(choseCard.getCardNum())) {
                        bankCardInfo.setCardStatus("1");
                    }
                }
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        return list;
    }

    *//**
     * 重置银行卡状态
     *
     * @param list
     * @return
     *//*
    public ArrayList<BankCardInfos> resetBankCardStatus(ArrayList<BankCardInfos> list) {
        if(null == list) return list;
        try {
            for (BankCardInfos bankCardInfo : list) {
                bankCardInfo.setCardStatus("0");
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        return list;
    }

    *//**
     * 付款码更新银行卡状态标识
     *
     * @param list
     * @param choseCard
     * @return
     *//*
    public ArrayList<BankCardInfos> replaceQRPayBankCardStatus(ArrayList<BankCardInfos> list,BankCardInfos choseCard
            ,String cardStatus) {
        if(null == list || null == choseCard) return list;
        try {
            if(!TextUtils.isEmpty(cardStatus) && cardStatus.equals("1")) {
                for (BankCardInfos bankCardInfo : list) {
                    if (bankCardInfo.getCardNum().equals(choseCard.getCardNum())) {
                        bankCardInfo.setQrCardStatus("1");
                    }
                }
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        return list;
    }

    *//**
     * 付款码 重置余额状态标识
     *
     * @param list
     * @return
     *//*
    public ArrayList<BankCardInfos> resetQRPayBankCardStatus(ArrayList<BankCardInfos> list) {
        if(null == list) return list;
        try {
            for (BankCardInfos bankCardInfo : list) {
                bankCardInfo.setQrCardStatus("0");
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        return list;
    }

    *//**
     * 根据类型获取对应的银行卡列表
     *
     * @param type 0:中付码  1：银联国际码
     * @param list
     * @return
     *//*
    public ArrayList<BankCardInfos> getBankCardList(int type, ArrayList<BankCardInfos> list) {
        ArrayList<BankCardInfos> bankCardList = new ArrayList<BankCardInfos>();
        if (null != list && list.size() > 0) {
            switch (type) {
                case 0x00:
                    for (BankCardInfos bankCardInfo : list) {
                        if (bankCardInfo.getChannelType().contains(ConstantUtils.ZHONGFU_CARDTYPE)) {
                            bankCardList.add(bankCardInfo);
                        }
                    }
                    break;
                case 0x01:
                    for (BankCardInfos bankCardInfo : list) {
                        if (bankCardInfo.getChannelType().contains(ConstantUtils.YINLIAN_CARDTYPE)) {
                            bankCardList.add(bankCardInfo);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return bankCardList;
    }


    *//**
     * 根据二维码类型查询是否绑定过对应银行卡
     *
     * @param codeType 二维码类型  0:中付码  1：银联码
     * @param cards    银行卡对象
     * @return
     *//*
    public boolean bindBankCard(int codeType, BankInfoListRepModel cards) {
        boolean bindBankCard = false;  // 默认没有绑定过中付
        try {
            *//*for (BankCardInfo bankCardInfo : cards.list) {
                Logger.d("channelType:" + bankCardInfo.getChannelType());
                Logger.d("cardNumber:" + bankCardInfo.getCardNum());
                Logger.d("sysareaId:" + bankCardInfo.getSysareaId());
                if (bankCardInfo.getChannelType().contains(
                        codeType == 0 ? ConstantUtils.ZHONGFU_CARDTYPE : ConstantUtils.YINLIAN_CARDTYPE)) {
                    bindBankCard = true;
                    break;
                }
            }*//*
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
        return bindBankCard;
    }

    *//**
     * 根据区域码查询是否有绑定对应区域的银行卡
     *
     * @param sysareaId 区域Id
     * @return
     *//*
    public boolean bindSysAreaCountryCard(String sysareaId, BankInfoListRepModel cards) {
        boolean bindBankCard = false;
        try {
            *//*for (BankCardInfo bankCardInfo : cards.list) {
                Logger.d("channelType:" + bankCardInfo.getChannelType());
                Logger.d("cardNumber:" + bankCardInfo.getCardNum());
                Logger.d("sysareaId:" + bankCardInfo.getSysareaId());
                if (bankCardInfo.getChannelType().contains(ConstantUtils.ZHONGFU_CARDTYPE)
                        && bankCardInfo.getSysareaId().toLowerCase().contains(sysareaId.toLowerCase())) {
                    bindBankCard = true;
                    break;
                }
            }*//*
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
        return bindBankCard;
    }

    *//**
     * 根据当前区域来选择对应的银行卡
     * @param isInternational 是否为国际银联卡
     * @param list
     * @return
     *//*
    public List<BankCardInfos> getSystemAreaBankList(boolean isInternational, List<BankCardInfos> list) {
        List<BankCardInfos> bankCardList = null;
        if(null == list || list.size() == 0) return list;
        try {
            bankCardList = new ArrayList<BankCardInfos>();
            if(isInternational) {
                for (BankCardInfos cardInfo:list) {
                    String up = cardInfo.getOpenCountry().UP;
                    if(!TextUtils.isEmpty(up) && up.equals("0")) {
                        bankCardList.add(cardInfo);
                    }
                }
            } else {
                for (BankCardInfos cardInfo:list) {
                    String my = cardInfo.getOpenCountry().MY;
                    String sg= cardInfo.getOpenCountry().SG;
                    String hk = cardInfo.getOpenCountry().HK;
                    if((!TextUtils.isEmpty(my) && my.equals("0")) ||
                            (!TextUtils.isEmpty(sg) && sg.equals("0")) ||
                            (!TextUtils.isEmpty(hk) && hk.equals("0"))) {
                        bankCardList.add(cardInfo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
        return bankCardList;
    }

    *//**
     * 根据当前区域来选择对应的银行卡
     * @param isInternational 是否为国际银联卡
     * @param list
     * @return
     *//*
    public ArrayList<BankCardInfos> getSysAreaBankList(boolean isInternational, List<BankCardInfos> list) {
        ArrayList<BankCardInfos> bankCardList = null;
        if(null == list || list.size() == 0) return bankCardList;
        try {
            bankCardList = new ArrayList<BankCardInfos>();
            if(isInternational) {
                for (BankCardInfos cardInfo:list) {
                    String up = cardInfo.getOpenCountry().UP;
                    if(!TextUtils.isEmpty(up) && up.equals("0")) {
                        bankCardList.add(cardInfo);
                    }
                }
            } else {
                for (BankCardInfos cardInfo:list) {
                    String my = cardInfo.getOpenCountry().MY;
                    String sg= cardInfo.getOpenCountry().SG;
                    String hk = cardInfo.getOpenCountry().HK;
                    if((!TextUtils.isEmpty(my) && my.equals("0")) ||
                            (!TextUtils.isEmpty(sg) && sg.equals("0")) ||
                            (!TextUtils.isEmpty(hk) && hk.equals("0"))) {
                        bankCardList.add(cardInfo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
        return bankCardList;
    }

    *//**
     * 判断银行卡是否开通过
     * @param sysareaid
     * @param bankCardInfo
     * @return
     *//*
    public boolean areaOpendCountry(String sysareaid,BankCardInfo bankCardInfo) {
        boolean isOpened = false;
        try {
            if (TextUtils.isEmpty(sysareaid) || null == bankCardInfo) return isOpened;
            switch (sysareaid) {
                case "HK":
                    isOpened = bankCardInfo.getOpenCountry().HK.equals("0");
                    break;
                case "SG":
                    isOpened = bankCardInfo.getOpenCountry().SG.equals("0");
                    break;
                case "MY":
                    isOpened = bankCardInfo.getOpenCountry().MY.equals("0");
                    break;
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        return isOpened;
    }*/

}
