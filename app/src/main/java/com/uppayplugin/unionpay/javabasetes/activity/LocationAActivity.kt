package com.uppayplugin.unionpay.javabasetes.activity

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.whty.xzfpos.base.AppToolBarActivity
import kotlinx.android.synthetic.main.activity_location_a.*

class LocationAActivity : AppToolBarActivity(), View.OnClickListener {
    private val PRIVATE_CODE = 1315//开启GPS权限
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_A -> {
                //1、是否打开GPS
                initPermission()
            }
        }
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
        Handler().postDelayed(Runnable() {
            openActivity(LocationBActivity::class.java, true)
        }, 1500)
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

    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_location_a
    }

    override fun initViewsAndEvents() {
        btn_A.setOnClickListener(this)
    }

    override fun getLoadingTargetView(): View? {
        return null
    }
}
