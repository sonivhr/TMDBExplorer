package com.moviesaggregator.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.moviesaggregator.api.MoviesApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val HTTP_TIME_OUT = 15
private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
@Module
class NetworkModule {

    @Provides
    fun stethoInterceptor() = StethoInterceptor()

    @Singleton
    @Provides
    fun provideHTTPClient(stethoInterceptor: StethoInterceptor) = OkHttpClient.Builder()
            .addNetworkInterceptor(stethoInterceptor)
            .connectTimeout(HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofitObject(httpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideMoviesApiClient(retrofit: Retrofit) = retrofit.create(MoviesApiInterface::class.java)


}