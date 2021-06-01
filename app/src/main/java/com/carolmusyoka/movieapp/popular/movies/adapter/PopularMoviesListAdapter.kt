package com.carolmusyoka.movieapp.popular.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carolmusyoka.movieapp.R
import com.carolmusyoka.movieapp.data.model.Result
import kotlinx.android.synthetic.main.movie_item.view.*

class PopularMoviesListAdapter(private val popularMovies: List<Result>) :
    PagingDataAdapter<Result, PagerListViewHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PagerListViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(
            PagerListViewHolder.LAYOUT, parent, false
        )
        return PagerListViewHolder(viewHolder)

    }

    override fun onBindViewHolder(holder: PagerListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class PagerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pop: Result) {
        itemView.apply {
            rating.text = pop.vote_average.toString()

            Glide.with(poster_image.context)
                .load("https://image.tmdb.org/t/p/w500" + pop.poster_path)
                .into(poster_image)
        }
    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.movie_item
    }

}

object MovieComparator : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}