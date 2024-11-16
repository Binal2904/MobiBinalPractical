package com.task.mobiiworldbinalpractcal.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.mobiiworldbinalpractcal.R
import com.task.mobiiworldbinalpractcal.data.response.Post
import com.task.mobiiworldbinalpractcal.databinding.ItemAdvertiseBinding

class AdvertiseAdapter(private var items: ArrayList<Post>, private val listener: OnViewClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var showLoading = false

    companion object {
        private const val ITEM_TYPE_NORMAL = 0
        private const val ITEM_TYPE_LOADING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_LOADING) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        } else {
            val binding = ItemAdvertiseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            AdvertiseViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size && showLoading) ITEM_TYPE_LOADING else ITEM_TYPE_NORMAL
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdvertiseViewHolder) {
            val item = items[position]
            holder.bind(item, listener)
        }
    }

    override fun getItemCount(): Int = items.size + if (showLoading) 1 else 0

    fun showLoadingIndicator(show: Boolean) {
        showLoading = show
        notifyDataSetChanged()
    }

    interface OnViewClick {
        fun onViewItemClick(id: Int)
    }

    inner class AdvertiseViewHolder(private val binding: ItemAdvertiseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post, listener: OnViewClick) {
            binding.tvTitle.text = item.name
            binding.tvDEscription.text = item.description
            binding.tvStars.text = "" + item.stargazers_count + " Stars"
            if (item.isBookMark) {
                binding.ivBookmark.setImageResource(R.drawable.ic_bookmark_selected)
            } else {
                binding.ivBookmark.setImageResource(R.drawable.ic_bookmark)
            }
            binding.cvMain.setOnClickListener {
                listener.onViewItemClick(item.id)
            }
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

}