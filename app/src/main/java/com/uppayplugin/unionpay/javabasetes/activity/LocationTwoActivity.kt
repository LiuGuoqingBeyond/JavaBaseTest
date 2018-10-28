package com.uppayplugin.unionpay.javabasetes.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.GpsStatus
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.orhanobut.logger.Logger
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.utils.locations.EasyPermissionsEx
import com.uppayplugin.unionpay.javabasetes.utils.locations.LocationHelper
import com.uppayplugin.unionpay.javabasetes.utils.locations.LocationUtils
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_location_two.*





class LocationTwoActivity : AppToolBarActivity(), View.OnClickListener {
    private val mNeedPermissionsList = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_location_two
    }

    override fun initViewsAndEvents() {
        btn_location.setOnClickListener(this)
    }

    override fun getLoadingTargetView(): View? {
        return null
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_location->{
                // 使用了 EasyPermissionsEx 类来管理动态权限配置
                if (EasyPermissionsEx.hasPermissions(mContext, mNeedPermissionsList.toString())) {
                    initLocation()
                } else {
                    EasyPermissionsEx.requestPermissions(mContext, "需要定位权限来获取当地天气信息", 1, mNeedPermissionsList.toString())
                }
            }
        }
    }

    private fun initLocation() {
        LocationUtils.getInstance(mContext).initLocation(object : LocationHelper {
            override fun UpdateLocation(location: Location) {
                Logger.e("MoLin", "location.getLatitude():" + location.getLatitude())
                /*mLatitudeView.setText(location.getLatitude() + "")
                mLongtitudeView.setText(location.getLongitude() + "")*/
            }

            override fun UpdateStatus(provider: String, status: Int, extras: Bundle) {}

            override fun UpdateGPSStatus(pGpsStatus: GpsStatus) {

            }

            override fun UpdateLastLocation(location: Location) {
                Logger.e("MoLin", "UpdateLastLocation_location.getLatitude():" + location.getLatitude())
                /*mLatitudeView.setText(location.getLatitude() + "")
                mLongtitudeView.setText(location.getLongitude() + "")*/
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            Toast.makeText(this, "settings", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Logger.e("MoLin", "已获取权限!")
                    initLocation()
                } else {
                    if (EasyPermissionsEx.somePermissionPermanentlyDenied(this, mNeedPermissionsList.toString())) {
                        EasyPermissionsEx.goSettings2Permissions(this, "需要定位权限来获取当地天气信息,但是该权限被禁止,你可以到设置中更改"
                                , "去设置", 1)
                    }
                }
            }
        }
    }
}
