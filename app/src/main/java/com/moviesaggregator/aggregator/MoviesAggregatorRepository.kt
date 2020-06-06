package com.moviesaggregator.aggregator

import com.moviesaggregator.AggregatorApplication
import com.moviesaggregator.apiresponse.SearchResult
import io.reactivex.Single

class MoviesAggregatorRepository {
    fun getNowPlayingMovies(): Single<SearchResult> =
        AggregatorApplication.appComponent.moviesApiInterface().getNowPlayingMovies()

    fun getPopularMovies(): Single<SearchResult> =
        AggregatorApplication.appComponent.moviesApiInterface().getNowPopularMovies()
}