package com.example.rviciana.kotmdb.data.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponseDto(
        val page: Int,
        @SerializedName("total_results")
        val totalResults: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        val results: List<MovieDto>
)