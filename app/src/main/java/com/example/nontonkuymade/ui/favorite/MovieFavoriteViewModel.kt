package com.example.nontonkuymade.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel @ViewModelInject constructor (
    private val movieUseCase: MovieUseCase
) : ViewModel(){
    val favoriteMovie = movieUseCase.getFavMovie().asLiveData()

    fun setFavMovie (movie: MovieEntity) =
        movieUseCase.setFavMovie(movie, !movie.isFav)
}