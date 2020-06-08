package com.moviesaggregator.userinterface.aggregator

import androidx.lifecycle.MutableLiveData
import com.moviesaggregator.api.apiresponse.SearchResult
import com.moviesaggregator.userinterface.baseviewmodel.BaseViewModel
import io.reactivex.schedulers.Schedulers

class MoviesAggregatorViewModel : BaseViewModel() {

    private val TAG = this.javaClass.simpleName
    private val moviesAggregatorRepository = MoviesAggregatorRepository()

    val liveDataNowPlayingMovies = MutableLiveData<SearchResult>()
    val liveDataNowPlayingMoviesException = MutableLiveData<Throwable>()

    val liveDataPopularMovies = MutableLiveData<SearchResult>()
    val liveDataPopularMoviesException = MutableLiveData<Throwable>()
    private var popularMoviesPagesLoaded = 1
    private var popularMoviesTotalPages = 2

    init {
        loadOnNowMovies()
    }

    private fun loadOnNowMovies() {
        compositeDisposable.add(
            moviesAggregatorRepository.getNowPlayingMovies()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { nowPlayingMovies ->
                        liveDataNowPlayingMovies.postValue(nowPlayingMovies)
                        loadPopularMovies()
                    },
                    { throwable ->
                        liveDataNowPlayingMoviesException.postValue(throwable)
                        loadPopularMovies()
                    }
                )
        )
    }

    fun loadPopularMovies() {

        if (popularMoviesPagesLoaded > popularMoviesTotalPages) {
            return
        }

        compositeDisposable.add(
            moviesAggregatorRepository.getPopularMovies(popularMoviesPagesLoaded)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { popularMovies ->
                        popularMoviesPagesLoaded += 1
                        popularMoviesTotalPages = popularMovies.total_pages
                        liveDataPopularMovies.postValue(popularMovies)
                    },
                    { throwable -> liveDataPopularMoviesException.postValue(throwable) }
                )
        )
    }
}