package com.ldq.excellent

import android.app.Application
import kotlin.properties.Delegates

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/24 14:35
 *
 *@email  dingqiang.l@verifone.cn
 */
class MyApp : Application() {

    companion object{
        var context: Application by Delegates.notNull()
            private set
    }


    override fun onCreate() {
        super.onCreate()
        context = this
    }
}