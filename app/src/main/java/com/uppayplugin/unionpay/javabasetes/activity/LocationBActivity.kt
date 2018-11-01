package com.uppayplugin.unionpay.javabasetes.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.view.View
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil
import com.uppayplugin.unionpay.javabasetes.utils.location.LocationSaveUtil
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_location_b.*

class LocationBActivity : AppToolBarActivity(), View.OnClickListener {
    private val PRIVATE_CODE = 1316//开启GPS权限
    private var rxPermissions: RxPermissions? = null
    protected var locationManager: LocationManager? = null
    private var location: Location? = null
    private var provider: String? = ""
    private var prefe: PreferencesUtil? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_B -> {
                openActivity(LocationCActivity::class.java)
            }
        }
    }

    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_location_b
    }

    override fun initViewsAndEvents() {
        prefe = PreferencesUtil(mContext)
        btn_B.setOnClickListener(this)
        initPermission()
    }

    private fun initPermission() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            turnLogin()
        } else {
            val mDialog = AlertDialog.Builder(this)
//            mDialog.setTitle(getString(R.string.text_check_update))
            mDialog.setMessage(getString(R.string.text_open_location))
            mDialog.setPositiveButton(R.string.text_set, { dialog, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                startActivityForResult(intent, PRIVATE_CODE)
                dialog.dismiss()
            })
            mDialog.setNegativeButton(R.string.text_cancel, { dialogInterface, _ ->
                turnLogin()
            })
            mDialog.setCancelable(false)
            mDialog.create().show()
        }
    }

    private fun turnLogin() {
        rxPermissions = RxPermissions(this)
        rxPermissions!!
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ permission ->
                    when (permission.name) {
                        Manifest.permission.CAMERA -> {
                        }
                        Manifest.permission.ACCESS_COARSE_LOCATION -> getLocations()
                    }
                }) { error -> }
    }

    /**
     * 下面是当点击获取GPS定位，跳转到系统开关，ActivityResult回调，我这里做的是必须要开启GPS权限，
     * 没有开启会一直让用户开启权限，怎么决定，看具体需求
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PRIVATE_CODE->{
                initPermission()
            }
        }
    }

    private fun getLocations() {
        val mRunnable = Runnable {
            run {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager!!.getProvider(LocationManager.NETWORK_PROVIDER) != null || locationManager!!.getProvider(LocationManager.GPS_PROVIDER) != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return@Runnable
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
                LocationSaveUtil.saveLocationInfo(mContext, location, prefe)
            }
        }
            }
        }
        Thread(mRunnable).start()
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
