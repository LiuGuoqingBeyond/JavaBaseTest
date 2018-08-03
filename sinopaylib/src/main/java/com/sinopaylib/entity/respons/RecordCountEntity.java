package com.sinopaylib.entity.respons;

import java.util.List;

/**
 * @project：ElongGradleTaskDemo-master
 * @author：- octopus on 2017/12/7 20:29
 * @email：zhanghuan@xinguodu.com
 */
public class RecordCountEntity {

    // 交易时间
    public String day = "";
    // 交易笔数
    public String dailyTotals = "";
    // 交易金额
    public String dailyAccount = "";

    // 交易币种
    public String curr = "";

    // 当日交易列表
    public List<RecordItemEntity> dailyList;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDailyTotals() {
        return dailyTotals;
    }

    public void setDailyTotals(String dailyTotals) {
        this.dailyTotals = dailyTotals;
    }

    public String getDailyAccount() {
        return dailyAccount;
    }

    public void setDailyAccount(String dailyAccount) {
        this.dailyAccount = dailyAccount;
    }

    public List<RecordItemEntity> getDailyList() {
        return dailyList;
    }

    public void setDailyList(List<RecordItemEntity> dailyList) {
        this.dailyList = dailyList;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getCurr() {
        return curr;
    }
}
