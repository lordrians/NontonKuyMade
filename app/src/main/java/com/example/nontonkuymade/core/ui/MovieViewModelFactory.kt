package com.example.nontonkuymade.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.nontonkuymade.core.di.MovieInjection
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase
import com.example.nontonkuymade.core.ui.detail.DetailMovieViewModel
import com.example.nontonkuymade.core.ui.favorite.MovieFavoriteViewModel
import com.example.nontonkuymade.core.ui.home.HomeViewModel
import com.example.nontonkuymade.di.AppScope
import javax.inject.Inject

@AppScope
class MovieViewModelFactory @Inject constructor(
    private val movieUseCase: MovieUseCase
): ViewModelProvider.NewInstanceFactory() {

//    companion object{
//        @Volatile
//        private var instance: MovieViewModelFactory? = null
//
//        fun getInstance(
//            mContext: Context
//        ): MovieViewModelFactory =
//            instance ?: synchronized(this){
//                instance ?: MovieViewModelFactory(MovieInjection.provideMovieUseCase(mContext))
//            }
//    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel (
                    movieUseCase
                ) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel (
                    movieUseCase
                ) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel (
                    movieUseCase
                ) as T
            }
            else -> throw Throwable("Uknwon ViewModel Class : ${modelClass.name}")
        }
    }
}