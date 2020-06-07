package com.moviesaggregator.userinterface.contentdetail

import com.moviesaggregator.AggregatorApplication
import com.moviesaggregator.api.apiresponseobjects.ContentDetail
import io.reactivex.Single


class ContentDetailRepository {

    fun getContentDetail(contentId: Int) : Single<ContentDetail> =
        AggregatorApplication.appComponent.moviesApiInterface().getMovieDetails(movieId = contentId)

}