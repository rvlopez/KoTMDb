package com.example.rviciana.kotmdb.navigator

import android.widget.ImageView
import android.widget.TextView
import com.example.rviciana.kotmdb.domain.bo.Movie

interface Navigator {

    fun navigateToMovieDetail(movie: Movie, imageView: ImageView)

    fun navigateToSearchMovie()
}