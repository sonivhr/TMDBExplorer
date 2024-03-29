package com.moviesaggregator.api.apiresponseobjects

import com.moviesaggregator.api.apiresponse.SearchResult

const val PRESENTATION_HORIZONTAL = 1
const val PRESENTATION_VERTICAL = 2
class AggregatorSection(
    val searchResult: SearchResult,
    val sectionHeader: String,
    val listPresentation: Int = PRESENTATION_HORIZONTAL
)