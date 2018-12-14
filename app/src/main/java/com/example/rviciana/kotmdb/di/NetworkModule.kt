package com.example.rviciana.kotmdb.di

import com.example.rviciana.kotmdb.data.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(NetworkConfig.API_URL)
        client(okHttpClient)
    }.build()

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient
            = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideMoviesMapper() : MoviesMapper = MoviesMapper()

    @Provides
    fun provideMoviesRepository(moviesApi: MoviesApi, moviesMapper: MoviesMapper) : MoviesRepository
            = MoviesRepositoryImpl(moviesApi, moviesMapper)

}