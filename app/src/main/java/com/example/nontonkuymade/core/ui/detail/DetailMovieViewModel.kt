package com.example.nontonkuymade.core.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nontonkuymade.core.data.Resource
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(
    private val movieUseCase: MovieUseCase
): ViewModel(){
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun getDetailMovie(idMovie: String): LiveData<Resource<MovieEntity>>{
        detailMovie = movieUseCase.getDetailMovie(idMovie).asLiveData()
        return detailMovie
    }


    fun setFavoriteMovie(){
        val newData = detailMovie.value
        if (newData?.data != null){
            val newState = !newData.data.isFav
            movieUseCase.setFavMovie(newData.data, newState)
        }
    }


}