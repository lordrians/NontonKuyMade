package com.example.core.domain.usecase

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: IMovieRepository
) : MovieUseCase{

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getDetailMovie(idMovie: String) = movieRepository.getDetailMovie(idMovie)

    override fun setFavMovie(movie: MovieEntity, state: Boolean) = movieRepository.setFavMovie(movie,state)

    override fun getFavMovie() = movieRepository.getFavMovie()
}