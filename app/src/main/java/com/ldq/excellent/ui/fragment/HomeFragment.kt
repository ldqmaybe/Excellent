package com.ldq.excellent.ui.fragment

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
import com.chad.library.adapter.base.BaseViewHolder
import com.ldq.excellent.R
import com.ldq.excellent.base.BaseFragment
import com.ldq.excellent.mvp.contract.HomeContract
import com.ldq.excellent.mvp.entity.GankEntity
import com.ldq.excellent.mvp.presenter.HomePresenter
import com.ldq.excellent.utils.StringUtils
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeContract.View, RequestLoadMoreListener {

    val TAG = "MainActivity"
    private var mTitle: String? = null
    private var adapter: BaseQuickAdapter<GankEntity, BaseViewHolder>? = null
    /**
     * 一次加载多少
     */
    private var counts = 10
    /**
     * 加载第几页
     */
    private var page = 1
    private var PAGE_SIZE = 1


    private val presenter by lazy { HomePresenter() }

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        Log.i(TAG, "HomeFragment show ")
        presenter.attachView(this)
        recycle?.layoutManager = LinearLayoutManager(activity)
        mPtrFrame?.post { mPtrFrame.autoRefresh(true) }
        mPtrFrame?.setLastUpdateTimeRelateObject(this)
        mPtrFrame?.setPtrHandler(object : PtrDefaultHandler() {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                Handler().post {
                    page = 1
                    presenter.getArticles(counts, page)
                }
            }
        })

        initAdapter()
    }

    private fun initItemClick() {
        adapter!!.setOnItemClickListener { adapter: BaseQuickAdapter<*, *>, view: View?, position: Int ->
            //            val article: GankEntity = adapter.data[position] as GankEntity
//            val intent = Intent(activity, ArticleWebActivity::class.java)
//            intent.putExtra("url", article.url)
//            intent.putExtra("title", article.desc)
//            startActivity(intent)
        }
    }

    private fun initAdapter() {
        adapter = object : BaseQuickAdapter<GankEntity, BaseViewHolder>(R.layout.article_item) {
            override fun convert(helper: BaseViewHolder, article: GankEntity) {
                helper.setText(R.id.article_title, article.desc)
                    .setText(R.id.article_who, article.who)
                    .setText(R.id.article_type, article.type)
                helper.setText(
                    R.id.article_time,
                    StringUtils.getFormattedDate(
                        article.publishedAt?.split("T")?.get(0),
                        "yyyy-MM-dd",
                        "yyyy年MM月dd日"
                    )
                )
                val imageView =
                    helper.getView<ImageView>(R.id.imageView)
                imageView.visibility = View.GONE
                if (article.images != null && article.images?.size!! > 0) {
                    imageView.visibility = View.VISIBLE

                    val option = RequestOptions().placeholder(R.mipmap.placeholder)
                        .fallback(R.mipmap.placeholder).error(R.mipmap.placeholder)
                    Glide.with(this@HomeFragment)
                        .load(article.images?.get(0))
                        .apply(option)
                        .into(imageView)
                }
            }
        }
        recycle?.adapter = adapter
        (adapter as BaseQuickAdapter<GankEntity, BaseViewHolder>).setOnLoadMoreListener(
            this,
            recycle
        )
    }

    override fun lazyLoad() {
//        presenter.getArticles(10, 1)
    }


    override fun onSuccess(gankEntities: List<GankEntity>) {
        if (gankEntities.size == 0) {
            adapter?.setEmptyView(R.layout.empty)
            mPtrFrame!!.refreshComplete()
            return
        }
        if (page == 1) {
            adapter!!.setNewData(gankEntities)
            adapter!!.disableLoadMoreIfNotFullPage()
            mPtrFrame!!.refreshComplete()
        } else {
            adapter!!.addData(gankEntities)
            adapter!!.loadMoreComplete()
        }
        page++
    }

    override fun onFailure(e: Throwable) {
        mPtrFrame!!.refreshComplete()
        adapter!!.loadMoreFail()
    }

    override fun onLoadMoreRequested() {
        mPtrFrame!!.refreshComplete()
        if (adapter!!.data.size < PAGE_SIZE) {
            adapter!!.loadMoreEnd(true)
        } else {
            presenter.getArticles(counts, page)
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
