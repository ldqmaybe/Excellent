package com.ldq.excellent.mvp.presenter

import com.ldq.excellent.base.BasePresenter
import com.ldq.excellent.mvp.contract.HomeContract
import com.ldq.excellent.mvp.entity.GankEntity
import com.ldq.excellent.net.ObservableCallback
import com.ldq.excellent.net.RetrofitHttp
import com.ldq.excellent.scheduler.SchedulerUtils

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/27 14:14
 *
 *@email  dingqiang.l@verifone.cn
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    override fun getArticles(counts: Int, page: Int) {

        RetrofitHttp.service.getArticlesObservable(counts,page)
            .compose (SchedulerUtils.observableMapBaseResponse())
            .subscribeWith(object :ObservableCallback<List<GankEntity>>(null){

                override fun onSuccess(t: List<GankEntity>) {
                    mRootView?.apply { onSuccess(t) }
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    mRootView?.apply { onFailure(e) }
                }

            })?.let {
                addSubscription(
                    it
            )
            };

    }
}
