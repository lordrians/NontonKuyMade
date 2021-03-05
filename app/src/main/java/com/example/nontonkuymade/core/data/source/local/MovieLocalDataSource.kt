package com.example.nontonkuymade.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity
import com.example.nontonkuymade.core.data.source.local.room.MovieDao
import com.example.nontonkuymade.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.lang.Exception

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

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()
    fun getDetailMovie(idMovie: String): Flow<MovieEntity> = movieDao.getDetailMovie(idMovie)
    fun getFavMovies(): Flow<List<MovieEntity>> = movieDao.getFavMovies()
    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)
    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        movieDao.updateMovie(movie)
    }
//    fun updateMovie(movie: MovieEntity, newState: Boolean){
//        movie.isFav = newState
//        movieDao.updateMovie(movie)
//    }

    suspend fun updateMovie(movie: MovieEntity, newState: Boolean): Unit =
            withContext(Dispatchers.IO){
                movie.isFav = newState
                movieDao.updateMovie(movie)
            }

}