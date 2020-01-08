package com.ldq.excellent.mvp.entity

/**
 *
 * @author LinDingQiang
 *
 * @time 2020/1/2 16:05
 *
 *@email  dingqiang.l@verifone.cn
 */
class BaseGankResponse<T> {
    //false为正确返回数据
    var error = false
    var results: T? = null
}