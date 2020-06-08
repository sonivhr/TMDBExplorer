package com.moviesaggregator.api.apiresponse

import com.moviesaggregator.api.apiresponseobjects.Genre

class ContentDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val runtime: Int,
    val genres: List<Genre>,
    val backdrop_path: String,
    val vote_average: Float
)