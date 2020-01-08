package com.ldq.excellent.net

import android.content.Context
import com.ldq.excellent.exception.ApiErrorHelper
import com.ldq.excellent.exception.MyException
import com.ldq.excellent.utils.LoadingUtils
import com.ldq.excellent.utils.NetworkUtils
import io.reactivex.observers.DisposableObserver

/**
 * @author Admin
 * @time 2016/7/28 0028.17:18
 */
abstract class ObservableCallback<T> : DisposableObserver<T> {
    private var context: Context? = null

    constructor(context: Context?) {
        this.context = context
    }

    constructor() {}

    abstract fun onSuccess(t: T)
    override fun onStart() {
        super.onStart()
        //网络判断
        if (!NetworkUtils.isConnected) {
            onError(MyException("无网络连接"))
            if (!isDisposed) {
                dispose()
            }
            return
        }
        if (context != null) { //开启加载动画
            LoadingUtils.show(context)
        }
    }

    override fun onComplete() {
        LoadingUtils.dismiss()
    }

    override fun onNext(response: T) {
        onSuccess(response)
    }

    override fun onError(e: Throwable) {
        LoadingUtils.dismiss()
        ApiErrorHelper.handleCommonError(e)
    }
}