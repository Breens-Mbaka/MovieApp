package com.carolmusyoka.movieapp.ui.person_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.carolmusyoka.movieapp.data.db.remote.ApiService
import com.carolmusyoka.movieapp.data.model.EventObserver
import com.carolmusyoka.movieapp.databinding.FragmentPersonDetailsBinding
import com.carolmusyoka.movieapp.ui.BaseFragment
import com.carolmusyoka.movieapp.util.extension.openUrl
import com.carolmusyoka.movieapp.util.extension.showSnackBar


class PersonDetailsFragment : BaseFragment(true) {

    private val args: PersonDetailsFragmentArgs by navArgs()
    private val viewModel: PersonDetailsViewModel by viewModels { PersonDetailsViewModelFactory(args.personIdArg) }
    private lateinit var viewDataBinding: FragmentPersonDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            FragmentPersonDetailsBinding.inflate(inflater, container, false)
                .apply {
                    viewmodel = viewModel
                    lifecycleOwner = this@PersonDetailsFragment.viewLifecycleOwner
                }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToImageEvent.observe(
            viewLifecycleOwner,
            EventObserver { openUrl(ApiService.getProfileUrl(it.filePath)) })

        viewModel.goToCreditEvent.observe(viewLifecycleOwner, EventObserver {
            if (it.mediaType == "movie") {
                navigateToMovieDetails(it.id, it.title)
            } else if (it.mediaType == "tv") {
                navigateToTvShowDetails(it.id, it.title)
            }
        })
    }

    private fun navigateToMovieDetails(id: Int, title: String) {
        val action =
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(
                id,
                title
            )
        findNavController().navigate(action)
    }

    private fun navigateToTvShowDetails(id: Int, title: String) {
        val action =
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToTvShowDetailsFragment(
                id,
                title
            )
        findNavController().navigate(action)
    }
}