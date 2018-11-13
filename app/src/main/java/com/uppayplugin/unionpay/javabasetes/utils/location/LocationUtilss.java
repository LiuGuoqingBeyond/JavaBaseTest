package com.uppayplugin.unionpay.javabasetes.utils.location;

import android.text.TextUtils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.MApplication;

/**
 * User: chengxingjiang
 * Date: 2017/11/29
 * Time: 上午10:47
 */

public class LocationUtilss {

    private String latitude;
    private String longitude;


    private LocationClient mLocationClient = null;
    private LocationClientOption mLocationOption = null;

    private onLocationBackListener mOnLocationBackListener;

    public void setOnLocationBackListener(onLocationBackListener onLocationBackListener) {
        mOnLocationBackListener = onLocationBackListener;
    }

    public static LocationUtilss getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final LocationUtilss INSTANCE = new LocationUtilss();
    }

    private LocationUtilss() {
        init();
    }


    private void init() {

        //初始化定位.

        mLocationClient = new LocationClient(MApplication.getAppContext());

        mLocationOption = new LocationClientOption();
        mLocationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置定位回调监听.
        mLocationOption.setOpenGps(true);
        mLocationOption.setLocationNotify(true);
        mLocationOption.setScanSpan(1000);
        mLocationOption.setIsNeedAddress(true);
        mLocationOption.setIgnoreKillProcess(false);
        mLocationOption.SetIgnoreCacheException(false);
        mLocationOption.setWifiCacheTimeOut(5*60*1000);

        mLocationClient.registerLocationListener(mAMapLocationListener);
        mLocationClient.setLocOption(mLocationOption);
        

    }

    /**
     * 开始定位
     */
    public void startLocation() {
        mLocationClient.start();
    }

    /**
     * onDestry中调用，或者主动调用。
     */
    public void stopLocation() {
        mLocationClient.stop();
    }


    public String getLongitude() {

        if (!TextUtils.isEmpty(longitude)) {
            String string = longitude;
            Logger.e("格式经纬度:" + string);//格式经纬度 116.3716820,39.9629340
            return string;
        } else {
            //返回个当前默认值。待商榷。
            return "";
        }
    }

    public String getLatitude() {

        if (!TextUtils.isEmpty(latitude)) {
            String string = latitude;
            Logger.e("格式经纬度:" + string);//格式经纬度 116.3716820,39.9629340
            return string;
        } else {
            //返回个当前默认值。待商榷。
            return "";
        }

    }


    /*private BDAbstractLocationListener mAMapLocationListener = amapLocation -> {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                if (mOnLocationBackListener != null) {
                    mOnLocationBackListener.location(String.valueOf(amapLocation.getLatitude()), String.valueOf(amapLocation.getLongitude()),amapLocation.getCountry());
                }
                latitude = String.valueOf(amapLocation.getLatitude());
                longitude = String.valueOf(amapLocation.getLongitude());
                Logger.d("格式经纬度回调成功:" + latitude + "---" + longitude);//格式经纬度 116.3716820,39.9629340

            } else {
                if (mOnLocationBackListener != null) {
                    mOnLocationBackListener.onError(amapLocation.getErrorInfo());
                }
                Logger.e("格式经纬度" + amapLocation.getErrorCode() + "-----" + amapLocation.getErrorInfo());
            }
        }
    };*/
    private BDAbstractLocationListener mAMapLocationListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location!=null){
                if (location.getLocType() == 61){
                    if (mOnLocationBackListener!=null){
                        mOnLocationBackListener.location(location.getCountryCode(),location.getAddrStr(),location.getCountry());
                    }else {
                        if (mOnLocationBackListener!=null){
                            mOnLocationBackListener.onError(location.getLocType()+"");
                        }
                    }
                }
            }
        }
    };


    public interface onLocationBackListener {
        void location(String latitude, String longitude, String detailedAddress);

        void onError(String errorMsg);
    }
}
