package com.task.mobiiworldbinalpractcal.view.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.mobiiworldbinalpractcal.data.response.Post
import com.task.mobiiworldbinalpractcal.databinding.ActivityHomeBinding
import com.task.mobiiworldbinalpractcal.enum.ApiStatus
import com.task.mobiiworldbinalpractcal.view.base.BaseActivity
import com.task.mobiiworldbinalpractcal.view.ui.adapter.AdvertiseAdapter
import com.task.mobiiworldbinalpractcal.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val mainViewModel: MainViewModel by viewModels()
    var advertiseAdapter: AdvertiseAdapter? = null
    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false // Track if it's the last page

    private var items: ArrayList<Post> = arrayListOf()
    override fun setBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        initAdapter()
        setLiveDataListeners()
        binding.rvHomeItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    currentPage++
                    isLoading = true
                    mainViewModel.getRepoData(currentPage)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        items.clear()
        currentPage = 0
        isLastPage = false
        mainViewModel.getRepoData(currentPage)
    }


    private fun initAdapter() {
        advertiseAdapter = AdvertiseAdapter(items, object : AdvertiseAdapter.OnViewClick {
            override fun onViewItemClick(id: Int) {
                mainViewModel.setClickedData(id)
                startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
            }
        })
        binding.rvHomeItems.layoutManager = LinearLayoutManager(this)
        binding.rvHomeItems.adapter = advertiseAdapter
    }

    private fun setLiveDataListeners() {

        mainViewModel.mActivityDataList.observe(this) {
            when (it.status) {
                ApiStatus.SUCCESS -> {
                    it.data?.let { user ->
                        items.addAll(user)
                        advertiseAdapter?.notifyDataSetChanged()
                        if (user.size < mainViewModel.pageSize) {
                            isLastPage = true // No more pages to load
                        }
                        isLoading = false
                        advertiseAdapter?.showLoadingIndicator(false)
                    }
                }

                ApiStatus.LOADING -> {
                    advertiseAdapter?.showLoadingIndicator(true)

                }

                ApiStatus.ERROR -> {
                    messageSnackBar(it.message!!, binding.root)
                    isLoading = false
                    advertiseAdapter?.showLoadingIndicator(false)
                }
            }
        }
    }


}