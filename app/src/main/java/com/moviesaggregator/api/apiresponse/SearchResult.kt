package com.moviesaggregator.api.apiresponse

import com.moviesaggregator.api.apiresponseobjects.Content

class SearchResult(
    val results: List<Content>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int
)