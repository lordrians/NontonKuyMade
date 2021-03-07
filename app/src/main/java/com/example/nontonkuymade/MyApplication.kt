package com.example.nontonkuymade

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication : MultiDexApplication() {
//    private val coreComponent: CoreComponent by lazy {
//        DaggerCoreComponent.factory().create(applicationContext)
//    }
//
//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.factory().create(coreComponent)
//    }
}