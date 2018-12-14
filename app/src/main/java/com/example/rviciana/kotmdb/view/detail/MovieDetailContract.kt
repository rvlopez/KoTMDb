package com.example.rviciana.kotmdb.view.detail

import com.example.rviciana.kotmdb.domain.bo.Movie

interface MovieDetailContract {

    interface View {
        fun showMovieDetails(movie: Movie)
        fun showTitleShow(movie: Movie)
        fun hideRecommendations()
        fun showRecommendations(movies: List<Movie>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun setView(view: MovieDetailContract.View?)
        fun onViewReady(movie: Movie)
        fun onStop()
    }
}