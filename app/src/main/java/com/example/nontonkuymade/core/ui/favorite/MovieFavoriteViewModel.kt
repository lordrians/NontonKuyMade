package com.example.nontonkuymade.core.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel (
    private val movieUseCase: MovieUseCase
) : ViewModel(){
    val favoriteMovie = movieUseCase.getFavMovie()

    fun setFavMovie (movie: MovieEntity) =
        movieUseCase.setFavMovie(movie, !movie.isFav)
}