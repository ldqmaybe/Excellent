package com.ldq.excellent.mvp

import com.flyco.tablayout.listener.CustomTabEntity

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/26 18:15
 *
 *@email  dingqiang.l@verifone.cn
 */
class TabEntity(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}