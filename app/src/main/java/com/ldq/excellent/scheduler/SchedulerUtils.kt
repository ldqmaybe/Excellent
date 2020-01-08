package com.ldq.excellent.scheduler

import com.ldq.excellent.mvp.entity.BaseGankResponse
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/27 14:32
 *
 *@email  dingqiang.l@verifone.cn
 */
object SchedulerUtils {
    /**
     * 线程转换，不支持背压
     */
    fun <T> observable(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


    /**
     *
     * **map方式,该方法不支持背压******
     * 线程转换+ 在BaseHttpResult的情况下，将BaseHttpResult类型转换成T类型
     *
     * @param <T> data数据源
     * @return ObservableTransformer
    </T> */
    fun <T> observableMapBaseResponse(): ObservableTransformer<BaseGankResponse<T>, T>? {
        return object : ObservableTransformer<BaseGankResponse<T>, T> {
            override fun apply(upstream: Observable<BaseGankResponse<T>>): ObservableSource<T> {
                return upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { result ->
                        if (!result.error) { //服务器不正确返回
                            RuntimeException("error")
                        }
                        //服务器正确返回
                        result.results
                    }
            }
        }
    }


    /**
     * 线程转换，支持背压
     */
    fun <T> flowable(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}