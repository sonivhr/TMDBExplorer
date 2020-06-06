package com.moviesaggregator.di

import com.moviesaggregator.api.MoviesApiInterface
import dagger.Component
import javax.inject.Singleton

// Definition of the Application graph
@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun moviesApiInterface() : MoviesApiInterface
}