package com.example.rviciana.kotmdb.data

import com.example.rviciana.kotmdb.data.dto.MoviesResponseDto
import com.example.rviciana.kotmdb.domain.bo.Movie
import com.example.rviciana.kotmdb.domain.bo.MoviesResponse

class MoviesMapper {

    fun map(moviesResponseDto: MoviesResponseDto) : MoviesResponse =
            MoviesResponse(
                    toMoviesList(moviesResponseDto),
                    moviesResponseDto.totalPages
            )

    private fun toMoviesList(moviesResponseDto: MoviesResponseDto) : List<Movie> =
            moviesResponseDto.results.map {
                Movie(
                        it.voteCount, it.id, it.name, it.video, it.voteAverage, it.popularity,
                        generatePosterImageUrl(it.posterPath), it.originalLang,
                        generateBackdropImageUrl(it.backdropPath), it.adult, it.overview
                )
            }

    private fun generatePosterImageUrl(url: String) =
            NetworkConfig.API_BASE_IMAGE + NetworkConfig.API_POSTER_IMAGE_SIZE + url

    private fun generateBackdropImageUrl(url: String) =
            NetworkConfig.API_BASE_IMAGE + NetworkConfig.API_BACKDROP_IMAGE_SIZE + url
}