package com.uppayplugin.unionpay.javabasetes.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * User: LiuGuoqing
 * Data: 2018/9/13 0013.
 */

public class TestBean {

    private static TestBean testBean;

    public TestBean() {
    }

    public static synchronized TestBean getInstance() {
        if (testBean == null) {
            testBean = new TestBean();
        }
        return testBean;
    }

    /**
     * 模拟数据
     *
     * @return
     */
    public List<AuditTestBean> getAuditList() {
        List<AuditTestBean> list = new ArrayList<>();
        AuditTestBean auditTestBean = new AuditTestBean();
        auditTestBean.setShopName("深圳明日商行有限公司");
        auditTestBean.setApplyName("张三");
        auditTestBean.setMerNumber("84721267638373");
        auditTestBean.setAuditStatus("待审核");
        list.add(auditTestBean);

        AuditTestBean auditTestBean1 = new AuditTestBean();
        auditTestBean1.setShopName("深圳明日商行有限公司");
        auditTestBean1.setApplyName("张三");
        auditTestBean1.setMerNumber("84721267638373");
        auditTestBean1.setAuditStatus("待审核");
        list.add(auditTestBean1);

        AuditTestBean auditTestBean2 = new AuditTestBean();
        auditTestBean2.setShopName("深圳明日商行有限公司");
        auditTestBean2.setApplyName("张三");
        auditTestBean2.setMerNumber("84721267638373");
        auditTestBean2.setAuditStatus("待审核");
        list.add(auditTestBean2);

        AuditTestBean auditTestBean3 = new AuditTestBean();
        auditTestBean3.setShopName("深圳明日商行有限公司");
        auditTestBean3.setApplyName("张三");
        auditTestBean3.setMerNumber("84721267638373");
        auditTestBean3.setAuditStatus("待审核");
        list.add(auditTestBean3);

        AuditTestBean auditTestBean4 = new AuditTestBean();
        auditTestBean4.setShopName("深圳明日商行有限公司");
        auditTestBean4.setApplyName("张三");
        auditTestBean4.setMerNumber("84721267638373");
        auditTestBean4.setAuditStatus("待审核");
        list.add(auditTestBean4);

        AuditTestBean auditTestBean5 = new AuditTestBean();
        auditTestBean5.setShopName("深圳明日商行有限公司");
        auditTestBean5.setApplyName("张三");
        auditTestBean5.setMerNumber("84721267638373");
        auditTestBean5.setAuditStatus("待审核");
        list.add(auditTestBean5);

        AuditTestBean auditTestBean6 = new AuditTestBean();
        auditTestBean6.setShopName("深圳明日商行有限公司");
        auditTestBean6.setApplyName("张三");
        auditTestBean6.setMerNumber("84721267638373");
        auditTestBean6.setAuditStatus("待审核");
        list.add(auditTestBean6);

        AuditTestBean auditTestBean7 = new AuditTestBean();
        auditTestBean7.setShopName("深圳明日商行有限公司");
        auditTestBean7.setApplyName("张三");
        auditTestBean7.setMerNumber("84721267638373");
        auditTestBean7.setAuditStatus("待审核");
        list.add(auditTestBean7);

        AuditTestBean auditTestBean8 = new AuditTestBean();
        auditTestBean8.setShopName("深圳明日商行有限公司");
        auditTestBean8.setApplyName("张三");
        auditTestBean8.setMerNumber("84721267638373");
        auditTestBean8.setAuditStatus("待审核");
        list.add(auditTestBean8);

        AuditTestBean auditTestBean9 = new AuditTestBean();
        auditTestBean9.setShopName("深圳明日商行有限公司");
        auditTestBean9.setApplyName("张三");
        auditTestBean9.setMerNumber("84721267638373");
        auditTestBean9.setAuditStatus("待审核");
        list.add(auditTestBean9);

        AuditTestBean auditTestBean10 = new AuditTestBean();
        auditTestBean10.setShopName("深圳明日商行有限公司");
        auditTestBean10.setApplyName("张三");
        auditTestBean10.setMerNumber("84721267638373");
        auditTestBean10.setAuditStatus("待审核");
        list.add(auditTestBean10);

        return list;
    }

    /**
     * 模拟数据
     *
     * @return
     */
    public List<TradeTestBean> getTradeList() {
        List<TradeTestBean> list = new ArrayList<>();
        TradeTestBean tradeTestBean = new TradeTestBean();
        tradeTestBean.setMerName("深圳明日商行有限公司");
        tradeTestBean.setTradestatus("商户类型：D1");
        tradeTestBean.setTradeMoney("84721267638373");
        tradeTestBean.setMerNumber("¥ 9.000");
        tradeTestBean.setTradeFastPay("已出款");
        tradeTestBean.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean);

        TradeTestBean tradeTestBean1 = new TradeTestBean();
        tradeTestBean1.setMerName("深圳明日商行有限公司");
        tradeTestBean1.setTradestatus("商户类型：D1");
        tradeTestBean1.setMerNumber("84721267638373");
        tradeTestBean1.setMerNumber("¥ 9.000");
        tradeTestBean1.setTradeFastPay("已出款");
        tradeTestBean1.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean1);

        TradeTestBean tradeTestBean2 = new TradeTestBean();
        tradeTestBean2.setMerName("深圳明日商行有限公司");
        tradeTestBean2.setTradestatus("商户类型：D1");
        tradeTestBean2.setMerNumber("84721267638373");
        tradeTestBean2.setMerNumber("¥ 9.000");
        tradeTestBean2.setTradeFastPay("已出款");
        tradeTestBean2.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean2);

        TradeTestBean tradeTestBean3 = new TradeTestBean();
        tradeTestBean3.setMerName("深圳明日商行有限公司");
        tradeTestBean3.setTradestatus("商户类型：D1");
        tradeTestBean3.setMerNumber("84721267638373");
        tradeTestBean3.setMerNumber("¥ 9.000");
        tradeTestBean3.setTradeFastPay("已出款");
        tradeTestBean3.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean3);

        TradeTestBean tradeTestBean4 = new TradeTestBean();
        tradeTestBean4.setMerName("深圳明日商行有限公司");
        tradeTestBean4.setTradestatus("商户类型：D1");
        tradeTestBean4.setMerNumber("84721267638373");
        tradeTestBean4.setMerNumber("¥ 9.000");
        tradeTestBean4.setTradeFastPay("已出款");
        tradeTestBean4.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean4);

        TradeTestBean tradeTestBean5 = new TradeTestBean();
        tradeTestBean5.setMerName("深圳明日商行有限公司");
        tradeTestBean5.setTradestatus("商户类型：D1");
        tradeTestBean5.setMerNumber("84721267638373");
        tradeTestBean5.setMerNumber("¥ 9.000");
        tradeTestBean5.setTradeFastPay("已出款");
        tradeTestBean5.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean5);

        TradeTestBean tradeTestBean6 = new TradeTestBean();
        tradeTestBean6.setMerName("深圳明日商行有限公司");
        tradeTestBean6.setTradestatus("商户类型：D1");
        tradeTestBean6.setMerNumber("84721267638373");
        tradeTestBean6.setMerNumber("¥ 9.000");
        tradeTestBean6.setTradeFastPay("已出款");
        tradeTestBean6.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean6);

        TradeTestBean tradeTestBean7 = new TradeTestBean();
        tradeTestBean7.setMerName("深圳明日商行有限公司");
        tradeTestBean7.setTradestatus("商户类型：D1");
        tradeTestBean7.setMerNumber("84721267638373");
        tradeTestBean7.setMerNumber("¥ 9.000");
        tradeTestBean7.setTradeFastPay("已出款");
        tradeTestBean7.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean7);

        TradeTestBean tradeTestBean8 = new TradeTestBean();
        tradeTestBean8.setMerName("深圳明日商行有限公司");
        tradeTestBean8.setTradestatus("商户类型：D1");
        tradeTestBean8.setMerNumber("84721267638373");
        tradeTestBean8.setMerNumber("¥ 9.000");
        tradeTestBean8.setTradeFastPay("已出款");
        tradeTestBean8.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean8);

        TradeTestBean tradeTestBean9 = new TradeTestBean();
        tradeTestBean9.setMerName("深圳明日商行有限公司");
        tradeTestBean9.setTradestatus("商户类型：D1");
        tradeTestBean9.setMerNumber("84721267638373");
        tradeTestBean9.setMerNumber("¥ 9.000");
        tradeTestBean9.setTradeFastPay("已出款");
        tradeTestBean9.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean9);

        TradeTestBean tradeTestBean10 = new TradeTestBean();
        tradeTestBean10.setMerName("深圳明日商行有限公司");
        tradeTestBean10.setTradestatus("商户类型：D1");
        tradeTestBean10.setMerNumber("84721267638373");
        tradeTestBean10.setMerNumber("¥ 9.000");
        tradeTestBean10.setTradeFastPay("已出款");
        tradeTestBean10.setTradeTime("2018-08-17 12:52:30");
        list.add(tradeTestBean10);

        return list;
    }

    /**
     * 模拟数据
     *
     * @return
     */
    public List<BankTestBean> getBankNameList() {
        List<BankTestBean> list = new ArrayList<>();
        BankTestBean bankTestBean = new BankTestBean();
        bankTestBean.setBankName("中国光大银行");
        list.add(bankTestBean);
        BankTestBean bankTestBean1 = new BankTestBean();
        bankTestBean1.setBankName("中国农业银行");
        list.add(bankTestBean1);
        BankTestBean bankTestBean2 = new BankTestBean();
        bankTestBean2.setBankName("中国银行");
        list.add(bankTestBean2);
        BankTestBean bankTestBean3 = new BankTestBean();
        bankTestBean3.setBankName("交通银行");
        list.add(bankTestBean3);
        BankTestBean bankTestBean4 = new BankTestBean();
        bankTestBean4.setBankName("中信银行");
        list.add(bankTestBean4);
        BankTestBean bankTestBean5 = new BankTestBean();
        bankTestBean5.setBankName("广东发展银行");
        list.add(bankTestBean5);
        BankTestBean bankTestBean6 = new BankTestBean();
        bankTestBean6.setBankName("招商银行");
        list.add(bankTestBean6);
        BankTestBean bankTestBean7 = new BankTestBean();
        bankTestBean7.setBankName("兴业银行");
        list.add(bankTestBean7);
        BankTestBean bankTestBean8 = new BankTestBean();
        bankTestBean8.setBankName("恒丰银行");
        list.add(bankTestBean8);
        BankTestBean bankTestBean9 = new BankTestBean();
        bankTestBean9.setBankName("浙商银行");
        list.add(bankTestBean9);
        BankTestBean bankTestBean10 = new BankTestBean();
        bankTestBean10.setBankName("贵州银行");
        list.add(bankTestBean10);

        return list;
    }
}
