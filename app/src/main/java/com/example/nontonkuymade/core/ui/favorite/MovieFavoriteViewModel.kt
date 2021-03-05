package com.example.nontonkuymade.core.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel (
    private val movieUseCase: MovieUseCase
) : ViewModel(){
    val favoriteMovie = movieUseCase.getFavMovie().asLiveData()

    fun setFavMovie (movie: MovieEntity) =
        movieUseCase.setFavMovie(movie, !movie.isFav)
}