package com.example.rviciana.kotmdb.di

import com.example.rviciana.kotmdb.KoTMDbApplication
import com.example.rviciana.kotmdb.view.list.di.MoviesComponent
import com.example.rviciana.kotmdb.view.list.di.MoviesModule
import dagger.Component

@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class])
interface ApplicationComponent {

    fun inject(application: KoTMDbApplication)

    fun plus(moviesModule: MoviesModule) : MoviesComponent

}