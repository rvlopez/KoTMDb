package com.example.rviciana.kotmdb.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDto (
        @SerializedName("vote_count")
        val voteCount: Int,
        val id: Int,
        val name: String,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        val popularity: Float,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("original_language")
        val originalLang: String,
        @SerializedName("genre_ids")
        val genreIds: MutableList<Int>,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        val adult: Boolean,
        val overview: String
)