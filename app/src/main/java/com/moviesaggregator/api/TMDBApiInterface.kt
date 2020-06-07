package com.moviesaggregator.api

import com.moviesaggregator.api.apiresponse.SearchResult
import com.moviesaggregator.api.apiresponseobjects.ContentDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "ba3446d281c92c0e5ed8ed04cb7b12be"
private const val LANGUAGE = "en-US"
private const val REGION = "US"
interface MoviesApiInterface {

    @GET("now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String = API_KEY,
                            @Query("language") language: String = LANGUAGE,
                            @Query("region") region: String = REGION,
                            @Query("page") page: Int = 1): Single<SearchResult>

    @GET("popular")
    fun getNowPopularMovies(@Query("api_key") apiKey: String = API_KEY,
                            @Query("language") language: String = LANGUAGE,
                            @Query("region") region: String = REGION,
                            @Query("page") page: Int = 1): Single<SearchResult>

    @GET("{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int,
                        @Query("api_key") apiKey: String = API_KEY,
                        @Query("language") language: String = LANGUAGE): Single<ContentDetail>
}