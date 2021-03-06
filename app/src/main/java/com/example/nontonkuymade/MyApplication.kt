package com.example.nontonkuymade

import android.app.Application
import com.example.nontonkuymade.core.di.CoreComponent
import com.example.nontonkuymade.core.di.DaggerCoreComponent
import com.example.nontonkuymade.di.AppComponent
import com.example.nontonkuymade.di.DaggerAppComponent

open class MyApplication : Application(){
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}