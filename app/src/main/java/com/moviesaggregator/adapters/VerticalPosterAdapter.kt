package com.moviesaggregator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviesaggregator.R
import com.moviesaggregator.apiresponseobjects.AggregatorSection
import com.moviesaggregator.databinding.LayoutItemVerticalPosterBinding
import com.moviesaggregator.apiresponseobjects.Content
import com.moviesaggregator.eventlisteners.AggregatorItemClickListener
import com.moviesaggregator.util.loadImageWithGlide

class VerticalPosterAdapter(private val aggregatorSection: AggregatorSection,
                            private val aggregatorItemClickListener: AggregatorItemClickListener) :
    RecyclerView.Adapter<VerticalPosterAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemVerticalPosterBinding: LayoutItemVerticalPosterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_vertical_poster, parent, false)
        return ContentViewHolder(layoutItemVerticalPosterBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) =
        holder.bindViews(aggregatorSection.searchResult.results[position], position, aggregatorItemClickListener)

    override fun getItemCount() = aggregatorSection.searchResult.results.size

    class ContentViewHolder(private val layoutItemVerticalPosterBinding: LayoutItemVerticalPosterBinding):
        RecyclerView.ViewHolder(layoutItemVerticalPosterBinding.root) {

        private val cvContent: CardView = layoutItemVerticalPosterBinding.root.findViewById(R.id.cvContent)

        fun bindViews(content: Content, position: Int, aggregatorItemClickListener: AggregatorItemClickListener) {
            layoutItemVerticalPosterBinding.content = content
            content.poster_path?.let {
                layoutItemVerticalPosterBinding.ivPoster.loadImageWithGlide(content.poster_path)
            }
            cvContent.setOnClickListener {
                aggregatorItemClickListener.onItemClick(position, content)
            }
        }
    }
}