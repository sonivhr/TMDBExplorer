package com.moviesaggregator.userinterface.aggregator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moviesaggregator.R
import com.moviesaggregator.userinterface.adapters.AggregatorAdapter
import com.moviesaggregator.api.apiresponseobjects.AggregatorSection
import com.moviesaggregator.api.apiresponseobjects.Content
import com.moviesaggregator.api.apiresponseobjects.PRESENTATION_VERTICAL
import com.moviesaggregator.userinterface.contentdetail.ContentDetailFragment
import com.moviesaggregator.userinterface.eventlisteners.AggregatorItemClickListener
import com.moviesaggregator.util.ARGUMENT_EXTRA_CONTENT_ID
import com.moviesaggregator.util.addFragmentWithBackStack
import com.moviesaggregator.util.showSnackBar
import kotlinx.android.synthetic.main.layout_content_listing.*

class AggregatorFragment : Fragment(), AggregatorItemClickListener {

    private lateinit var moviesAggregatorAdapter: AggregatorAdapter
    private lateinit var moviesAggregatorViewModel : MoviesAggregatorViewModel

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.apply {
            setTitle(R.string.app_name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_content_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvContentList.also {
            it.setHasFixedSize(false)
        }

        moviesAggregatorViewModel = ViewModelProvider(this).get(MoviesAggregatorViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        moviesAggregatorViewModel.liveDataNowPlayingMovies.observe(viewLifecycleOwner, Observer {
            nowPlayingMovies ->
            hideProgressBar()
            showRecyclerView()
            if (!::moviesAggregatorAdapter.isInitialized) {
                val aggregatorSection = AggregatorSection(nowPlayingMovies, getString(R.string.title_now_playing))
                moviesAggregatorAdapter = AggregatorAdapter(aggregatorSection, this)
            }
            rvContentList.adapter = moviesAggregatorAdapter

        })

        moviesAggregatorViewModel.liveDataNowPlayingMoviesException.observe(viewLifecycleOwner, Observer {
            throwable ->
            requireActivity().showSnackBar(getString(R.string.error_loading_now_playing_movies))
        })

        moviesAggregatorViewModel.liveDataPopularMovies.observe(viewLifecycleOwner, Observer {
            popularMovies ->
            val aggregatorSection = AggregatorSection(popularMovies,
                getString(R.string.title_popular_movies), PRESENTATION_VERTICAL)
            if (!::moviesAggregatorAdapter.isInitialized) {
                moviesAggregatorAdapter = AggregatorAdapter(aggregatorSection, this)
            } else {
                moviesAggregatorAdapter.addSection(aggregatorSection)
            }
        })

        moviesAggregatorViewModel.liveDataPopularMoviesException.observe(viewLifecycleOwner, Observer {
            throwable ->
            requireActivity().showSnackBar(getString(R.string.error_loading_popular_movies))
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showRecyclerView() {
        if (rvContentList.visibility == View.GONE ||
            rvContentList.visibility == View.INVISIBLE) {
            rvContentList.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(position: Int, content: Content) {
        val bundle = Bundle()
        bundle.putInt(ARGUMENT_EXTRA_CONTENT_ID, content.id)
        requireActivity().addFragmentWithBackStack(fragmentClass = ContentDetailFragment::class.java,
            args = bundle, tag = ContentDetailFragment::class.java.simpleName
        )
    }
}