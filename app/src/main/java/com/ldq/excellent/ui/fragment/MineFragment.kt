package com.ldq.excellent.ui.fragment

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ldq.excellent.R
import com.ldq.excellent.base.BaseFragment
import com.ldq.excellent.mvp.contract.WelfareContact
import com.ldq.excellent.mvp.entity.GankEntity
import com.ldq.excellent.mvp.presenter.WelfarePresenter
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment(), WelfareContact.View, BaseQuickAdapter.RequestLoadMoreListener {
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

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    private val presenter by lazy { WelfarePresenter() }

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        presenter.attachView(this)
        recycle.layoutManager = LinearLayoutManager(activity)

        initAdpter()
        mPtrFrame.post { mPtrFrame.autoRefresh(true) }
        mPtrFrame.setLastUpdateTimeRelateObject(this)
        mPtrFrame.setPtrHandler(object : PtrDefaultHandler() {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                page = 1
                presenter.getWelfares(counts, page)
            }
        })
    }

    private fun initAdpter() {
        adapter = object : BaseQuickAdapter<GankEntity, BaseViewHolder>(R.layout.welfare_item) {
            override fun convert(helper: BaseViewHolder?, item: GankEntity?) {
                val imageView = helper?.getView<ImageView>(R.id.imageView)
                imageView?.visibility = View.VISIBLE
                val option = RequestOptions().placeholder(R.mipmap.placeholder)
                    .fallback(R.mipmap.placeholder).error(R.mipmap.placeholder)

                imageView?.let {
                    Glide.with(this@MineFragment)
                        .load(item?.url)
                        .apply(option)
                        .into(it)
                }
            }
        }

        recycle?.adapter = adapter
        (adapter as BaseQuickAdapter<GankEntity, BaseViewHolder>).setOnLoadMoreListener(
            this,
            recycle
        )

    }

    override fun onSuccess(welfares: List<GankEntity>) {
        if (welfares.size == 0) {
            adapter?.setEmptyView(R.layout.empty);
            mPtrFrame.refreshComplete();
            return;
        }
        if (page == 1) {
            adapter?.setNewData(welfares);
            adapter?.disableLoadMoreIfNotFullPage();
            mPtrFrame.refreshComplete();
        } else {
            adapter?.addData(welfares);
            adapter?.loadMoreComplete();
        }
        page++;
    }

    override fun onFailure(e: Throwable?) {
        mPtrFrame.refreshComplete()
        adapter!!.loadMoreFail()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMoreRequested() {
        mPtrFrame.refreshComplete()
        if (adapter!!.data.size < PAGE_SIZE) {
            adapter!!.loadMoreEnd(true)
        } else {
            presenter.getWelfares(counts, page)
        }
    }

}
