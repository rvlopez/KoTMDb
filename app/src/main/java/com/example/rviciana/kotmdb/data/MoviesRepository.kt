package com.example.rviciana.kotmdb.data

import com.example.rviciana.kotmdb.domain.bo.MoviesResponse
import io.reactivex.Single

interface MoviesRepository {

    fun getMovies(page: Int) : Single<MoviesResponse>

    fun getMovieRecommendations(id: Int) : Single<MoviesResponse>

    fun searchMovie(movie: String, page: Int) : Single<MoviesResponse>
}