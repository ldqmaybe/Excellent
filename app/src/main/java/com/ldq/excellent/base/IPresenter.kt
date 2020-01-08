package com.ldq.excellent.base

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/27 11:33
 *
 *@email  dingqiang.l@verifone.cn
 */
interface IPresenter <in V : IBaseView> {
    fun attachView(mRootView: V)

    fun detachView()
}