package com.ldq.excellent.scheduler

import com.ldq.excellent.utils.NetworkUtils
import io.reactivex.observers.DisposableObserver

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/27 14:55
 *
 *@email  dingqiang.l@verifone.cn
 */
abstract class ObservableCallback<T> : DisposableObserver<T>() {

    abstract fun onSuccess(t: T)

    override fun onStart() {
        super.onStart()

        if (!NetworkUtils.isConnected) {
            this.onError(Exception("无网络连接"))
            if (!isDisposed) {
                dispose()
            }
            return
        }
    }

    override fun onNext(response: T) {
        onSuccess(response)
    }

}