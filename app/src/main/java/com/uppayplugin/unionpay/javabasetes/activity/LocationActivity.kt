package com.uppayplugin.unionpay.javabasetes.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.*
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils
import com.uppayplugin.unionpay.javabasetes.utils.location.CountryCodeUtils
import com.uppayplugin.unionpay.javabasetes.utils.location.LocationUtil
import com.whty.xzfpos.base.AppToolBarActivity
import java.io.IOException




class LocationActivity : AppToolBarActivity(), LocationUtil.onLocationBackListener {
    override fun location(latitude: Double?, longitude: Double?) {
        ToastUtils.showLong("$latitude+$longitude")
        try {
            val geocoder = Geocoder(context)
            var locationList: List<Address>? = null

            try {
                locationList = geocoder.getFromLocation(latitude!!, longitude!!, 1000)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            ToastUtils.showLong(locationList!![0].getThoroughfare() + locationList[0].getSubThoroughfare())
            val countryCode = CountryCodeUtils.getUserCountry(context)
            val address = locationList[0]//得到Address实例
            Logger.i("infoaddress =$address")
            // String countryName = address.getCountryName();//得到国家名称，比如：中国
            // 修改定位问题
            /*if (!TextUtils.isEmpty(countryCode)) {
                prefs.writePrefs(Constant.PREFES_COUNTRY_CODE, "SG");//写死SG
            } else {
                if (TextUtils.isEmpty(prefs.readPrefs(Constant.PREFES_COUNTRY_CODE))) {
                    prefs.writePrefs(Constant.PREFES_COUNTRY_CODE, "SG");
                }
            }
            // prefs.writePrefs(Constant.PREFES_COUNTRY_CODE, address.getLocale().getCountry());
            prefs.writePrefs(Constant.PREFES_LATITUDE, address.getLatitude() + "");
            prefs.writePrefs(Constant.PREFES_LONGITUDE, address.getLongitude() + "");*/

            Logger.e("PREFES_LATITUDE:" + address.getLatitude() + "--longitude:" + address.getLongitude())
        } catch (e: Exception) {
            Logger.e(e.message)
        }

    }

    override fun onError(errorMsg: String?) {
    }

    private var rxPermissions: RxPermissions? = null
    private var locationManager: LocationManager?= null
    private var location: Location? = null
    private var provider: String? = ""
    var context: Context? = null
    private var prefe: PreferencesUtil? = null
    private val BAIDU_READ_PHONE_STATE = 100//定位权限请求
    private val PRIVATE_CODE = 1315//开启GPS权限
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_location
    }

    override fun initViewsAndEvents() {
        context = this@LocationActivity
        prefe = PreferencesUtil(context)
        showGPSContacts()
        /*rxPermissions = RxPermissions(this)
        rxPermissions!!.requestEach(Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ permission ->
                    when (permission.name) {
                        Manifest.permission.CAMERA -> {
                        }
                        Manifest.permission.ACCESS_COARSE_LOCATION -> {
                            val mRunnable = Runnable {
                                run {
                                    getLocations()
//                                    getLocat()
                                }
                            }
                            Thread(mRunnable).start()
                        }
                    }
                }, { error -> })*/
    }

    private fun showGPSContacts() {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val ok = locationManager.isProviderEnabled(GPS_PROVIDER)
        val okk = locationManager.isProviderEnabled(NETWORK_PROVIDER)
        if (ok||okk) {//开了定位服务
            if (SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !== PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, LOCATIONGPS,
                            BAIDU_READ_PHONE_STATE)
                } else {
                    getLocation()//getLocation为定位方法
                }
            } else {
                getLocation()//getLocation为定位方法
            }
        } else {
            Toast.makeText(this, "系统检测到未开启GPS定位服务,请开启", Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
            startActivityForResult(intent, PRIVATE_CODE)
        }
    }

    private fun getLocat() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //上传经纬度，
            LocationUtil.getInstance().setOnLocationBackListener(this@LocationActivity)
            LocationUtil.getInstance().startLocation()
            Logger.i("Location:开启定位")
        } else {
            Logger.i("Location:关闭定位")
        }
    }
    private fun getLocation() {
// 获取位置管理服务
        val locationManager: LocationManager
        val serviceName = Context.LOCATION_SERVICE
        locationManager = this.getSystemService(serviceName) as LocationManager
        // 查找到服务信息
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE // 高精度
        criteria.isAltitudeRequired = false
        criteria.isBearingRequired = false
        criteria.isCostAllowed = true
        criteria.powerRequirement = Criteria.POWER_LOW // 低功耗
        val provider = locationManager.getBestProvider(criteria, true) // 获取GPS信息
        /**这段代码不需要深究，是locationManager.getLastKnownLocation(provider)自动生成的，不加会出错**/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            return
        }
        val location = locationManager.getLastKnownLocation(provider) // 通过GPS获取位置
        updateLocation(location)
    }

    private fun updateLocation(location: Location?) {
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            Logger.e("维度：$latitude\n经度$longitude")
        } else {
            Logger.e("无法获取到位置信息")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            BAIDU_READ_PHONE_STATE->{
                //如果用户取消，permissions可能为null.
                if (grantResults[0] == PERMISSION_GRANTED && grantResults.isNotEmpty()) { //有权限
                    // 获取到权限，作相应处理
                    getLocation()
                } else {
                    showGPSContacts()
                }
            }
        }
    }

    /**
     * 下面是当点击获取GPS定位，跳转到系统开关，ActivityResult回调，我这里做的是必须要开启GPS权限，
     * 没有开启会一直让用户开启权限，怎么决定，看具体需求
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PRIVATE_CODE->{
//                showContacts()
                showGPSContacts()
            }
        }
    }

    private fun getLocations() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager!!.getProvider(LocationManager.NETWORK_PROVIDER) != null || locationManager!!.getProvider(LocationManager.GPS_PROVIDER) != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            if (locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                //是否为GPS位置控制器
                provider = LocationManager.GPS_PROVIDER
            } else if (locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
                //是否为网络位置控制器
                provider = LocationManager.NETWORK_PROVIDER
            }
            Logger.e("provider=$provider")
            location = locationManager!!.getLastKnownLocation(provider)
            if (null != location) {
                val latitude = location!!.latitude//维度
                val longitude = location!!.longitude//经度
                val latLongInfo = "维度：" + latitude + "精度:" + longitude
                Logger.e("latLongInfo:=$latLongInfo")
//                try {
                    val geocoder = Geocoder(context)
                    var locationList: List<Address>? = null

                    try {
                        locationList = geocoder.getFromLocation(latitude, longitude, 1000)
                        Logger.e("hfdh"+locationList!![0].thoroughfare + locationList[0].subThoroughfare)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    // String countryName = address.getCountryName();//得到国家名称，比如：中国
                    // 修改定位问题
                    /*if (!TextUtils.isEmpty(countryCode)) {
                prefs.writePrefs(Constant.PREFES_COUNTRY_CODE, "SG");//写死SG
            } else {
                if (TextUtils.isEmpty(prefs.readPrefs(Constant.PREFES_COUNTRY_CODE))) {
                    prefs.writePrefs(Constant.PREFES_COUNTRY_CODE, "SG");
                }
            }
            // prefs.writePrefs(Constant.PREFES_COUNTRY_CODE, address.getLocale().getCountry());
            prefs.writePrefs(Constant.PREFES_LATITUDE, address.getLatitude() + "");
            prefs.writePrefs(Constant.PREFES_LONGITUDE, address.getLongitude() + "");*/

//                    Logger.e("PREFES_LATITUDE:" + address.latitude + "--longitude:" + address.longitude)
//                } catch (e: Exception) {
////                    Logger.e(e.message)
//                }

//                LocationUtil.saveLocationInfo(mContext, location, prefe)
            } else {
                // TODO: 2018/4/10 新增部分机型获取不到定位信息解决方案
                Logger.e("null--location")

                locationManager!!.requestLocationUpdates(provider, 2000, 1f, locationListener)
            }
        } else {
            //无法定位：1、提示用户打开定位服务；2、跳转到设置界面
            Toast.makeText(this, getString(R.string.login_open_location), Toast.LENGTH_SHORT).show()
            val i = Intent()
            i.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
            startActivity(i)
        }
    }

    private var locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            Logger.e("null--login_location")
            if (null != location) {
//                LocationUtil.saveLocationInfo(mContext, location, prefe)
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }

    override fun getLoadingTargetView(): View? {
        return null
    }

    val LOCATIONGPS = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE)
}
