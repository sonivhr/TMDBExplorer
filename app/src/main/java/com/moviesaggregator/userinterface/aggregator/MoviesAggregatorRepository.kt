package com.moviesaggregator.userinterface.aggregator

import com.moviesaggregator.AggregatorApplication
import com.moviesaggregator.api.apiresponse.SearchResult
import io.reactivex.Single

class MoviesAggregatorRepository {
    fun getNowPlayingMovies(): Single<SearchResult> =
        AggregatorApplication.appComponent.moviesApiInterface().getNowPlayingMovies()

    fun getPopularMovies(page: Int): Single<SearchResult> =
        AggregatorApplication.appComponent.moviesApiInterface().getNowPopularMovies(page = page)
}