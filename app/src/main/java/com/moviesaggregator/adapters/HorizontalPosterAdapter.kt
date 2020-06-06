package com.moviesaggregator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviesaggregator.R
import com.moviesaggregator.databinding.LayoutItemHorizontalPosterBinding
import com.moviesaggregator.apiresponseobjects.Content
import com.moviesaggregator.apiresponseobjects.AggregatorSection
import com.moviesaggregator.eventlisteners.AggregatorItemClickListener
import com.moviesaggregator.util.loadImageWithGlide

class HorizontalPosterAdapter(private val aggregatorSection: AggregatorSection,
                              private val aggregatorItemClickListener: AggregatorItemClickListener) :
    RecyclerView.Adapter<HorizontalPosterAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemPosterBinding: LayoutItemHorizontalPosterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_horizontal_poster, parent, false)
        return ContentViewHolder(layoutItemPosterBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) =
        holder.bindViews(aggregatorSection.searchResult.results[position], position,
            aggregatorItemClickListener)

    override fun getItemCount() = aggregatorSection.searchResult.results.size

    class ContentViewHolder(private val layoutItemPosterBinding: LayoutItemHorizontalPosterBinding):
        RecyclerView.ViewHolder(layoutItemPosterBinding.root) {

        private val cvContent: CardView = layoutItemPosterBinding.root.findViewById(R.id.cvContent)

        fun bindViews(content: Content, position: Int, aggregatorItemClickListener: AggregatorItemClickListener) {
            layoutItemPosterBinding.content = content
            content.poster_path?.let {
                layoutItemPosterBinding.ivPoster.loadImageWithGlide(content.poster_path)
            }
            cvContent.setOnClickListener {
                aggregatorItemClickListener.onItemClick(position, content)
            }
        }
    }
}