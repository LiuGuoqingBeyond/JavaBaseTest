package com.uppayplugin.unionpay.javabasetes.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Looper
import android.util.DisplayMetrics
import android.view.View
import com.orhanobut.logger.Logger
import com.uppayplugin.unionpay.javabasetes.Interface.AsynViewClickOnceListener
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils
import com.uppayplugin.unionpay.javabasetes.utils.sign.DrawView
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_sign_name.*
import java.nio.ByteBuffer


class SignNameActivity : AppToolBarActivity() {
    private var drawView: DrawView? = null
    // 上方显示的高度
    private var sign_name_top_height: Int = 0
    // 下方按钮的高度
    private var sign_name_again_height: Int = 0
    var statusHeight: Int = 0
    private val line_height: Int = 0
    var random = ""
    var date = ""
    var ref = ""
    // 图片内容
    private var sign_picture = ""
    private var asyn: AsynViewClickOnceListener? = null
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_sign_name
    }

    override fun initViewsAndEvents() {
        sign_name_top_height = sign_name_card_no.measuredHeight
        sign_name_again_height = sign_name_again.measuredHeight
        statusHeight = getStatusBarHeight()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        Logger.e("SignFragment", "onViewCreated")
        val dm = DisplayMetrics()
        // 获取屏幕信息
        this.windowManager.defaultDisplay.getMetrics(dm)

        val screenWidth = dm.widthPixels
        val screenHeigh = dm.heightPixels

        var displayWidth = 0
        var displayHeight = 0

        if (screenWidth > screenHeigh) {
            displayWidth = screenWidth
            displayHeight = screenHeigh
        } else {
            displayWidth = screenHeigh
            displayHeight = screenWidth
        }

        // 165是页面头部高度，3是两条灰色的线的高度
        drawView = DrawView(this, displayWidth, displayHeight
                - sign_name_again_height - sign_name_top_height - statusHeight
                - line_height - line_height, random, displayHeight)
        // drawView.setBackgroundColor(Color.WHITE);
        sign_name.removeAllViews()
        sign_name.addView(drawView)


        sign_name_again.setOnClickListener { v ->
            Logger.e("width_sign_name_again")
//            sign_picture = ""
            DrawView.painted = false
            val dm = DisplayMetrics()
            // 获取屏幕信息
            this.windowManager.defaultDisplay
                    .getMetrics(dm)
            this.windowManager.defaultDisplay
                    .getMetrics(dm)

            val screenWidth = dm.widthPixels

            val screenHeigh = dm.heightPixels

            var displayWidth = 0
            var displayHeight = 0

            if (screenWidth > screenHeigh) {
                displayWidth = screenWidth
                displayHeight = screenHeigh
            } else {
                displayWidth = screenHeigh
                displayHeight = screenWidth
            }

            drawView = DrawView(this, displayWidth, displayHeight
                    - sign_name_again_height - sign_name_top_height
                    - statusHeight - line_height - line_height, random,
                    displayHeight)
            sign_name.removeAllViews()
            sign_name.addView(drawView)

        }
        asyn = AsynViewClickOnceListener {
            if (Looper.myLooper() == null) {
                Looper.prepare()
            }
            try {
                if (!DrawView.painted) {
                    Logger.e("adad"+"dadfsggdgdgtg")
//                        ToastUtils.showLong(getString(R.string.pls_sign_name))
//                    asyn!!.reset()
                    return@AsynViewClickOnceListener
                }
                if (drawView != null && !drawView!!.isValidPaint) {
                    Logger.e("adad"+"dadfsggdgdgtjdoafojfojfojfog")
//                        ToastUtils.showLong(getString(R.string.not_valid_sign_name))
//                    asyn!!.reset()
                    return@AsynViewClickOnceListener
                }

                if (drawView == null) {
                    this.finish()
                    return@AsynViewClickOnceListener
                }
                Logger.e("adad"+"成功签名了")
                var bitmap = drawView!!.bitmap

                val bytes = bitmap.byteCount

                val buf = ByteBuffer.allocate(bytes)
                bitmap.copyPixelsToBuffer(buf)

                val byteArray = buf.array()

//                bitmap = BmpConvert.scaleImage(bitmap, 248, 80)//压缩图片
                val width = bitmap.width
                val height = bitmap.height
                Logger.e("签名宽、高", "width:" + width + "height:" + height)
                val pixels = IntArray(bitmap.width * bitmap.height)
                bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)// 取得BITMAP的所有像素点

//                val data = JBig.enc(pixels, bitmap.width, bitmap.height)
//                if (data != null) {
//                    sign_picture = FunctionUtils.bytesToHexString(data)
//                }
//
//                DialogUtils.showLoadingDialog(getActivity(), R.string.trade_task_upload_ticket)
//                var request: Map<String, Any>? = (getActivity() as TradeActivity).traceMap.get("requestMSG")
//                var response1: Map<String, String>? = (getActivity() as TradeActivity).traceMap.get("responseMSG")
//                val ticketInfo = (getActivity() as TradeActivity).traceMap.get("signTicketInfo") as String
//                if (request == null || response1 == null) {
//                    (getActivity() as TradeActivity).traceMap = SerializeUtils.unSerialize(TradeMessageCache.getTradeInfo(getActivity()))
//                    request = (getActivity() as TradeActivity).traceMap.get("requestMSG")
//                    response1 = (getActivity() as TradeActivity).traceMap.get("responseMSG")
//                }
//
//                traderesponse = (getActivity() as TradeActivity).traceMap.get("responseMSG") as Map<String, String>
//                SharedInfo.TICKET_URL = tradeService.doElecSignature(request, response1, ticketInfo, sign_picture)
                /*if (SharedInfo.TICKET_URL != null && SharedInfo.TICKET_URL.length > 0) {
                    // 为null的情况在方法内已处理
                    ToastUtils.showLong(getString(R.string.sign_name_send_success))
                } else {
                    // ClickBtn.clickAble(true, sign_name_send);
                    asyn!!.reset()
                    return@AsynViewClickOnceListener
                }*/
                try {
//                    Thread.sleep(1700)
                } catch (e: InterruptedException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }

//                signCtl.turnToTradeResultPage(SharedInfo.TICKET_URL, "", this@SignNameActivity, cardNO)
//                asyn!!.reset()
                /*val intent = Intent()
                intent.putExtra("branchName",byteArray)
                setResult(Activity.RESULT_OK, intent)
                finish()*/
//                openActivity(MainActivity::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                ToastUtils.showLong(getString(R.string.sign_name_send_fail))
                asyn!!.reset()
                return@AsynViewClickOnceListener
            }

            Looper.loop()

        }
        sign_name_send.setOnClickListener(asyn)
    }

    override fun getLoadingTargetView(): View? {
        return null
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height",
                "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
