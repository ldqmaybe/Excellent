package com.ldq.excellent.utils

import android.app.Activity
import android.os.Process
import java.io.Serializable
import java.util.*

/**
 *
 * @author LinDingQiang
 *
 * @time 2020/1/8 10:04
 *
 *@email  dingqiang.l@verifone.cn
 */
class AppManager private constructor() : Serializable {
    private var sActivityStack: Stack<Activity>? = null
    private val WAITTIME: Long = 2000
    private var touchTime: Long = 0

    //静态内部类
    private object SingletonHolder {
        val INSTANCES: AppManager = AppManager()

    }

    companion object {
        //全局访问
        fun getInstance(): AppManager {
            return SingletonHolder.INSTANCES
        }
    }

    //防止单例对象在反序列化时重新生成对象
    private fun readResolve(): Any {
        return SingletonHolder.INSTANCES
    }

    /**
     * 将activity添加到任务栈中
     *
     * @param activity
     */
    fun addActivityToStack(activity: Activity?) {
        if (sActivityStack == null) {
            sActivityStack = Stack()
        }
        sActivityStack?.add(activity)
    }

    fun finishActivity(clas: Class<*>) {
        val size: Int = sActivityStack?.size ?: 0
        for (i in 0 until size) {
            if (null != sActivityStack?.get(i) && sActivityStack!!.get(i).javaClass == clas) {
                sActivityStack!!.get(i).finish()
                return
            }
        }
    }

    /**
     * 关闭除该activity的其他activity
     *
     * @param activity
     */
    fun finishOthersActivity(activity: Activity) {
        val size: Int = sActivityStack?.size ?: 0
        for (i in 0 until size) {
            if (null != sActivityStack?.get(i) && sActivityStack!!.get(i) !== activity) {
                sActivityStack!!.get(i).finish()
            }
        }
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    fun finishActivity(activity: Activity) {
        sActivityStack?.remove(activity)
        activity.finish()
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        val size: Int = sActivityStack?.size ?: 0
        for (i in 0 until size) {
            if (null != sActivityStack?.get(i)) {
                sActivityStack!!.get(i).finish()
            }
        }
        sActivityStack?.clear()
        System.gc()
    }

    fun onKeyDown() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - touchTime >= WAITTIME) {
            ToastUtils.showShortToast("再按一次退出")
            touchTime = currentTime
        } else {
            getInstance().finishAllActivity()
            Process.killProcess(Process.myPid())
        }
    }

}