package com.example.rviciana.kotmdb.view.list

import com.example.rviciana.kotmdb.domain.bo.MoviesResponse
import com.example.rviciana.kotmdb.domain.usecase.GetMoviesUseCase

class MovieListPresenter(private val moviesUseCase: GetMoviesUseCase) : MovieListContract.Presenter {

    internal var page: Int = 1
    internal var lastPage: Int = 1
    internal lateinit var view: MovieListContract.View

    private fun requestMovies() = moviesUseCase.execute(page, ::onSuccess, ::onError)

    internal fun onSuccess(moviesResponse: MoviesResponse) {
        val movieList = moviesResponse.moviesList
        lastPage = moviesResponse.totalPages

        if (movieList.isNotEmpty()) {
            when(page) {
                1 -> {
                    view.showMovies(movieList)
                    view.hideLoading()
                }
                else -> { view.addMovies(movieList) }
            }
        } else {
            view.hideLoading()
        }
    }

    internal fun onError(throwable: Throwable) {
        if (page == 1) {
            view.hideLoading()
            view.showError(throwable)
        }
    }

    override fun setView(view: MovieListContract.View) {
        this.view = view
    }

    override fun onViewReady() {
        view.showLoading()
        requestMovies()
    }

    override fun onBottomReached() {
        if (++page <= lastPage) {
            requestMovies()
        }
    }

    override fun onStop() = moviesUseCase.dispose()

}