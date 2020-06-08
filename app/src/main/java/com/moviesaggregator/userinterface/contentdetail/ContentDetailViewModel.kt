package com.moviesaggregator.userinterface.contentdetail

import androidx.lifecycle.MutableLiveData
import com.moviesaggregator.api.apiresponse.ContentDetail
import com.moviesaggregator.userinterface.baseviewmodel.BaseViewModel
import io.reactivex.schedulers.Schedulers

class ContentDetailViewModel : BaseViewModel() {

    var contentId: Int = 0
    private val contentDetailRepository = ContentDetailRepository()

    val contentDetailLiveData = MutableLiveData<ContentDetail>()
    val contentDetailLiveDataException = MutableLiveData<Throwable>()

    fun loadContentDetail() {
        compositeDisposable.add(
            contentDetailRepository.getContentDetail(contentId)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        contentDetail -> contentDetailLiveData.postValue(contentDetail)
                    },
                    {
                        throwable -> contentDetailLiveDataException.postValue(throwable)
                    }
                )
        )
    }
}