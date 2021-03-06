package com.example.nontonkuymade.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nontonkuymade.core.ui.MovieViewModelFactory
import com.example.nontonkuymade.core.ui.detail.DetailMovieViewModel
import com.example.nontonkuymade.core.ui.favorite.MovieFavoriteViewModel
import com.example.nontonkuymade.core.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieFavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: MovieFavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun bindDetailMovieViewModel(viewModel: DetailMovieViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory

}