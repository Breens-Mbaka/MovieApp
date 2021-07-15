package com.carolmusyoka.movieapp.ui.tv_series_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.carolmusyoka.movieapp.data.db.remote.ApiService
import com.carolmusyoka.movieapp.data.model.EventObserver
import com.carolmusyoka.movieapp.databinding.FragmentTvSeriesDetailsBinding
import com.carolmusyoka.movieapp.ui.BaseFragment
import com.carolmusyoka.movieapp.util.extension.openUrl
import com.carolmusyoka.movieapp.util.extension.showSnackBar

class TvShowDetailsFragment : BaseFragment(true) {

    private val args: TvShowDetailsFragmentArgs by navArgs()
    private val viewModel: TVShowDetailsViewModel by viewModels { TVShowDetailsViewModelFactory(args.tvShowIdArg) }
    private lateinit var viewDataBinding: FragmentTvSeriesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            FragmentTvSeriesDetailsBinding.inflate(inflater, container, false)
                .apply {
                    viewmodel = viewModel
                    lifecycleOwner = this@TvShowDetailsFragment.viewLifecycleOwner
                }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToCastDetailsEvent.observe(
            viewLifecycleOwner,
            EventObserver { navigateToPersonDetails(it.id, it.name) })
        viewModel.goToVideoEvent.observe(
            viewLifecycleOwner,
            EventObserver { openUrl(ApiService.getYoutubeWatchUrl(it.key)) })
    }

    private fun navigateToPersonDetails(personId: Int, personName: String) {
        val action =
            TvShowDetailsFragmentDirections.actionTvShowDetailsFragmentToPersonDetailsFragment(
                personId,
                personName
            )
        findNavController().navigate(action)
    }
}