package com.uppayplugin.unionpay.javabasetes.utils.location;

import android.text.TextUtils;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.MApplication;

/**
 * User: chengxingjiang
 * Date: 2017/11/29
 * Time: 上午10:47
 */

public class LocationUtil {

    private Double latitude;
    private Double longitude;


    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;

    private onLocationBackListener mOnLocationBackListener;

    public void setOnLocationBackListener(onLocationBackListener onLocationBackListener) {
        mOnLocationBackListener = onLocationBackListener;
    }

    public static LocationUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final LocationUtil INSTANCE = new LocationUtil();
    }

    private LocationUtil() {
        init();
    }


    private void init() {

        //初始化定位.

        mLocationClient = new AMapLocationClient(MApplication.getAppContext());

        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位回调监听.
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setInterval(1000);
        mLocationOption.setNeedAddress(false);
        mLocationOption.setMockEnable(true);
        mLocationOption.setHttpTimeOut(20000);
        mLocationOption.setLocationCacheEnable(true);
        mLocationClient.setLocationListener(mAMapLocationListener);
        mLocationClient.setLocationOption(mLocationOption);

    }

    /**
     * 开始定位
     */
    public void startLocation() {
        mLocationClient.startLocation();
    }

    /**
     * onDestry中调用，或者主动调用。
     */
    public void stopLocation() {
        mLocationClient.stopLocation();
    }


    public String getLongitude() {

        if (!TextUtils.isEmpty(String.valueOf(longitude))) {
            String string = String.valueOf(longitude);
            Logger.e("格式经纬度:" + string);//格式经纬度 116.3716820,39.9629340
            return string;
        } else {
            //返回个当前默认值。待商榷。
            return "";
        }
    }

    public String getLatitude() {

        if (!TextUtils.isEmpty(String.valueOf(latitude))) {
            String string = String.valueOf(latitude);
            Logger.e("格式经纬度:" + string);//格式经纬度 116.3716820,39.9629340
            return string;
        } else {
            //返回个当前默认值。待商榷。
            return "";
        }

    }


    private AMapLocationListener mAMapLocationListener = amapLocation -> {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                if (mOnLocationBackListener != null) {
                    mOnLocationBackListener.location(amapLocation.getLatitude(), amapLocation.getLongitude());
                }
                latitude = amapLocation.getLatitude();
                longitude = amapLocation.getLongitude();
                Logger.d("格式经纬度回调成功:" + latitude + "---" + longitude);//格式经纬度 116.3716820,39.9629340
                Logger.d("定位信息为："+amapLocation.getProvince()+"-"+amapLocation.getCity()+"-"+amapLocation.getDistrict()+"-"+amapLocation.getAddress());
            } else {
                if (mOnLocationBackListener != null) {
                    mOnLocationBackListener.onError(amapLocation.getErrorInfo());
                }
                Logger.e("格式经纬度" + amapLocation.getErrorCode() + "-----" + amapLocation.getErrorInfo());
            }
        }
    };


    public interface onLocationBackListener {
        void location(Double latitude, Double longitude);

        void onError(String errorMsg);
    }
}
