package com.example.nontonkuymade.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.domain.usecase.MovieUseCase

class DetailMovieViewModel @ViewModelInject constructor(
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
            val newState = !newData.data!!.isFav
            movieUseCase.setFavMovie(newData.data!!, newState)
        }
    }


}