package com.moviesaggregator.userinterface.contentdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moviesaggregator.R
import com.moviesaggregator.databinding.LayoutContentDetailBinding
import com.moviesaggregator.util.ARGUMENT_EXTRA_CONTENT_ID
import com.moviesaggregator.util.loadOriginalImageWithGlide
import com.moviesaggregator.util.showSnackBar

class ContentDetailFragment : Fragment() {
    private lateinit var layoutContentDetailBinding: LayoutContentDetailBinding
    private lateinit var contentDetailViewModel: ContentDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutContentDetailBinding = DataBindingUtil.inflate(inflater,
            R.layout.layout_content_detail, container, false)
        return layoutContentDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentDetailViewModel = ViewModelProvider(this).get(ContentDetailViewModel::class.java)
        arguments?.let {
            contentDetailViewModel.contentId = it.getInt(ARGUMENT_EXTRA_CONTENT_ID)
            contentDetailViewModel.loadContentDetail()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        contentDetailViewModel.contentDetailLiveData.observe(viewLifecycleOwner, Observer {
            contentDetail ->
            layoutContentDetailBinding.contentDetail = contentDetail
            layoutContentDetailBinding.ivOriginalBackDrop.loadOriginalImageWithGlide(contentDetail.backdrop_path)
        })

        contentDetailViewModel.contentDetailLiveDataException.observe(viewLifecycleOwner, Observer {
            requireActivity().showSnackBar(getString(R.string.error_loading_movie_detail))
        })
    }
}