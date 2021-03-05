package com.example.nontonkuymade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.nontonkuymade.core.data.Resource
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<MovieEntity>>>
    fun getDetailMovie(idMovie: String): Flow<Resource<MovieEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)
    fun getFavMovie(): Flow<List<MovieEntity>>
}