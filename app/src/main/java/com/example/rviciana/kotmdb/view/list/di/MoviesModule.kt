package com.example.rviciana.kotmdb.view.list.di

import com.example.rviciana.kotmdb.domain.MoviesRepository
import com.example.rviciana.kotmdb.domain.usecase.GetMoviesUseCase
import com.example.rviciana.kotmdb.view.list.MovieListContract
import com.example.rviciana.kotmdb.view.list.MovieListPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class MoviesModule {

    @Provides
    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository,
                                @Named("subscribeOn") subscribeOn: Scheduler,
                                @Named("observeOn") observeOn: Scheduler) : GetMoviesUseCase
            = GetMoviesUseCase(moviesRepository, subscribeOn, observeOn)

    @Provides
    fun provideMovieListPresenter(getMoviesUseCase: GetMoviesUseCase): MovieListContract.Presenter
            = MovieListPresenter(getMoviesUseCase)
}