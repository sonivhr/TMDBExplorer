package com.moviesaggregator.userinterface.contentdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moviesaggregator.api.apiresponseobjects.ContentDetail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ContentDetailViewModel : ViewModel() {

    var contentId: Int = 0
    private val compositeDisposable = CompositeDisposable()
    val contentDetailRepository = ContentDetailRepository()

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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}