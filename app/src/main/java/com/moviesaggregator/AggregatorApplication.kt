package com.moviesaggregator

import android.app.Application
import com.facebook.stetho.Stetho
import com.moviesaggregator.di.DaggerApplicationComponent
import com.moviesaggregator.di.NetworkModule

class AggregatorApplication : Application() {

    companion object {
        val appComponent = DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
    }

    private fun initializeStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}