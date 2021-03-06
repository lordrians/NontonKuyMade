package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<MovieEntity>>>
    fun getDetailMovie(idMovie: String): Flow<Resource<MovieEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)
    fun getFavMovie(): Flow<List<MovieEntity>>
}