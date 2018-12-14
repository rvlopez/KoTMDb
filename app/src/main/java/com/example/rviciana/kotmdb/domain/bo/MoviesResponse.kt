package com.example.rviciana.kotmdb.domain.bo

data class MoviesResponse (
        val moviesList: List<Movie>,
        val totalPages: Int
)