package com.ldq.excellent.mvp.contract

import com.ldq.excellent.base.IBaseView
import com.ldq.excellent.mvp.entity.GankEntity

/**
 *
 * @author LinDingQiang
 *
 * @time 2020/1/3 17:18
 *
 *@email  dingqiang.l@verifone.cn
 */
interface WelfareContact {
    interface View : IBaseView {
        fun onSuccess(welfares: List<GankEntity>)

        fun onFailure(e: Throwable?)
    }
    interface Presenter{
        /**
         * 获取福利
         *
         * @param counts 每页数量
         * @param page   第几页
         */
        fun getWelfares(counts: Int, page: Int)
    }
}