package com.example.nontonkuymade.core.di

import com.example.nontonkuymade.core.data.MovieRepository
import com.example.nontonkuymade.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

}