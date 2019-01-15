package com.example.rviciana.kotmdb.view.detail

import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.domain.bo.MoviesResponse
import com.example.rviciana.kotmdb.domain.usecase.GetMoviesRecommendationUseCase
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(
    private val getMoviesRecommendationUseCase: GetMoviesRecommendationUseCase
) : MovieDetailContract.Presenter {

    internal lateinit var view: MovieDetailContract.View

    internal fun onSuccess(moviesResponse: MoviesResponse) {
        view.hideLoading()
        val movieList = moviesResponse.moviesList
        if (movieList.isNotEmpty()) {
            view.showRecommendations(moviesResponse.moviesList)
        } else {
            view.hideRecommendations()
        }
    }

    internal fun onError(throwable: Throwable) {
        view.hideLoading()
        view.hideRecommendations()
    }

    override fun setView(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun onViewReady(movie: Movie) {
        view.toolbarTitleShow(movie)
        view.showMovieDetails(movie)
        view.showLoading()
        getMoviesRecommendationUseCase.execute(movie.id, ::onSuccess, ::onError)
    }

    override fun onStop() {
        getMoviesRecommendationUseCase.dispose()
    }
}