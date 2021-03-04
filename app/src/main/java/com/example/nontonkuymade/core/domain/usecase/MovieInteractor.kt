package com.example.nontonkuymade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.nontonkuymade.core.data.Resource
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.domain.repository.IMovieRepository

class MovieInteractor (
    private val movieRepository: IMovieRepository
) : MovieUseCase{

    override fun getAllMovie(): LiveData<Resource<List<MovieEntity>>> = movieRepository.getAllMovie()

    override fun getDetailMovie(idMovie: String): LiveData<Resource<MovieEntity>> = movieRepository.getDetailMovie(idMovie)

    override fun setFavMovie(movie: MovieEntity, state: Boolean) = movieRepository.setFavMovie(movie,state)

    override fun getFavMovie(): LiveData<Resource<List<MovieEntity>>> = movieRepository.getFavMovie()
}