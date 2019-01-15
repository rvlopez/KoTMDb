package com.example.rviciana.kotmdb.view.list

import com.example.rviciana.kotmdb.domain.bo.Movie

interface MovieListContract {

    interface View {
        fun showMovies(movieList: List<Movie>)
        fun addMovies(movieList: List<Movie>)
        fun showLoading()
        fun hideLoading()
        fun showError(throwable: Throwable)
    }

    interface Presenter {
        fun setView(view: MovieListContract.View)
        fun onViewReady()
        fun onBottomReached()
        fun onStop()
    }
}