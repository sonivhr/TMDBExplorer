package com.moviesaggregator.userinterface.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviesaggregator.R
import com.moviesaggregator.api.apiresponseobjects.PRESENTATION_HORIZONTAL
import com.moviesaggregator.api.apiresponseobjects.PRESENTATION_VERTICAL
import com.moviesaggregator.api.apiresponseobjects.AggregatorSection
import com.moviesaggregator.userinterface.eventlisteners.AggregatorItemClickListener
import kotlinx.android.synthetic.main.layout_inner_recycler_view_horizontal.view.*
import kotlinx.android.synthetic.main.layout_inner_recycler_view_vertical.view.*

class AggregatorAdapter(aggregatorSection: AggregatorSection,
                        val aggregatorItemClickListener: AggregatorItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val aggregatorSections = mutableListOf<AggregatorSection>()

    init {
        aggregatorSections.add(aggregatorSection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder: RecyclerView.ViewHolder
        val view: View
        when (viewType) {
            PRESENTATION_HORIZONTAL -> {
                view = layoutInflater.inflate(R.layout.layout_inner_recycler_view_horizontal, parent, false)
                viewHolder = HorizontalPosterViewHolder(view)
            }
            PRESENTATION_VERTICAL -> {
                view = layoutInflater.inflate(R.layout.layout_inner_recycler_view_vertical, parent, false)
                viewHolder = VerticalPosterViewHolder(view)
            }
            else -> {
                view = layoutInflater.inflate(R.layout.layout_inner_recycler_view_horizontal, parent, false)
                viewHolder = HorizontalPosterViewHolder(view)
            }
        }
        return viewHolder
    }

    override fun getItemCount() = aggregatorSections.size

    override fun getItemViewType(position: Int) = aggregatorSections[position].listPresentation

    fun addSection(aggregatorSection: AggregatorSection) {
        val size = aggregatorSections.size
        aggregatorSections.add(aggregatorSection)
        notifyItemInserted(size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            PRESENTATION_HORIZONTAL -> bindHorizontalViewHolder(holder, position)
            PRESENTATION_VERTICAL -> bindVerticalViewHolder(holder, position)
            else -> bindHorizontalViewHolder(holder, position)
        }
    }

    private fun bindHorizontalViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val aggregatorSection = aggregatorSections[position]
        val horizontalPosterAdapter = HorizontalPosterAdapter(aggregatorSection, aggregatorItemClickListener)
        val view = holder.itemView
        view.tvHorizontalSectionHeader.text = aggregatorSection.sectionHeader
        view.rvHorizontal.adapter = horizontalPosterAdapter
    }

    private fun bindVerticalViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val aggregatorSection = aggregatorSections[position]
        val verticalPosterAdapter = VerticalPosterAdapter(aggregatorSection, aggregatorItemClickListener)
        val view = holder.itemView
        view.tvVerticalSectionHeader.text = aggregatorSection.sectionHeader
        view.rvVertical.adapter = verticalPosterAdapter
    }

    class HorizontalPosterViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

    class VerticalPosterViewHolder(view: View) : RecyclerView.ViewHolder(view) { }
}