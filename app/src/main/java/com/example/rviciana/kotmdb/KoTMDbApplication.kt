package com.example.rviciana.kotmdb

import android.app.Application
import com.example.rviciana.kotmdb.di.ApplicationComponent
import com.example.rviciana.kotmdb.di.ApplicationModule
import com.example.rviciana.kotmdb.di.DaggerApplicationComponent

class KoTMDbApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule())
            .build()

        component.inject(this)
    }
}