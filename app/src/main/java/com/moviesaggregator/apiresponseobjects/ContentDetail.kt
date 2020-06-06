package com.moviesaggregator.apiresponseobjects

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