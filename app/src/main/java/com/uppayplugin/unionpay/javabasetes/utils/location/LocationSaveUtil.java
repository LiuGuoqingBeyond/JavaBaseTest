package com.uppayplugin.unionpay.javabasetes.utils.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/10/31 0031.
 */

public class LocationSaveUtil {
    /**
     * 保存定位信息
     *
     * @param location
     */
    public static void saveLocationInfo(Context context, Location location, PreferencesUtil prefs) {
        try {
            Geocoder geocoder = new Geocoder(context);
            List<Address> locationList = null;
            try {
                locationList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
                Address address = locationList.get(0);//得到Address实例
                String province = address.getAdminArea();//省
                String city = address.getLocality();//市
                String district = address.getSubLocality();//区
                String thoroughfare = address.getThoroughfare();//路
                String subThoroughfare = address.getSubThoroughfare();//号
                Logger.e("详细地址:" + province + city + district + thoroughfare + subThoroughfare);
                Logger.i("info" + "address =" + address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }
}
