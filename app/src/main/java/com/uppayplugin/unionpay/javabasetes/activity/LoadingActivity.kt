package com.uppayplugin.unionpay.javabasetes.activity

import android.os.Bundle
import android.view.View
import com.axl.android.frameworkbase.net.utils.ProgressSubscriber
import com.sinopaylib.entity.request.TradeRecordReqModel
import com.sinopaylib.entity.respons.TradeRecordListRepModel
import com.sinopaylib.impl.MerchantTradeRequestServiceImpl
import com.sinopaylib.mapbean.TransMapToBeanUtils
import com.sinopaylib.utils.PayUtils
import com.sinopaylib.utils.RSACoder
import com.uppayplugin.unionpay.javabasetes.R
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_loading.*
import java.util.*
import java.util.concurrent.TimeUnit

class LoadingActivity : AppToolBarActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0!!.id){
            /*R.id.btn1->{
                loadingView.showLoading()
                loadingView.setText("正在加载")
            }
            R.id.btn2->{
                loadingView.showSuccess()
                loadingView.setText("加载成功")
            }
            R.id.btn3->{
                loadingView.showFail()
                loadingView.setText("加载失败")
            }
            R.id.btn4->{
                loadingView.showUnusual()
                loadingView.setText("加载异常")
            }*/
        }
    }

    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_loading
    }

    override fun initViewsAndEvents() {
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        var map = TreeMap<String,String>()
        map["txnType"] = "09"
        val str = PayUtils.joinMapValue(map, '&')
        map["signature"] = RSACoder.sign(str.toByteArray()).replace("\n\r".toRegex(), "")
        MerchantTradeRequestServiceImpl.requestTradeRecordByMonth(TransMapToBeanUtils.mapToBean(map, TradeRecordReqModel::class.java) as TradeRecordReqModel)
                .delay(5,TimeUnit.SECONDS)
                .subscribe(object : ProgressSubscriber<TradeRecordListRepModel>(mContext) {
                    override fun _onNext(tradeRecordListRepModel: TradeRecordListRepModel) {
                    }

                    override fun _onError(message: String) {
                    }
                })
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
