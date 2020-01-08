package com.ldq.excellent.ui.activity

import android.Manifest
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.ldq.excellent.R
import com.ldq.excellent.base.BaseActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity : BaseActivity() {
    val TAG : String = "SplashActivity"
    var alphaAnimation : AlphaAnimation? = null
    override fun layoutId() : Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation : Animation?) {
            }

            override fun onAnimationEnd(animation : Animation?) {
                Logger.i(TAG, "onAnimationEnd")
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationStart(animation : Animation?) {
            }

        })
        checkPermission()
    }

    fun checkPermission() {
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "优了个秀需要以下权限，请允许", 0, *perms)
    }

    override fun onPermissionsGranted(requestCode : Int, perms : List<String>) {
        Logger.i(TAG, "onAnimationEnd")
        if (requestCode == 0) {
            Logger.i(TAG, "requestCode = ${requestCode}")
            if (perms.isNotEmpty()) {
                Logger.i(TAG, "perms.isNotEmpty() = ${perms.isNotEmpty()}")
                if (perms.contains(Manifest.permission.READ_PHONE_STATE) && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Logger.i(TAG, "onAnimationEnd ${alphaAnimation != null}")
                    if (alphaAnimation != null) {
                        Logger.i(TAG, "onAnimationEnd")
                        logo.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }

}
