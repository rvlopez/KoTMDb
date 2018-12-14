package com.example.rviciana.kotmdb.view.list.di

import com.example.rviciana.kotmdb.view.list.MovieListActivity
import dagger.Subcomponent

@Subcomponent(modules = [MoviesModule::class])
interface MoviesComponent {

   fun inject(activity: MovieListActivity)
}