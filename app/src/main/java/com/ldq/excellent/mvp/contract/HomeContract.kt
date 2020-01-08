package com.ldq.excellent.mvp.contract

import com.ldq.excellent.base.IBaseView
import com.ldq.excellent.mvp.entity.GankEntity

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/27 11:27
 *
 *@email  dingqiang.l@verifone.cn
 */
interface HomeContract {
    interface View : IBaseView {

        fun onSuccess(gankEntities: List<GankEntity>)
        fun onFailure(e: Throwable)
    }

    interface Presenter {

        fun getArticles(counts: Int, page: Int)
    }


}