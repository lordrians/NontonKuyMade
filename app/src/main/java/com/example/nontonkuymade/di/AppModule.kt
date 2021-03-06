package com.example.nontonkuymade.di

import com.example.nontonkuymade.core.domain.usecase.MovieInteractor
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}