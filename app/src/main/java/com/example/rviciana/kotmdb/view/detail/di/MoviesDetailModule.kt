package com.example.rviciana.kotmdb.view.detail.di

import com.example.rviciana.kotmdb.domain.MoviesRepository
import com.example.rviciana.kotmdb.domain.usecase.GetMoviesRecommendationUseCase
import com.example.rviciana.kotmdb.view.detail.MovieDetailContract
import com.example.rviciana.kotmdb.view.detail.MovieDetailPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class MoviesDetailModule {

    @Provides
    fun provideGetMoviesRecommendationUseCase(moviesRepository: MoviesRepository,
                                              @Named("subscribeOn") subscribeOn: Scheduler,
                                              @Named("observeOn") observeOn: Scheduler
    ) : GetMoviesRecommendationUseCase
            = GetMoviesRecommendationUseCase(moviesRepository, subscribeOn, observeOn)

    @Provides
    fun provideMovieDetailPresenter(getMoviesRecommendationUseCase: GetMoviesRecommendationUseCase)
            : MovieDetailContract.Presenter = MovieDetailPresenter(getMoviesRecommendationUseCase)
}