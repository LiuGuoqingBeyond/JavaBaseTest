package com.example.testdemolib.entity.respons;

import java.util.List;

/**
 * User: LiuGq
 * Date: 2017/12/29
 * Time: 15:06
 */

public class TradeRecordAllRespone extends BaseRepModel{
    private List<QrCodePayInfoResponseModel> list;

    private int totalRows;

    private String account;

    @Override
    public String toString() {
        return "{" +
                "msg='" + msg + '\'' +
                ", list=" + list +
                ", totalRows=" + totalRows +
                ", account='" + account + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setList(List<QrCodePayInfoResponseModel> list) {
        this.list = list;
    }

    public List<QrCodePayInfoResponseModel> getList() {
        return this.list;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalRows() {
        return this.totalRows;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }
}
