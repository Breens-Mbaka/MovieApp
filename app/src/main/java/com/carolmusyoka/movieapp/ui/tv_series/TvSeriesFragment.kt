package com.carolmusyoka.movieapp.ui.tv_series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carolmusyoka.movieapp.data.model.EventObserver
import com.carolmusyoka.movieapp.databinding.FragmentTvSeriesBinding
import com.carolmusyoka.movieapp.ui.BaseFragment
import com.carolmusyoka.movieapp.util.extension.showSnackBar


class TvSeriesFragment : BaseFragment(false) {

    private val viewModel: TvSeriesViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentTvSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            FragmentTvSeriesBinding.inflate(inflater, container, false)
                .apply {
                    viewmodel = viewModel
                    lifecycleOwner = this@TvSeriesFragment.viewLifecycleOwner
                }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToTvSeriesEvent.observe(
            viewLifecycleOwner,
            EventObserver { navigateToTvShowDetails(it.id, it.title) })
    }

    private fun navigateToTvShowDetails(tvShowId: Int, tvShowTitle: String) {
        val action = TvSeriesFragmentDirections.actionNavigationTvShowsToTVShowDetailsFragment(
            tvShowId, tvShowTitle
        )
        findNavController().navigate(action)
    }
}