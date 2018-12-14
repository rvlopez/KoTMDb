package com.example.rviciana.kotmdb.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
        val voteCount: Int,
        val id: Int,
        val name: String,
        val firstAirDate: String,
        val video: Boolean,
        val voteAverage: Double,
        val popularity: Float,
        val posterPath: String,
        val originalLang: String,
        val backdropPath: String,
        val adult: Boolean,
        val overview: String
) : Parcelable