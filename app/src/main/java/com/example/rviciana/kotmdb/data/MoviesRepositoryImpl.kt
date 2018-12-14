package com.example.rviciana.kotmdb.data

import com.example.rviciana.kotmdb.domain.bo.MoviesResponse
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
        private val moviesApi: MoviesApi,
        private val moviesMapper: MoviesMapper
) : MoviesRepository {

    override fun getMovies(page: Int): Single<MoviesResponse> =
            moviesApi.getMoviesList(NetworkConfig.API_KEY_TOKEN, NetworkConfig.API_LANG, page)
                    .map { moviesMapper.map(it) }

    override fun getMovieRecommendations(id: Int): Single<MoviesResponse> =
            moviesApi.getMoviesRecommendations(id, NetworkConfig.API_KEY_TOKEN, NetworkConfig.API_LANG)
                    .map { moviesMapper.map(it) }

    override fun searchMovie(movie: String, page: Int): Single<MoviesResponse> =
            moviesApi.searchMovie(movie, NetworkConfig.API_KEY_TOKEN, NetworkConfig.API_LANG, page)
                    .map { moviesMapper.map(it) }
}