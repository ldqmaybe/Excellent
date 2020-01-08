package com.ldq.excellent.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/24 15:03
 *
 *@email  dingqiang.l@verifone.cn
 */
open class BasePresenter<V : IBaseView> : IPresenter<V> {
    private var mCompositeDisposable = CompositeDisposable();
    var mRootView: V? = null
        private set

    override fun attachView(mRootView: V) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        if (this.mRootView != null) {
            mRootView == null
        }

        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.clear()
        }

    }

    fun addSubscription(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

}