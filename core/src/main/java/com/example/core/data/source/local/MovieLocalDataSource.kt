package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
){
//    companion object {
//        private var instance: MovieLocalDataSource? = null
//
//        fun getInstance(movieDao: MovieDao): MovieLocalDataSource =
//            instance ?: synchronized(this){
//                instance ?: MovieLocalDataSource(movieDao)
//            }
//    }

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()
    fun getDetailMovie(idMovie: String): Flow<MovieEntity> = movieDao.getDetailMovie(idMovie)
    fun getFavMovies(): Flow<List<MovieEntity>> = movieDao.getFavMovies()
    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)
    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        movieDao.updateMovie(movie)
    }
    fun updateMovie(movie: MovieEntity, newState: Boolean){
        movie.isFav = newState
        movieDao.updateMovie(movie)
    }

}