package com.moviesaggregator.userinterface.eventlisteners

import com.moviesaggregator.api.apiresponseobjects.Content

interface AggregatorItemClickListener {
    fun onItemClick(position: Int, content: Content)
}