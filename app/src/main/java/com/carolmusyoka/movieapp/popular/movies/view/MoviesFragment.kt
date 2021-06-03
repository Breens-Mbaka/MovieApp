package com.carolmusyoka.movieapp.popular.movies.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.carolmusyoka.movieapp.R
import com.carolmusyoka.movieapp.data.api.ApiHelper
import com.carolmusyoka.movieapp.data.api.RetrofitBuilder
import com.carolmusyoka.movieapp.data.model.Popular
import com.carolmusyoka.movieapp.data.model.Result
import com.carolmusyoka.movieapp.popular.movies.DefaultItemDecorator
import com.carolmusyoka.movieapp.popular.movies.adapter.PopularMoviesListAdapter
import com.carolmusyoka.movieapp.popular.movies.viewmodel.PopularMoviesViewModel
import com.carolmusyoka.movieapp.popular.movies.viewmodel.ViewModelFactory
import com.carolmusyoka.movieapp.utils.Status
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private lateinit var popularMoviesListAdapter: PopularMoviesListAdapter
    private lateinit var viewModel: PopularMoviesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
//        setupUI()
        setupObservers()
    }

    private fun setupUI() {
    }

    private fun setupObservers() {

        viewModel.getPopularMovies().observe(viewLifecycleOwner, { it ->
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        pop_movies_grid.visibility = View.VISIBLE
                        resource.data?.let { popular ->
                            val popularList: List<Result> = popular.results
                            pop_movies_grid.setHasFixedSize(true)
                            pop_movies_grid.addItemDecoration(DefaultItemDecorator(4, 6))
                            Log.d("TAG", "setupObservers: " +it.message)
                            popularMoviesListAdapter = PopularMoviesListAdapter(popularList)
                            pop_movies_grid.adapter = popularMoviesListAdapter
                            pop_movies_grid.layoutManager = GridLayoutManager(context, 2)
                            retrieveList(popularList)
                            
                            viewLifecycleOwner.lifecycleScope.launch { 
                                viewModel.movies.collectLatest { 
                                    popularMoviesListAdapter.submitData(it)
                                }
                            }
                        }
                        Toast.makeText(context, "successful", Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        Log.d("TAG", "setupObservers: " + it.message)
                    }
                    Status.LOADING -> {


                    }
                }
            }
        })
    }

    private fun setupViewModel() {
       viewModel = ViewModelProvider(
           this,
           ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
       ).get(PopularMoviesViewModel::class.java)
    }
    private fun retrieveList(popular: List<Result>) {
        popularMoviesListAdapter.apply {
            notifyDataSetChanged()
        }
    }

}