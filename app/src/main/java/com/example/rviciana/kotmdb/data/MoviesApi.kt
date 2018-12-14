package com.example.rviciana.kotmdb.data

import com.example.rviciana.kotmdb.data.dto.MoviesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    companion object {
        const val API_KEY = "api_key"
        const val LANGUAGE = "language"
        const val PAGE = "page"
        const val TV_ID = "tv_id"
    }

    @GET("tv/popular")
    fun getMoviesList(
            @Query(API_KEY) api: String,
            @Query(LANGUAGE) language: String,
            @Query(PAGE) page: Int
    ): Single<MoviesResponseDto>

    @GET("tv/{tv_id}/similar")
    fun getMoviesRecommendations(
            @Path(TV_ID) id: Int,
            @Query(API_KEY) api: String,
            @Query(LANGUAGE) language: String
    ): Single<MoviesResponseDto>

    @GET("search/movie")
    fun searchMovie(@Query("query") query: String,
                    @Query(API_KEY) api: String,
                    @Query(LANGUAGE) language: String,
                    @Query(PAGE) page: Int
    ): Single<MoviesResponseDto>
}