package com.example.nontonkuymade.core.di

import android.content.Context
import com.example.nontonkuymade.core.domain.repository.IMovieRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance mContext: Context): CoreComponent
    }

    fun provideRepository(): IMovieRepository
}