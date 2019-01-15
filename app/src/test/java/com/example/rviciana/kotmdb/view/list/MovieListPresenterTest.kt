package com.example.rviciana.kotmdb.view.list

import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.domain.bo.MoviesResponse
import com.example.rviciana.kotmdb.domain.usecase.GetMoviesUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class MovieListPresenterTest {

    private var mockView: MovieListContract.View = mock()
    private var mockMovieListUseCase: GetMoviesUseCase = mock()

    private val presenter by lazy {
        MovieListPresenter(
            mockMovieListUseCase
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter.setView(mockView)
    }

    @Test
    fun `should show loading when fetching movies`() {
        presenter.onViewReady()

        verify(mockView).showLoading()
    }

    @Test
    fun `should request data on view ready`() {
        presenter.onViewReady()

        verify(mockMovieListUseCase).execute(any(), any(), any())
    }

    @Test
    fun `should request more data on bottom reached`() {
        presenter.lastPage = 20
        presenter.page = 1

        presenter.onBottomReached()

        verify(mockMovieListUseCase).execute(any(), any(), any())
    }

    @Test
    fun `should not request more data on bottom reached`() {
        presenter.lastPage = 20
        presenter.page = 20

        presenter.onBottomReached()

        verifyZeroInteractions(mockMovieListUseCase)
    }

    @Test
    fun `should dispose use case on stop`() {
        presenter.onStop()

        verify(mockMovieListUseCase).dispose()
    }

    @Test
    fun `should show error view given error response`() {
        val throwable = Throwable()
        presenter.page = 1

        presenter.onError(throwable)

        verify(mockView).hideLoading()
        verify(mockView).showError(throwable)
    }

    @Test
    fun `should do nothing given error response`() {
        val throwable = Throwable()
        presenter.page = 2

        presenter.onError(throwable)

        verifyZeroInteractions(mockView)
    }

    @Test
    fun `should hide loading when first response is empty` () {
        presenter.onSuccess(givenEmptyResponse())

        verify(mockView).hideLoading()
    }

    @Test
    fun `should show movies when first response has data`() {
        val moviesResponse = givenSuccessResponse()
        presenter.onSuccess(moviesResponse)

        verify(mockView).showMovies(moviesResponse.moviesList)
        verify(mockView).hideLoading()
    }

    @Test
    fun `should add movies when non first response has data`() {
        val getMoviesResponse = givenSuccessResponse()
        presenter.page = 2

        presenter.onSuccess(getMoviesResponse)

        verify(mockView).addMovies(getMoviesResponse.moviesList)
    }

    private fun givenEmptyResponse(): MoviesResponse =
        MoviesResponse(
            emptyList(), 1
        )

    private fun givenSuccessResponse(): MoviesResponse =
        MoviesResponse(
            listOf(
                Movie(
                    1, 1, "",
                    "", false, 0.0,
                    0.0f, "", "",
                    "", false, ""
                )
            ), 1
        )
}