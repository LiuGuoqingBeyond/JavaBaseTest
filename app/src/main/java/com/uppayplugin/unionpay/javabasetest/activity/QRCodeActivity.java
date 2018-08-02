package com.uppayplugin.unionpay.javabasetest.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sinopaylib.api.APIProcxy;
import com.sinopaylib.entity.respons.QrCodeRepModel;
import com.sinopaylib.inter.RquestQRCodeInter;
import com.sinopaylib.listener.QRCodeListener;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.PreferencesUtil;
import com.uppayplugin.unionpay.javabasetest.utils.dialog.ToastUtils;
import com.uppayplugin.unionpay.javabasetest.utils.qrcode.QRCodeUtils;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

public class QRCodeActivity extends ToolBarActivity {
    @BindView(R.id.image_qr)
    ImageView imageQr;
    private PreferencesUtil prefs;
    private String mobile;
    private String countryCode;
    private String sessionID;
    private RquestQRCodeInter rquestQRCodeInter;
    private int displayWidth = 0;
    private WindowManager wm;
    private Bitmap bitmap;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initViewsAndEvents() {
        prefs = new PreferencesUtil(mContext);
        wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        displayWidth = wm.getDefaultDisplay().getWidth();
        mobile = prefs.readPrefs(Constant.PREFES_MOBILE);
        countryCode = prefs.readPrefs(prefs.readPrefs(Constant.PREFES_MOBILE)+Constant.PREFES_COUNTRYCODE);
        sessionID = prefs.readPrefs(Constant.PREFES_SESSIONID);
        //去请求二维码
        requestQrCode();
    }

    private void requestQrCode() {
        rquestQRCodeInter = APIProcxy.getInstance().getmQRCode();
        Map<String, String> map = new TreeMap<>();
        map.put("mobile", mobile);
        map.put("countryCode", countryCode);
        map.put("sessionID", sessionID);
        map.put("qrcType", "1");//0  opop二维码。1  银联国际二维码
        rquestQRCodeInter.getQRCode(mContext,map,qrCodeListener);
    }
    QRCodeListener qrCodeListener = new QRCodeListener() {
        @Override
        public void _onNext(QrCodeRepModel qrCodeRepModel) {
            if (qrCodeRepModel.isOk()){
                if (!TextUtils.isEmpty(qrCodeRepModel.getQrCode())){
                    showQrCodeImg(qrCodeRepModel.getQrCode());
                }
            }
        }

        @Override
        public void _onError(String error) {

        }
    };

    private void showQrCodeImg(String qrCode) {
        bitmap = QRCodeUtils.createQRCodeWithLogo(qrCode, (int) (displayWidth * 0.75),
                BitmapFactory.decodeResource(getResources(), R.drawable.logo_unionpay));
        imageQr.setImageBitmap(bitmap);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
