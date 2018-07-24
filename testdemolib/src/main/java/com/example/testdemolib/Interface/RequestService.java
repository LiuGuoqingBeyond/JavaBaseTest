package com.example.testdemolib.Interface;
import com.example.testdemolib.entity.respons.BankCardMobel;
import com.example.testdemolib.entity.respons.BankTypeModel;
import com.example.testdemolib.entity.request.BoundCardModel;
import com.example.testdemolib.entity.request.GetBankCardReqModel;
import com.example.testdemolib.entity.request.GetBankTypeReqModel;
import com.example.testdemolib.entity.request.GetBoundCardMobileCodeModel;
import com.example.testdemolib.entity.respons.GetBoundCardMobileRespons;
import com.example.testdemolib.entity.respons.GetTemsessionReqModel;
import com.example.testdemolib.entity.request.LoginAppModel;
import com.example.testdemolib.entity.respons.LoginAppReqModel;
import com.example.testdemolib.entity.request.LoginGetSessionModel;
import com.example.testdemolib.entity.respons.QueryCardListResponseModel;
import com.example.testdemolib.entity.respons.TradeRecordAllRespone;
import com.example.testdemolib.entity.respons.TradeRecordMonthRequest;

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
    @POST(Constans.BASEURL)
    Flowable<QueryCardListResponseModel> getCashireList(@Body GetBankCardReqModel params);

//    //查询国家和币种
//    @POST(Constans.BASEURL)
//    Flowable<QueryCountryCodeRepModel> getCountryCurrencyList(@Body QueryCountryCodeReqModel params);
//
    //判断卡类型
    @POST(Constans.BASEURL)
    Flowable<BankTypeModel> getBankType(@Body GetBankTypeReqModel params);
//
    //获取Qr借记卡绑定
    @POST(Constans.BASEURL)
    Flowable<BankCardMobel> getBoundCard(@Body BoundCardModel params);
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
    //获取全部交易数据
    @POST(Constans.BASEURL)
    Flowable<TradeRecordAllRespone> getTradeRecord(@Body TradeRecordMonthRequest params);

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
//    Flowable<BaseRepModel> getFindPayPwd(@Body FindPayPwdCodeRequest params);
//
//    //修改支付密码。
//    @POST(Constans.BASEURL)
//    Flowable<ModifySetPayPwdResponse> setModifyPayPwd(@Body ModifySetPayPwdRequest params);
//
//    //修改支付密码。
//    @POST(Constans.BASEURL)
//    Flowable<GetTicketRespons> getTicket(@Body GetTicketRequest params);
//
    //绑定国内借记卡获取短信。
    @POST(Constans.BASEURL)
    Flowable<GetBoundCardMobileRespons> getMessage(@Body GetBoundCardMobileCodeModel params);
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
    Flowable<GetTemsessionReqModel> getSessionMessage(@Body LoginGetSessionModel params);

    //正真的登录
    @POST(Constans.BASEURL)
    Flowable<LoginAppReqModel> getLoginAppMessage(@Body LoginAppModel params);

    //查询个人信息
//    @POST(Constans.BASEURL)
//    Flowable<QueryPesonRequestModel> getQueryPesonMessage(@Body QueryPesonModel params);

    //首页忘记密码发送短信
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getForgetMessage(@Body GetForgotMessageReqModel params);
//
//    //APP更新
//    @POST(Constans.BASEURL)
//    Flowable<UpdateRespons> getCheckUpdata(@Body UpdateRequest params);
//
//    //查询积分
//    @POST(Constans.BASEURL)
//    Flowable<IntegralsRepModel> getIntegral(@Body IntegralReqModel params);
//
//    //忘记密码04接口
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getVerificationCode(@Body VerificationCodeReqModel params);
//
//    //忘记密码05接口
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getresetPassword(@Body VerificationResetReqModel params);
//
//    //注册获取短信验证码
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getRegistrCode(@Body RegisterGetCodeModel params);
//
//    //注册下一步
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> getNextRegistrCode(@Body NextRegisterReqModel params);
//
//    //快速注册45接口
//    @POST(Constans.BASEURL)
//    Flowable<FastRegisterRepModel> getFastRegistrOne(@Body FastRegisterReqModel params);
//
//    //快速注册后登陆
//    @POST(Constans.BASEURL)
//    Flowable<FastRegisterLoginRepModel> getFastRegistrLogin(@Body FastRegisterLastReqModel params);
//
//    //银行卡认证
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> requestCardCertification(@Body GetCardCertificationReqModel params);
//
//    //申请中付卡
//    @POST(Constans.BASEURL)
//    Flowable<BaseRepModel> applyUpPayCard(@Body ApplyUpPayCardReqModel params);
//
//    //获取卡的交易数据
//    @POST(Constans.BASEURL)
//    Flowable<TradeRecordAllRespone> getCardTradeRecord(@Body CardTreadRecordReqModel params);
//
//    //获取推广有奖code
//    @POST(Constans.BASEURL)
//    Flowable<PromoteQRCodeRepModel> getPromoteQrCode(@Body PromoteQRCodeReqModel params);
}
