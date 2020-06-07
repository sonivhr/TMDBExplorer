package com.moviesaggregator.userinterface.aggregator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moviesaggregator.api.apiresponse.SearchResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesAggregatorViewModel : ViewModel() {

    private val TAG = this.javaClass.simpleName
    private val compositeDisposable = CompositeDisposable()
    private val moviesAggregatorRepository = MoviesAggregatorRepository()

    val liveDataNowPlayingMovies = MutableLiveData<SearchResult>()
    val liveDataNowPlayingMoviesException = MutableLiveData<Throwable>()

    val liveDataPopularMovies = MutableLiveData<SearchResult>()
    val liveDataPopularMoviesException = MutableLiveData<Throwable>()
    var popularMoviesPagesLoaded = 1
    var popularMoviesTotalPages = 2

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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}