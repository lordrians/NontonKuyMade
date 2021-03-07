package com.example.nontonkuymade

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication : MultiDexApplication() {
}