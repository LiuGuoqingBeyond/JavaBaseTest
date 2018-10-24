package com.uppayplugin.unionpay.javabasetes.utils.location;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * @project：unionpayscanAPPforAndroid
 * @author：- octopus on 2017/11/20 20:01
 * @email：zhanghuan@xinguodu.com
 */
public class CountryCodeUtils {

    /**
     * Get ISO 3166-1 alpha-2 country code for this device (or null if not available)
     * @param context Context reference to get the TelephonyManager instance from
     * @return country code or null
     */
    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toUpperCase(Locale.US);
            }
            else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toUpperCase(Locale.US);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }

}
