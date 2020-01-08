package com.ldq.excellent.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/24 17:38
 *
 *@email  dingqiang.l@verifone.cn
 */
abstract class BaseFragment : Fragment() {
    private val TAG = "BaseFragment"
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.i(TAG,"setUserVisibleHint init ")
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG,"onActivityCreated init ")
        isViewPrepare = true
        initView()
        lazyLoadDataIfPrepared()
    }
    private fun lazyLoadDataIfPrepared() {
        isVisible
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }
    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 初始化 ViewI
     */
    abstract fun initView()

    /**
     * 懒加载
     */
    open fun lazyLoad(){

    }
}