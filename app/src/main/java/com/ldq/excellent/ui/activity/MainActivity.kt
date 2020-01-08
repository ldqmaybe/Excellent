package com.ldq.excellent.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.KeyEvent
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.ldq.excellent.R
import com.ldq.excellent.base.BaseActivity
import com.ldq.excellent.mvp.TabEntity
import com.ldq.excellent.ui.fragment.HomeFragment
import com.ldq.excellent.ui.fragment.MineFragment
import com.ldq.excellent.utils.AppManager
import com.ldq.excellent.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    val TAG = "MainActivity"
    //底部菜单名称
    private val mTitles = arrayOf("首页", "我的")
    //未被选中的图标
    private val mIconUnSelectIds = arrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_mine_normal)
    //被选中的图标
    private val mIconSelectIds = arrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_mine_selected)
    //底部菜单entity
    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var homeFragment : HomeFragment? = null
    private var mineFragment : MineFragment? = null
    //默认选中0
    private var mIndex = 0

    override fun layoutId() : Int = R.layout.activity_main

    override fun initView() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.white))
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size).mapTo(mTabEntities) {
            TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it])
        }
        for (tabEntity in mTabEntities) {
            Log
                    .i(TAG, "底部标题=${tabEntity.tabTitle},选中的icon=${tabEntity.tabSelectedIcon},未选中的icon=${tabEntity.tabUnselectedIcon}")
        }
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position : Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position : Int) {
            }

        })
    }

    private fun switchFragment(position : Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)

        when (position) {
            0 -> homeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                homeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1 -> mineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance(mTitles[position]).let {
                mineFragment = it
                transaction.add(R.id.fl_container, it, "mine")
            }
        }
        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction : FragmentTransaction) {
        homeFragment?.let { transaction.hide(it) }
        mineFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState : Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun onKeyDown(keyCode : Int, event : KeyEvent?) : Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            AppManager.getInstance().onKeyDown()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
