package com.ldq.excellent.net

import com.ldq.excellent.mvp.entity.BaseGankResponse
import com.ldq.excellent.mvp.entity.GankEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @author LinDingQiang
 *
 * @time 2019/12/24 16:55
 *
 *@email  dingqiang.l@verifone.cn
 */
interface ApiService {
    /**
     * 获取文章 不支持背压
     * @param counts 每页的条数
     * @param page 第几页
     * @return 文章
     */
    @GET("data/all/{counts}/{page}")
    fun getArticlesObservable(@Path("counts") counts: Int, @Path("page") page: Int): Observable<BaseGankResponse<List<GankEntity>>>

    /**
     * 获取文章 不支持背压
     * @param counts 每页的条数
     * @param page 第几页
     * @return 文章
     */
    @GET("data/福利/{counts}/{page}")
    fun getWelfareObservable(@Path("counts") counts: Int, @Path("page") page: Int): Observable<BaseGankResponse<List<GankEntity>>>
}