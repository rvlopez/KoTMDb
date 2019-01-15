package com.example.rviciana.kotmdb.view.detail

import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.domain.bo.MoviesResponse
import com.example.rviciana.kotmdb.domain.usecase.GetMoviesRecommendationUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class MovieDetailPresenterTest {

    private var mockView: MovieDetailContract.View = mock()
    private var mockGetMoviesRecommendationUseCase: GetMoviesRecommendationUseCase = mock()

    private val presenter by lazy {
        MovieDetailPresenter(
            mockGetMoviesRecommendationUseCase
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter.setView(mockView)
    }

    @Test
    fun `show movie details on view ready`() {
        val movie = givenMovie()

        presenter.onViewReady(movie)

        verify(mockView).showMovieDetails(movie)
    }

    @Test
    fun `should show title on view ready`() {
        val movie = givenMovie()

        presenter.onViewReady(movie)

        verify(mockView).toolbarTitleShow(movie)
    }

    @Test
    fun `should show loading while getting recommendations`() {
        presenter.onViewReady(givenMovie())

        verify(mockView).showLoading()
    }

    @Test
    fun `should request recommendations when view is ready`() {
        presenter.onViewReady(givenMovie())

        verify(mockGetMoviesRecommendationUseCase).execute(any(), any(), any())
    }

    @Test
    fun `hide loading when getting response`() {
        presenter.onSuccess(givenMovieResponse())

        verify(mockView).hideLoading()
    }

    @Test
    fun `show recommendations when successful response`() {
        val movieResponse = givenMovieResponse()

        presenter.onSuccess(movieResponse)

        verify(mockView).showRecommendations(movieResponse.moviesList)
    }

    @Test
    fun `hide recommendations when empty response`() {
        presenter.onSuccess(givenEmptyResponse())

        verify(mockView).hideRecommendations()
    }

    @Test
    fun `hide recommendations when error response`() {
        presenter.onError(Throwable())

        verify(mockView).hideRecommendations()
    }

    @Test
    fun `hide loading when error response`() {
        presenter.onError(Throwable())

        verify(mockView).hideLoading()
    }

    @Test
    fun `clear use case on stop`() {
        presenter.onStop()

        verify(mockGetMoviesRecommendationUseCase).dispose()
    }

    private fun givenMovie(): Movie =
        Movie(
            1, 1, "",
            "", false, 0.0,
            0.0f, "", "",
            "", false, ""
        )

    private fun givenMovieResponse(): MoviesResponse =
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

    private fun givenEmptyResponse(): MoviesResponse = MoviesResponse(emptyList(), 1)
}