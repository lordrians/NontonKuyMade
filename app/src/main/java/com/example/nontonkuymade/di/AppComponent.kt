package com.example.nontonkuymade.di

import com.example.nontonkuymade.core.di.CoreComponent
import com.example.nontonkuymade.core.ui.detail.DetailMovieActivity
import com.example.nontonkuymade.core.ui.favorite.FavoriteFragment
import com.example.nontonkuymade.core.ui.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailMovieActivity)
}