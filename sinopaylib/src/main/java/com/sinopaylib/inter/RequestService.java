package com.sinopaylib.inter;
import com.sinopaylib.entity.request.LoginAppModel;
import com.sinopaylib.entity.request.LoginGetSessionModel;
import com.sinopaylib.entity.respons.GetTemsessionRepModel;
import com.sinopaylib.entity.respons.LoginAppRepModel;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * User: LiuGq
 * Date: 2017/11/4
 * Time: 18:11
 */

public interface RequestService {

    //查询银行卡列表
//    @POST(Constans.BASEURL)
//    Flowable<QueryCardListResponseModel> getCashireList(@Body GetBankCardReqModel params);
//
//    //查询国家和币种
//    @POST(Constans.BASEURL)
//    Flowable<QueryCountryCodeRepModel> getCountryCurrencyList(@Body QueryCountryCodeReqModel params);
//
//    //判断卡类型
//    @POST(Constans.BASEURL)
//    Flowable<BankTypeModel> getBankType(@Body GetBankTypeReqModel params);
//
//    //获取Qr借记卡绑定
//    @POST(Constans.BASEURL)
//    Flowable<BankCardMobel> getBoundCard(@Body BoundCardModel params);
//
//    //获取Qr信用卡绑定
//    @POST(Constans.BASEURL)
//    Flowable<BoundCreditModel> getBoundCreditCard(@Body CreditCardModel params);
//
//    //获取国际卡验证要素
//    @POST(Constans.BASEURL)
//    Flowable<InterCardModel> getBoundInterCard(@Body GetInterCardModel params);
//
//    //绑定国际卡验证信息
//    @POST(Constans.BASEURL)
//    Flowable<BoundInterVerifyModel> getBoundInterCard(@Body BoundInterReqModel params);
//
//    //绑定国际卡
//    @POST(Constans.BASEURL)
//    Flowable<BoundInterCardResultModel> getBoundInterCardInfo(@Body BoundInterCardReqModel params);
//
//    //修改登录密码
//    @POST(Constans.BASEURL)
//    Flowable<ModifyLoginPwdRespone> getModifyLogin(@Body ModifyLoginPwdRequest params);
//
//    //获取全部交易数据
//    @POST(Constans.BASEURL)
//    Flowable<TradeRecordAllRespones> getTradeRecord(@Body TradeRecordAllRequest params);
//
//    //获取选中月份交易数据
//    @POST(Constans.BASEURL)
//    Flowable<TradeRecordMonthRespone> getMonthTradeRecord(@Body TradeRecordMonthRequest params);
//
//    //获取首页最新一条交易数据
//    @POST(Constans.BASEURL)
//    Flowable<QrCodePayInfoResponseModel> getNewTradeRecord(@Body TradeRecordNewRequest params);
//
//    //获取我的优惠券
//    @POST(Constans.BASEURL)
//    Flowable<MyTicketRespone> getTicketStatus(@Body MyTicketRequest params);
//
//    //删除银行卡
//    @POST(Constans.BASEURL)
//    Flowable<UnbindCardModel> getUnbindCardInfo(@Body UnbindCardReqModel params);
//
//    //删除国际银行卡
//    @POST(Constans.BASEURL)
//    Flowable<UnBindInterCardModel> getUnbindInterCardInfo(@Body UnBindInterCardReqModel params);
//
//    //U计划优惠
//    @POST(Constans.BASEURL)
//    Flowable<UPlanResponse> getUPlan(@Body UPlanRequest params);
//
//    //U计划优惠
//    @POST(Constans.BASEURL)
//    Flowable<ModifyPayPwdResponse> checkOldPayPwd(@Body ModifyPayPwdRequest params);
//
//    //设置支付密码。
//    @POST(Constans.BASEURL)
//    Flowable<SetPayPwdResponse> setPayPwd(@Body SetPayPwdRequest params);
//
//    //找回支付密码下一步验证。
//    @POST(Constans.BASEURL)
//    Flowable<FindPayPwdCodeResponse> getFindPayPwd(@Body FindPayPwdCodeRequest params);
//
//    //修改支付密码。
//    @POST(Constans.BASEURL)
//    Flowable<ModifySetPayPwdResponse> setModifyPayPwd(@Body ModifySetPayPwdRequest params);
//
//    //修改支付密码。
//    @POST(Constans.BASEURL)
//    Flowable<GetTicketRespons> getTicket(@Body GetTicketRequest params);
//
//    //绑定国内借记卡获取短信。
//    @POST(Constans.BASEURL)
//    Flowable<GetBoundCardMobileRespons> getMessage(@Body GetBoundCardMobileCodeModel params);
//
//    //绑定国内借记卡获取短信。
//    @POST(Constans.BASEURL)
//    Flowable<GetBoundCardMobileRespons> getMessage(@Body GetBoundCreditCardMobileCodeModel params);
//
//    //绑定国际卡获取短信。
//    @POST(Constans.BASEURL)
//    Flowable<GetInterCardCodeResponse> getMessage(@Body GetInterCardMobilCodeModel params);

    //登录页获取tempsession
    @POST(Constans.BASEURL)
    Flowable<GetTemsessionRepModel> getSessionMessage(@Body LoginGetSessionModel params);

    //正真的登录
    @POST(Constans.BASEURL)
    Flowable<LoginAppRepModel> getLoginAppMessage(@Body LoginAppModel params);

//    //查询个人信息
//    @POST(Constans.BASEURL)
//    Flowable<QueryPesonRequestModel> getQueryPesonMessage(@Body QueryPesonModel params);
//
//    //首页忘记密码发送短信
//    @POST(Constans.BASEURL)
//    Flowable<GetFindPwdTemRepModel> getForgetMessage(@Body GetForgotMessageReqModel params);
//
//    //APP更新
//    @POST(Constans.BASEURL)
//    Flowable<UpdateRespons> getCheckUpdata(@Body UpdateRequest params);
//
//    //退出登录
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getExitLogin(@Body ExitLoginModel params);
//
//    //修改登录密码
//    @POST(Constans.BASEURL)
//    Flowable<FindLoginPwdRepModel> modifyLoginPwd(@Body FindLoginPwdReqModel params);
//
//    //注册
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getRegister(@Body RegisterReqModel params);
//
//    //查询商户资料
//    @POST(Constans.BASEURL)
//    Flowable<QueryMerchantRep> getMerchantInfomation(@Body QueryMerchantReqModel params);
//
//    //查询商户热线
//    @POST(Constans.BASEURL)
//    Flowable<QueryHotLineRepModel> getQueryHotLine(@Body QueryHotLineReqModel params);
//
//    //查询注册国家区域
//    @POST(Constans.BASEURL)
//    Flowable<BankCountyCodeListModel> getBankCountryCode(@Body ChooseBankCountryCodeReqModel params);
//
//    //查询分行
//    @POST(Constans.BASEURL)
//    Flowable<BankBranchCodeListModel> getBankBranchCode(@Body ChooseBranchReqModel params);
//
//    //查询商户号
//    @POST(Constans.BASEURL)
//    Flowable<CashierRegisterQueryMerchantRepModel> getMerchantMessage(@Body CashierRegisterQueryMerchantReqModel params);
//
//    //获取收银员注册短信
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getMerchantCodeMessage(@Body GetForgotMessageReqModel params);
//
//    //收银员注册完成
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> cashierRegisterToLogin(@Body CasgierRegisetToLoginReqModel params);
//
//    //获取收银员信息
//    @POST(Constans.BASEURL)
//    Flowable<CashierInformationRepModel> getCashierInformation(@Body CashierInfomationReqModel params);
//
//    //收银员退出
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> exitApp(@Body CashierInfomationReqModel params);
//
//    //收银员卡信息查询
//    @POST(Constans.BASEURL)
//    Flowable<CashierCardInformationRepModel> getCardInformation(@Body CashierInfomationReqModel params);
//
//    //收银员奖励查询
//    @POST(Constans.BASEURL)
//    Flowable<RewardMoneyRepModel> getCashierReward(@Body RewardMoneyReqModel params);
//
//    //银行卡列表查询
//    @POST(Constans.BASEURL)
//    Flowable<CashierCardListRepModel> getCardList(@Body QueryCardListReqModel params);
//
//    //绑卡验证码
//    @POST(Constans.BASEURL)
//    Flowable<VerificationCodeRepModel> getBoundCardCode(@Body VerificationCodeReqModel params);
//
//    //绑卡
//    @POST(Constans.BASEURL)
//    Flowable<BoundCardRepModel> getBoundCardInformation(@Body BoundCareReqModel params);
//
//    //解绑卡
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getUnoundCard(@Body UnBoundCardReqModel params);
//
//    //获取推广二维码
//    @POST(Constans.BASEURL)
//    Flowable<PromoteQRCodeRepModel> getPromoteQrCode(@Body PromoteQRCodeReqModel params);

}
