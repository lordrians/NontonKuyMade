package com.example.nontonkuymade.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.data.source.local.room.MovieDao

class MovieLocalDataSource private constructor(
    private val movieDao: MovieDao
){
    companion object {
        private var instance: MovieLocalDataSource? = null

        fun getInstance(movieDao: MovieDao): MovieLocalDataSource =
            instance ?: synchronized(this){
                instance ?: MovieLocalDataSource(movieDao)
            }
    }

    fun getAllMovie(): LiveData<List<MovieEntity>> = movieDao.getAllMovie()
    fun getDetailMovie(idMovie: String): LiveData<MovieEntity> = movieDao.getDetailMovie(idMovie)
    fun getFavMovies(): LiveData<List<MovieEntity>> = movieDao.getFavMovies()
    fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)
    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        movieDao.updateMovie(movie)
    }
    fun updateMovie(movie: MovieEntity, newState: Boolean){
        movie.isFav = newState
        movieDao.updateMovie(movie)
    }

}