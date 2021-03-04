package com.example.nontonkuymade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.nontonkuymade.core.data.Resource
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity

interface MovieUseCase {
    fun getAllMovie(): LiveData<Resource<List<MovieEntity>>>
    fun getDetailMovie(idMovie: String): LiveData<Resource<MovieEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)
    fun getFavMovie(): LiveData<Resource<List<MovieEntity>>>
}