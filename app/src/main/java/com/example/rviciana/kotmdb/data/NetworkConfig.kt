package com.example.rviciana.kotmdb.data

import java.util.*

object NetworkConfig {

    const val API_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_TOKEN = "93aea0c77bc168d8bbce3918cefefa45"
    val API_LANG: String get() = Locale.getDefault().language
    const val API_BASE_IMAGE = "https://image.tmdb.org/t/p/"
    const val API_POSTER_IMAGE_SIZE = "w500"
    const val API_BACKDROP_IMAGE_SIZE = "w1280"

}