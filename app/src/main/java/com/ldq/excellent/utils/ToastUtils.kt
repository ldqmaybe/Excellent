package com.ldq.excellent.utils

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.ldq.excellent.MyApp

/**
 * author: LinDingQiang <br></br>
 * created on: 2017/6/29 10:19 <br></br>
 * description:Toast工具类
 */
object ToastUtils {
    /**
     * 获取当前application对象
     *
     * @return application对象
     */
    val context: Context = MyApp.context

    /**
     * 获取资源
     *
     * @return Resources
     */
    val resource: Resources
        get() = context.resources

    /**
     * 显示时间为short的toast
     *
     * @param msg 需要显示的内容
     */
    fun showShortToast(msg: String?) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 显示时间为short的toast
     *
     * @param resid 需要显示的内容
     */
    fun showShortToast(resid: Int) {
        showShortToast(context.resources.getString(resid))
    }

    /**
     * 显示时间为Long的toast
     *
     * @param msg 需要显示的内容
     */
    fun showLongToast(msg: String?) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * 显示时间为Long的toast
     *
     * @param resid 需要显示的内容
     */
    fun showLongToast(resid: Int) {
        showLongToast(context.resources.getString(resid))
    }
}