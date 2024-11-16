package com.task.mobiiworldbinalpractcal.view.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.task.mobiiworldbinalpractcal.R
import com.task.mobiiworldbinalpractcal.databinding.ActivityDetailBinding
import com.task.mobiiworldbinalpractcal.view.base.BaseActivity
import com.task.mobiiworldbinalpractcal.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private val mainViewModel: MainViewModel by viewModels()


    override fun setBinding(layoutInflater: LayoutInflater): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mainViewModel.getClickedData(0)
        mainViewModel.selectedRepository.observe(this) { item ->
            val selectedItem = item.data

            binding.tvTitle.text = selectedItem?.name ?: ""
            binding.tvDEscription.text = selectedItem?.name ?: ""
            binding.tvStars.text = "" + (selectedItem?.stargazers_count ?: 0) + " Stars"

            if (selectedItem?.isBookMark == true) {
                binding.ivBookmark.setImageResource(R.drawable.ic_bookmark_selected)
            } else {
                binding.ivBookmark.setImageResource(R.drawable.ic_bookmark)
            }
            binding.ivBookmark.setOnClickListener {
                if (selectedItem != null) {
                    selectedItem.isBookMark = !selectedItem.isBookMark
                    mainViewModel.addOrUpdateBookmark(selectedItem)
                }
                if (selectedItem?.isBookMark == true) {
                    binding.ivBookmark.setImageResource(R.drawable.ic_bookmark_selected)
                } else {
                    binding.ivBookmark.setImageResource(R.drawable.ic_bookmark)
                }
            }
        }
    }
}