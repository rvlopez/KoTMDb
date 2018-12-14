package com.example.rviciana.kotmdb.view.detail.di

import com.example.rviciana.kotmdb.view.detail.MovieDetailActivity
import dagger.Subcomponent

@Subcomponent(modules = [MoviesDetailModule::class])
interface MoviesDetailComponent {

    fun inject(activity: MovieDetailActivity)
}