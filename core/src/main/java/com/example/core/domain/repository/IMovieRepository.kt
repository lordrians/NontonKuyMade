package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<MovieEntity>>>
    fun getDetailMovie(idMovie: String): Flow<Resource<MovieEntity>>
    fun setFavMovie(movie: MovieEntity, state: Boolean)
    fun getFavMovie(): Flow<List<MovieEntity>>
}