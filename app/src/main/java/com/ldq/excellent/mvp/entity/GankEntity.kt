package com.ldq.excellent.mvp.entity

/**
 * 干货集中营Bean：Article、福利
 */
class GankEntity {
    /**
     * _id : 593f2091421aa92c769a8c6a
     * createdAt : 2017-06-13T07:15:29.423Z
     * desc : Android之自定义View：侧滑删除
     * publishedAt : 2017-06-15T13:55:57.947Z
     * source : web
     * type : Android
     * url : https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247484934&idx=1&sn=f2a40261efe8ebee45804e9df93c1cce&chksm=96cda74ba1ba2e5dbbac15a9e57b5329176d1fe43478e5c63f7bc502a6ca50e4dfa6c0a9041e#rd
     * used : true
     * who : 陈宇明
     * images : ["http://img.gank.io/775b8ae5-4c21-4553-a77e-a0842248e1af"]
     */
    var _id: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    var isUsed = false
    var who: String? = null
    var images: List<String>? = null

}