package com.carolmusyoka.movieapp.popular.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.carolmusyoka.movieapp.popular.movies.view.MoviesFragment
import com.carolmusyoka.movieapp.popular.mymovies.MyMoviesFragment
import com.carolmusyoka.movieapp.popular.series.SeriesFragment

class PageAdapter(fragManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragManager,lifecycle)  {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> {
                MoviesFragment()
           }
           1 -> {
               SeriesFragment()
           }
           2 -> {
                MyMoviesFragment()
           }
           else -> {
               Fragment()
           }
       }
    }

}