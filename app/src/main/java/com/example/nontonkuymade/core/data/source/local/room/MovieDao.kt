package com.example.nontonkuymade.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nontonkuymade.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getDetailMovie(id: String?): LiveData<MovieEntity>

    @Query("SELECT * FROM movies WHERE isFav = 1")
    fun getFavMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}