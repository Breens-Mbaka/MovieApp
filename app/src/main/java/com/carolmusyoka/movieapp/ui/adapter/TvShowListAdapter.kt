package com.carolmusyoka.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.carolmusyoka.movieapp.data.model.GoToTvSeries
import com.carolmusyoka.movieapp.data.model.InfiniteContentScrollListener
import com.carolmusyoka.movieapp.data.model.entity.TvSeries
import com.carolmusyoka.movieapp.databinding.ListItemTvShowBinding
import com.carolmusyoka.movieapp.databinding.ListItemTvShowGridBinding


class TvShowListAdapter internal constructor(
    private val goToTvSeries: GoToTvSeries,
    private val infiniteContentScrollListener: InfiniteContentScrollListener
) : ListAdapter<(TvSeries), TvShowListAdapter.ViewHolder>(TvShowDiffCallback()) {

    private var isTvShowItemGrid: Boolean = false

    class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(goToTvSeries: GoToTvSeries, item: TvSeries) {
            when (binding) {
                is ListItemTvShowBinding -> {
                    binding.goToInterface = goToTvSeries
                    binding.tvShow = item
                    binding.executePendingBindings()
                }
                is ListItemTvShowGridBinding -> {
                    binding.goToInterface = goToTvSeries
                    binding.tvShow = item
                    binding.executePendingBindings()
                }
                else -> throw Exception("Invalid list binding")
            }
        }
    }

    override fun submitList(list: List<TvSeries>?) {
        val newList: MutableList<TvSeries> = arrayListOf()
        if (list != null) newList.addAll(list)
        super.submitList(newList)
        infiniteContentScrollListener.itemsLoaded()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(goToTvSeries, getItem(position))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (recyclerView.layoutManager is GridLayoutManager) isTvShowItemGrid = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = if (isTvShowItemGrid) {
            ListItemTvShowGridBinding.inflate(layoutInflater, parent, false)
        } else {
            ListItemTvShowBinding.inflate(layoutInflater, parent, false)
        }
        return ViewHolder(binding)
    }

    private class TvShowDiffCallback : DiffUtil.ItemCallback<TvSeries>() {
        override fun areItemsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
