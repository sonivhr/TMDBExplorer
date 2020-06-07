package com.moviesaggregator.userinterface.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviesaggregator.R
import com.moviesaggregator.api.apiresponseobjects.AggregatorSection
import com.moviesaggregator.databinding.LayoutItemVerticalPosterBinding
import com.moviesaggregator.api.apiresponseobjects.Content
import com.moviesaggregator.userinterface.eventlisteners.AggregatorItemClickListener
import com.moviesaggregator.util.loadPosterImageWithGlide

class VerticalPosterAdapter(private val aggregatorSection: AggregatorSection,
                            private val aggregatorItemClickListener: AggregatorItemClickListener) :
    RecyclerView.Adapter<VerticalPosterAdapter.ContentViewHolder>() {

    private val contentList = mutableListOf<Content>()

    init {
        contentList.addAll(aggregatorSection.searchResult.results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemVerticalPosterBinding: LayoutItemVerticalPosterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.layout_item_vertical_poster, parent, false)
        return ContentViewHolder(layoutItemVerticalPosterBinding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) =
        holder.bindViews(contentList[position], position, aggregatorItemClickListener)

    override fun getItemCount() = contentList.size

    fun addMoreResults(contents: List<Content>) {
        val size = contentList.size
        contentList.addAll(contents)
        notifyItemInserted(size)
    }

    class ContentViewHolder(private val layoutItemVerticalPosterBinding: LayoutItemVerticalPosterBinding):
        RecyclerView.ViewHolder(layoutItemVerticalPosterBinding.root) {

        private val cvContent: CardView = layoutItemVerticalPosterBinding.root.findViewById(R.id.cvContent)

        fun bindViews(content: Content, position: Int, aggregatorItemClickListener: AggregatorItemClickListener) {
            layoutItemVerticalPosterBinding.content = content
            content.poster_path?.let {
                layoutItemVerticalPosterBinding.ivPoster.loadPosterImageWithGlide(content.poster_path)
            }
            cvContent.setOnClickListener {
                aggregatorItemClickListener.onItemClick(position, content)
            }
        }
    }
}