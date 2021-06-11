package com.carolmusyoka.movieapp.ui.show_all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.carolmusyoka.movieapp.data.model.EventObserver
import com.carolmusyoka.movieapp.databinding.FragmentShowAllBinding
import com.carolmusyoka.movieapp.ui.BaseFragment
import com.carolmusyoka.movieapp.util.extension.showSnackBar

class ShowAllFragment : BaseFragment(true) {

    private val args: ShowAllFragmentArgs by navArgs()
    private val viewModel: ShowAllViewModel by viewModels { ShowAllViewModelFactory(args.movieListTypeArg) }
    private lateinit var viewDataBinding: FragmentShowAllBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentShowAllBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
                lifecycleOwner = this@ShowAllFragment.viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun setupViewModelObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner, EventObserver { view?.showSnackBar(it) })
        viewModel.goToMovieDetailsEvent.observe(
            viewLifecycleOwner,
            EventObserver { navigateToMovieDetails(it.id, it.title) })
    }

    private fun navigateToMovieDetails(movieId: Int, movieTitle: String) {
        val action = ShowAllFragmentDirections.actionShowAllFragmentToMovieDetailsFragment(
            movieId,
            movieTitle
        )
        findNavController().navigate(action)
    }
}