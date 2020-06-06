package com.moviesaggregator.eventlisteners

import com.moviesaggregator.apiresponseobjects.Content

interface AggregatorItemClickListener {
    fun onItemClick(position: Int, content: Content)
}