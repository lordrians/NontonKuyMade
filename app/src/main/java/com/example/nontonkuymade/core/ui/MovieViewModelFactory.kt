package com.example.nontonkuymade.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase
import com.example.nontonkuymade.core.ui.detail.DetailMovieViewModel
import com.example.nontonkuymade.core.ui.favorite.MovieFavoriteViewModel
import com.example.nontonkuymade.core.ui.home.HomeViewModel
import com.example.nontonkuymade.di.AppScope
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class MovieViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull{
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown model class $modelClass")
        return creator.get() as T
    }
}