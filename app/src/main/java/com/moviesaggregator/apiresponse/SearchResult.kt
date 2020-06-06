package com.moviesaggregator.apiresponse

import com.moviesaggregator.apiresponseobjects.Content

class SearchResult(
    val results: List<Content>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int
)