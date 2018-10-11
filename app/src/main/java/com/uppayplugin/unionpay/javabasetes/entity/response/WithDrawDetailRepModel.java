package com.uppayplugin.unionpay.javabasetes.entity.response;

import android.text.TextUtils;

import java.util.List;

/**
 * User: LiuGuoqing
 * Data: 2018/9/19 0019.
 */

public class WithDrawDetailRepModel {
    private String returnCode;
    private String returnMsg;
    private DataBean data;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String obtainSalesSlipId;
        private String merchantName;
        private String merchantCode;
        private String merchantType;
        private String machineNo;
        private String withdrawAmt;
        private String withdrawFeeRate;
        private String signContractFeeRate;
        private String remitFee;
        private String withdrawTime;
        private String approveStatus;
        private String remitStatus;
        private String remitTime;
        private String remark;
        private String approveStatusChinese;
        private String merchantTypeChinese;
        private List<String> imgPaths;

        public String getMerchantTypeChinese() {
            return merchantTypeChinese;
        }

        public void setMerchantTypeChinese(String merchantTypeChinese) {
            this.merchantTypeChinese = merchantTypeChinese;
        }

        public String getApproveStatusChinese() {
            return approveStatusChinese;
        }

        public void setApproveStatusChinese(String approveStatusChinese) {
            this.approveStatusChinese = approveStatusChinese;
        }

        public List<String> getImgPaths() {
            return imgPaths;
        }

        public void setImgPaths(List<String> imgPaths) {
            this.imgPaths = imgPaths;
        }

        public String getObtainSalesSlipId() {
            return obtainSalesSlipId;
        }

        public void setObtainSalesSlipId(String obtainSalesSlipId) {
            this.obtainSalesSlipId = obtainSalesSlipId;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public String getMerchantType() {
            return merchantType;
        }

        public void setMerchantType(String merchantType) {
            this.merchantType = merchantType;
        }

        public String getMachineNo() {
            return machineNo;
        }

        public void setMachineNo(String machineNo) {
            this.machineNo = machineNo;
        }

        public String getWithdrawAmt() {
            return withdrawAmt;
        }

        public void setWithdrawAmt(String withdrawAmt) {
            this.withdrawAmt = withdrawAmt;
        }

        public String getWithdrawFeeRate() {
            return withdrawFeeRate;
        }

        public void setWithdrawFeeRate(String withdrawFeeRate) {
            this.withdrawFeeRate = withdrawFeeRate;
        }

        public String getSignContractFeeRate() {
            return signContractFeeRate;
        }

        public void setSignContractFeeRate(String signContractFeeRate) {
            this.signContractFeeRate = signContractFeeRate;
        }

        public String getRemitFee() {
            return remitFee;
        }

        public void setRemitFee(String remitFee) {
            this.remitFee = remitFee;
        }

        public String getWithdrawTime() {
            return withdrawTime;
        }

        public void setWithdrawTime(String withdrawTime) {
            this.withdrawTime = withdrawTime;
        }

        public String getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(String approveStatus) {
            this.approveStatus = approveStatus;
        }

        public String getRemitStatus() {
            return remitStatus;
        }

        public void setRemitStatus(String remitStatus) {
            this.remitStatus = remitStatus;
        }

        public String getRemitTime() {
            return remitTime;
        }

        public void setRemitTime(String remitTime) {
            this.remitTime = remitTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public boolean isOk() {
        return TextUtils.equals("00", returnCode);
    }
}
